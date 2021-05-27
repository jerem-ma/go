import java.awt.*;
import javax.swing.*;

public class GobanGraph extends JPanel
{
	private SideLength sideLength;

	public GobanGraph(SideLength sideLength)
	{
		this.setSideLength(sideLength);
	}

	public SideLength getSideLength()
	{
		return this.sideLength;
	}

	public void setSideLength(SideLength sideLength)
	{
		if (sideLength == null)
			throw new NullPointerException();

		this.sideLength = sideLength;
	}

	private void drawGrid(Graphics g)
	{
		double gapX = this.getGapX();
		double gapY = this.getGapY();

		Dimension size = this.getSize();

		for (double x = 0; x < size.getWidth(); x += gapX)
		{
			g.drawLine((int) x, 0, (int) x, (int) size.getHeight());
		}

		for (double y = 0; y < size.getHeight(); y += gapY)
		{
			g.drawLine(0, (int) y, (int) size.getWidth(), (int) y);
		}
	}

	private double getGapX()
	{
		return this.getSize().getWidth() / this.sideLength.length;
	}

	private double getGapY()
	{
		return this.getSize().getHeight() / this.sideLength.length;
	}

	private void drawStone(Graphics g, StoneGraph stone)
	{
		Color oldColor = g.getColor();
		g.setColor(stone.getColor());

		double gapX = this.getGapX();
		double gapY = this.getGapY();

		int x = (int) ((stone.getX()+.25)*gapX);
		int y = (int) ((stone.getY()+.25)*gapY);
		int width = (int) (gapX / 2);
		int height = (int) (gapY / 2);

		g.fillOval(x, y, width, height);

		g.setColor(oldColor);
	}

	private void drawHoshi(Graphics g, HoshiGraph hoshi)
	{
		Color oldColor = g.getColor();
		g.setColor(Color.BLACK);

		double gapX = this.getGapX();
		double gapY = this.getGapY();

		int x = (int) (hoshi.getX()*gapX - hoshi.getSize()/2);
		int y = (int) (hoshi.getY()*gapY - hoshi.getSize()/2);
		int size = hoshi.getSize();

		g.fillOval(x, y, size, size);

		g.setColor(oldColor);
	}
}
