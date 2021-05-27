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
		this.drawOval(g, stone, stone.getColor());
	}

	private void drawHoshi(Graphics g, OvalInfo hoshi)
	{
		this.drawOval(g, hoshi, Color.BLACK);
	}

	private void drawOval(Graphics g, OvalInfo oval, Color color)
	{
		Color oldColor = g.getColor();
		g.setColor(color);

		double gapX = this.getGapX();
		double gapY = this.getGapY();

		int x = (int) (oval.getX()*gapX - oval.getWidth()/2);
		int y = (int) (oval.getY()*gapY - oval.getHeight()/2);
		int width = oval.getWidth();
		int height = oval.getHeight();

		g.fillOval(x, y, width, height);

		g.setColor(oldColor);
	}
}
