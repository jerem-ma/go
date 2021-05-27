public class OvalInfo
{
	private int x;
	private int y;
	private int height;
	private int width;

	public OvalInfo(int x, int y, int height, int width)
	{
		this.setX(x);
		this.setY(y);
		this.setHeight(height);
		this.setWidth(width);
	}

	public int getX()
	{
		return this.x;
	}

	public void setX(int x)
	{
		if (x < 0)
			throw new IllegalArgumentException("x must be greater or equal to 0 !");

		this.x = x;
	}

	public int getY()
	{
		return this.y;
	}

	public void setY(int y)
	{
		if (y < 0)
			throw new IllegalArgumentException("y must be greater or equal to 0 !");

		this.y = y;
	}

	public int getHeight()
	{
		return this.height;
	}

	public void setHeight(int height)
	{
		if (height <= 0)
			throw new IllegalArgumentException("height must be greater than 0 !");

		this.height = height;
	}

	public int getWidth()
	{
		return this.width;
	}

	public void setWidth(int width)
	{
		if (width <= 0)
			throw new IllegalArgumentException("width must be greater than 0 !");

		this.width = width;
	}
}