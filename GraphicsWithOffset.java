import java.awt.*;

public class GraphicsWithOffset{
	public final Graphics g;
	private int offsetX;
	private int offsetY;

	public GraphicsWithOffset(Graphics g, int offsetX, int offsetY)
	{
		if (g == null)
			throw new NullPointerException();

		this.g = g;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public void drawLine(int x1, int y1, int x2, int y2)
	{
		g.drawLine(x1+offsetX, y1+offsetY, x2+offsetX, y2+offsetY);
	}

	public Color getColor()
	{
		return g.getColor();
	}

	public void setColor(Color color)
	{
		g.setColor(color);
	}

	public void fillOval(int x, int y, int width, int height)
	{
		g.fillOval(x+offsetX, y+offsetY, width, height);
	}
}
