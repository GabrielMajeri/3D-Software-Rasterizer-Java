package engine.graphics;

import engine.math.Matrix4f;
import engine.math.Vector4f;

public class Vertex
{
	private Vector4f pos;
	private Vector4f color;
	
	public Vector4f getColor(){ return color; }
	
	public Vertex(Vector4f v, Vector4f c)
	{
		pos = v;
		color = c;
	}
	
	public Vertex transform(Matrix4f transform)
	{
		return new Vertex(transform.Transform(pos), color);
	}
	
	public Vertex perspectiveDivide()
	{
		return new Vertex(new Vector4f(pos.getX()/pos.getW(), pos.getY()/pos.getW(), pos.getZ()/pos.getZ(), pos.getW()), color);
	}
	
	public float triangleArea(Vertex b, Vertex c)
	{
		float x1 = b.pos.getX() - pos.getX(),
			   y1 = b.pos.getY() - pos.getY();
		float x2 = c.pos.getX() - pos.getX(),
			  y2 = c.pos.getY() - pos.getY();
		
		return (x1*y2 - x2 * y1)/2.0f;
	}

	public float getX()
	{
		return pos.getX();
	}

	public float getY()
	{
		return pos.getY();
	}
}
