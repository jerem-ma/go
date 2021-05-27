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
		Dimension size = this.getSize();
		double gapX = size.getWidth() / this.sideLength.length;
		double gapY = size.getHeight() / this.sideLength.length;

		for (double x = 0; x < size.getWidth(); x += gapX)
		{
			g.drawLine((int) x, 0, (int) x, (int) size.getHeight());
		}

		for (double y = 0; y < size.getHeight(); y += gapY)
		{
			g.drawLine(0, (int) y, (int) size.getWidth(), (int) y);
		}
	}
}
