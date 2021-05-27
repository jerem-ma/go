import java.awt.Color;

public class StoneGraph extends OvalInfo{
	private Color color;

	public StoneGraph(int x, int y, int height, int width, Color color)
	{
		super(x, y, height, width);
		this.setColor(color);
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
