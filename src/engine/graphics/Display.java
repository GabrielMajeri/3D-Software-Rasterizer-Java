package engine.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JFrame;

public class Display extends Canvas
{
	private static final long serialVersionUID = 1L;

	private final JFrame frame;
	
	private final RenderContext frameBuffer;
	private final BufferedImage displayImage;
	private final byte[] displayComponents;
	private final BufferStrategy bf;
	private final Graphics g;
	
	public Display(int width, int height, String title)
	{
		super();
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		frameBuffer = new RenderContext(width, height);
		frameBuffer.clear((byte)0x00);
		frameBuffer.drawPixel(100, 100, (byte)0x00, (byte)0, (byte)0x00, (byte)0xFF);
		displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		displayComponents = ((DataBufferByte)displayImage.getRaster().getDataBuffer()).getData();
		
		frame = new JFrame(title);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		createBufferStrategy(1);
		bf = getBufferStrategy();
		g = bf.getDrawGraphics();
	}
	
	public void swapBuffers()
	{
		frameBuffer.copyToByteArray(displayComponents);
		g.drawImage(displayImage, 0, 0, getWidth(), getHeight(), null);
		bf.show();
	}
	
	public RenderContext getFrameBuffer()
	{
		return frameBuffer;
	}
}
