import java.awt.*;
import java.awt.event.*;
import java.util.Set;

import javax.swing.*;

public class GobanGraph extends JPanel implements MouseMotionListener
{
	private final static int SIZE_HOSHI = 20;
	private final static int SIZE_STONE = 50;

	private final static int OFFSET = SIZE_STONE / 2 + 5;

	private Goban goban;
	private Point currentPos;

	public GobanGraph(Goban goban)
	{
		this.setGoban(goban);
		this.currentPos = null;
	}

	public Goban getGoban()
	{
		return this.goban;
	}

	public void setGoban(Goban goban)
	{
		if (goban == null)
			throw new NullPointerException();

		this.goban = goban;
	}

	@Override
	public Dimension getSize()
	{
		Dimension sizeWithOffset = new Dimension();
		sizeWithOffset.setSize(super.getSize().getWidth()-2*OFFSET, super.getSize().getHeight()-2*OFFSET);

		return sizeWithOffset;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.translate(OFFSET, OFFSET);
		this.drawGrid(g);
		this.drawHoshis(g);
		this.drawStones(g);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		this.currentPos = e.getPoint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

  private void drawGrid(Graphics g)
  {
    double gapX = this.getGapX();
    double gapY = this.getGapY();

    Dimension size = this.getSize();

    for (double x = 0; x <= size.getWidth(); x += gapX)
    {
      g.drawLine((int) x, 0, (int) x, (int) size.getHeight());
    }

    for (double y = 0; y <= size.getHeight(); y += gapY)
    {
      g.drawLine(0, (int) y, (int) size.getWidth(), (int) y);
    }
  }

	private double getGapX()
	{
		return this.getSize().getWidth() / this.goban.getSideLength().length;
	}

	private double getGapY()
	{
		return this.getSize().getHeight() / this.goban.getSideLength().length;
	}

	private void drawStones(Graphics g)
	{
		Set<StoneInfo> stones = this.goban.getStones();

		for (StoneInfo stone : stones)
		{
			this.drawStone(g, stone);
		}
	}

	private void drawStone(Graphics g, StoneInfo stone)
	{
		OvalInfo oval = new OvalInfo(stone.getX(), stone.getY(), SIZE_STONE, SIZE_STONE);
		this.drawOval(g, oval, stone.getColor());
	}

	private void drawHoshis(Graphics g)
	{
		if (this.goban.getSideLength() == SideLength.SMALL)
		{
			this.drawHoshi(g, 2, 2);
			this.drawHoshi(g, 6, 2);
			this.drawHoshi(g, 4, 4);
			this.drawHoshi(g, 2, 6);
			this.drawHoshi(g, 6, 6);
		}

		else if (this.goban.getSideLength() == SideLength.MEDIUM)
		{
			this.drawHoshi(g, 3, 3);
			this.drawHoshi(g, 9, 3);
			this.drawHoshi(g, 6, 6);
			this.drawHoshi(g, 3, 9);
			this.drawHoshi(g, 9, 9);
		}

		else if (this.goban.getSideLength() == SideLength.LARGE)
		{
			this.drawHoshi(g, 3, 3);
			this.drawHoshi(g, 9, 3);
			this.drawHoshi(g, 15, 3);
			this.drawHoshi(g, 3, 9);
			this.drawHoshi(g, 9, 9);
			this.drawHoshi(g, 15, 9);
			this.drawHoshi(g, 3, 15);
			this.drawHoshi(g, 9, 15);
			this.drawHoshi(g, 15, 15);
		}
	}

	private void drawHoshi(Graphics g, int x, int y)
	{
		OvalInfo oval = new OvalInfo(x, y, SIZE_HOSHI, SIZE_HOSHI);
		this.drawOval(g, oval, Color.BLACK);
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
