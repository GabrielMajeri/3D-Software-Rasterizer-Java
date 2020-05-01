package engine.graphics;

public class Edge
{
	public final double xStep;
	public final int yStart, yEnd;

	private double x;

	public Edge(Vertex minYVert, Vertex maxYVert)
	{
		yStart = (int)Math.ceil(minYVert.position.y);
		yEnd = (int)Math.ceil(maxYVert.position.y);
		
		double yDist = maxYVert.position.y - minYVert.position.y;
		double xDist = maxYVert.position.x - minYVert.position.x;

		double yPrestep = yStart - minYVert.position.y;
		
		xStep = xDist / yDist;
		x = minYVert.position.x + yPrestep * xStep;
	}
	
	public void step()
	{
		x += xStep;
	}
	public double getX() { return x; }
}
