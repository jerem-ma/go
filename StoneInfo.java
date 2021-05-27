import java.awt.Color;

public class StoneInfo 
{
	private int x;
	private int y;
	private Color color;

	public StoneInfo(int x, int y, Color color)
	{
		this.setX(x);
		this.setY(y);
		this.setColor(color);
	}
	
	public int getX()
	{
		return this.x;
	}

	public void setX(int x)
	{
		if (x < 0)
			throw new IllegalArgumentException("x must be greater or equal to 0");

		this.x = x;
	}

	public int getY()
	{
		return this.y;
	}

	public void setY(int y)
	{
		if (y < 0)
			throw new IllegalArgumentException("y must be greater or equal to 0");

		this.y = y;
	}

	public Color getColor()
	{
		return this.color;
	}

	public void setColor(Color color)
	{
		if (color == null)
			throw new NullPointerException();

		this.color = color;
	}
}
