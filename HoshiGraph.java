public class HoshiGraph
{
	private int x;
	private int y;
	private int size;

	public HoshiGraph(int x, int y, int size)
	{
		this.setX(x);
		this.setY(y);
		this.setSize(size);
	}

	public int getX()
	{
		return this.x;
	}

	public void setX(int x)
	{
		if (x < 0)
			throw new IllegalArgumentException("x must be greater than 0 !");

		this.x = x;
	}

	public int getY()
	{
		return this.y;
	}

	public void setY(int y)
	{
		if (y < 0)
			throw new IllegalArgumentException("y must be greater than 0!");

		this.y = y;
	}

	public int getSize()
	{
		return this.size;
	}

	public void setSize(int size)
	{
		if (size <= 0)
			throw new IllegalArgumentException("size must be greater than 0 !");

		this.size = size;
	}
}