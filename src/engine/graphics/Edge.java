package engine.graphics;

public class Edge
{
	private float x, xStep;
	private int yStart, yEnd;
	
	public float getX(){ return x; }
	public int getYStart(){ return yStart; }
	public int getYEnd(){ return yEnd; }
	
	Edge(Vertex minYVert, Vertex maxYVert)
	{
		yStart = (int)Math.ceil(minYVert.getY());
		yEnd = (int)Math.ceil(maxYVert.getY());
		
		float yDist = maxYVert.getY() - minYVert.getY();
		float xDist = maxYVert.getX() - minYVert.getX();
		
		float yPrestep = yStart - minYVert.getY();
		
		xStep = (float)xDist/(float)yDist;
		x = minYVert.getX() + yPrestep * xStep;
	}
	
	public void step()
	{
		x += xStep;
	}
	
	
}
