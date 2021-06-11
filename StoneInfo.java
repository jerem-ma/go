import java.awt.Color;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (!(o instanceof StoneInfo))
			return false;

		StoneInfo stone = (StoneInfo) o;

		return this.x == stone.x && this.y == stone.y;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.x, this.y);
	}
}
