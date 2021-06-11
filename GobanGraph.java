import java.awt.*;
import java.awt.event.*;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.*;

public class GobanGraph extends JPanel implements MouseInputListener
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

		this.addMouseMotionListener(this);
		this.addMouseListener(this);
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
		this.drawMousePos(g);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		this.currentPos = e.getPoint();
		this.currentPos.translate(-OFFSET, -OFFSET);
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Point steppedPos = this.getSteppedPos();
		StoneInfo stone = new StoneInfo(steppedPos.x, steppedPos.y, this.goban.getTurn());

		if (!this.goban.isPlaceable(stone))
			return;

		this.goban.play(stone);
		this.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

  private void drawGrid(Graphics g)
  {
    double gapX = this.getGapX();
    double gapY = this.getGapY();

    Dimension size = this.getSize();

    for (double x = 0; (int) x <= size.getWidth(); x += gapX)
    {
      g.drawLine((int) x, 0, (int) x, (int) size.getHeight());
    }

    for (double y = 0; (int) y <= size.getHeight(); y += gapY)
    {
      g.drawLine(0, (int) y, (int) size.getWidth(), (int) y);
    }
  }

	private double getGapX()
	{
		return this.getSize().getWidth() / (this.goban.getSideLength().length-1);
	}

	private double getGapY()
	{
		return this.getSize().getHeight() / (this.goban.getSideLength().length-1);
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

	private void drawMousePos(Graphics g)
	{
		if (this.currentPos == null)
			return;

		Point steppedPos = getSteppedPos();
		StoneInfo stone = new StoneInfo(steppedPos.x, steppedPos.y, this.goban.getTurn());

		if (this.goban.exists(stone))
			return;

		OvalInfo oval = new OvalInfo(steppedPos.x, steppedPos.y, SIZE_STONE, SIZE_STONE);

		Color baseColor = this.goban.getTurn();
		Color color;

		if (!this.goban.isLegal(stone))
			color = new Color(255, 0, 0, 127);
		else
			color = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 127);

		this.drawOval(g, oval, color);
	}

	private Point getSteppedPos()
	{
		if (this.currentPos == null)
			return null;

		double gapX = this.getGapX();
		double gapY = this.getGapY();

		double xMiddled = this.currentPos.x + gapX/2;
		double yMiddled = this.currentPos.y + gapY/2;

		int steppedX = (int) Math.round(((xMiddled - xMiddled % gapX) / gapX));
		int steppedY = (int) Math.round(((yMiddled - yMiddled % gapY) / gapY));

		return new Point(steppedX, steppedY);
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
