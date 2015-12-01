package engine.graphics;

import engine.math.Matrix4f;

public class RenderContext extends Bitmap
{
	public RenderContext(int width, int height)
	{
		super(width, height);
	}
	
	public void fillTriangle(Vertex v1, Vertex v2, Vertex v3)
	{
		Matrix4f scr = new Matrix4f(); scr.InitScreenSpaceTransform(getWidth()/2, getHeight()/2);
		Vertex minYVert = v1.transform(scr).perspectiveDivide();
		Vertex midYVert = v2.transform(scr).perspectiveDivide();
		Vertex maxYVert = v3.transform(scr).perspectiveDivide();
		
		if(maxYVert.getY() < midYVert.getY() )
		{
			Vertex tmp = maxYVert;
			maxYVert = midYVert;
			midYVert = tmp;
		}
		
		if(midYVert.getY() < minYVert.getY() )
		{
			Vertex tmp = midYVert;
			midYVert = minYVert;
			minYVert = tmp;
		}
		
		if(maxYVert.getY() < midYVert.getY() )
		{
			Vertex tmp = maxYVert;
			maxYVert = midYVert;
			midYVert = tmp;
		}
		
		scanTriangle(minYVert, midYVert, maxYVert, minYVert.triangleArea(maxYVert, midYVert) >= 0);
	}
	
	public void scanTriangle(Vertex minYVert, Vertex midYVert, Vertex maxYVert, boolean handedness)
	{
		Edge topToBottom = new Edge(minYVert, maxYVert);
		Edge topToMiddle = new Edge(minYVert, midYVert);
		Edge middleToBottom = new Edge(midYVert, maxYVert);
		
		Edge left = topToBottom;
		Edge right = topToMiddle;
		if(handedness)
		{
			Edge temp = left;
			left = right;
			right = temp;
		}

		int yStart = topToMiddle.getYStart(),
			yEnd = topToMiddle.getYEnd();
		for(int j = yStart; j < yEnd; j++)
		{
			drawScanLine(left, right, j);
			left.step();
			right.step();
		}
		
		left = topToBottom;
		right = middleToBottom;
		if(handedness)
		{
			Edge temp = left;
			left = right;
			right = temp;
		}

		yStart = middleToBottom.getYStart();
		yEnd = middleToBottom.getYEnd();
		for(int j = yStart; j < yEnd; j++)
		{
			drawScanLine(left, right, j);
			left.step();
			right.step();
		}
	}
	
	private void drawScanLine(Edge left, Edge right, int j)
	{
		int xMin = (int) Math.ceil(left.getX());
		int xMax = (int) Math.ceil(right.getX());
		
		for(int i = xMin; i < xMax; i++)
			drawPixel(i, j, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF);
		
	}
	
}
