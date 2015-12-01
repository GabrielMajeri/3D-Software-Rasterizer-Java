package engine;

import engine.graphics.Display;
import engine.graphics.RenderContext;
import engine.graphics.Vertex;
import engine.math.Matrix4f;
import engine.math.Vector4f;

public class Main
{
	public static void main(String[] args)
	{
		Display display = new Display(800, 600, "Software renderer");
		
		RenderContext target = display.getFrameBuffer();
		
		Vertex v1 = new Vertex(new Vector4f(-1, -1, 0, 1), new Vector4f(1, 1, 1, 1)), 
				v2 = new Vertex(new Vector4f(0, 1, 0, 1), new Vector4f(1, 1, 1, 1)), 
				v3 = new Vertex(new Vector4f(1, -1, 0, 1), new Vector4f(1, 1, 1, 1));
		
		Matrix4f proj = new Matrix4f().InitPerspective((float)Math.toRadians(70), (float)target.getWidth()/(float)target.getHeight(),
				0.1f, 1000);
		
		float rotCounter = 0.f;
		
		long lastTime = System.nanoTime();
		while(true)
		{
			long currentTime = System.nanoTime();
			float delta = (float)((currentTime - lastTime) / 1000000000.0);
			lastTime = currentTime;
			
			rotCounter += delta;
			
			Matrix4f tran = new Matrix4f().InitTranslation(0, 0, 4.f);
			Matrix4f rot = new Matrix4f().InitRotation(0, rotCounter, 0);
			Matrix4f trn = proj.Mul(tran.Mul(rot));
			
			target.clear((byte)0x00);
			target.fillTriangle(v1.transform(trn), v2.transform(trn), v3.transform(trn));
			
			
			display.swapBuffers();
		}
	}

}
