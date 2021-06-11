import java.awt.*;
import java.util.*;
import java.util.List;

public class Goban
{
	private SideLength sideLength;

	private StoneInfo[][] stones;
	private Goban previous;
	private Goban next;
	private Color turn;

	public Goban(SideLength length)
	{
		if (length == null)
			throw new NullPointerException();

		this.sideLength = length;
		this.stones = initStones();
		this.previous = null;
		this.next = null;
		this.turn = Color.BLACK;
	}

	private StoneInfo[][] initStones()
	{
		return new StoneInfo[this.sideLength.length][this.sideLength.length];
	}

	public StoneInfo[][] getStones()
	{
		return this.stones;
	}

	public SideLength getSideLength()
	{
		return this.sideLength;
	}

	public Color getTurn()
	{
		return this.turn;
	}

	public void switchColor()
	{
		this.turn = this.turn == Color.BLACK ? Color.WHITE : Color.BLACK;
	}

	public boolean isPlaceable(StoneInfo stone)
	{
		return !this.exists(stone) && this.isLegal(stone);
	}

	public boolean exists(StoneInfo stone)
	{
		return this.toList(this.stones).contains(stone);
	}

	private <T> List<T> toList(T[][] array)
	{
		List<T> list = new ArrayList<T>();

		for (T[] line : array)
		{
			for (T element : line)
			{
				list.add(element);
			}
		}

		return list;
	}

	public boolean isLegal(StoneInfo stone)
	{
		// Fill this method to tell if a move is legal
		return true;
	}

	public void play(StoneInfo stone)
	{
		this.setStone(stone);
		this.switchColor();
	}

	public void setStone(StoneInfo stone)
	{
		if (!isPlaceable(stone))
			throw new IllegalMoveException();

		stones[stone.getX()][stone.getY()] = stone;
	}

	public int getNumberOfGroupLiberties(StoneInfo stone)
	{
		return this.getGroupLiberties(stone).size();
	}

	public Set<Point> getGroupLiberties(StoneInfo stone)
	{
		return this.getGroupLiberties(stone, new HashSet<StoneInfo>());
	}

	public Set<Point> getGroupLiberties(StoneInfo stone, Set<StoneInfo> visited)
	{
		Set<Point> liberties = new HashSet<Point>();

		visited.add(stone);
		liberties.addAll(this.getSingleLiberties(stone));

		for (StoneInfo neighbour : this.getNeighbours(stone))
		{
			if (!visited.contains(neighbour) && neighbour.getColor().equals(stone.getColor()))
				liberties.addAll(getGroupLiberties(neighbour, visited));
		}

		return liberties;
	}

	public Set<Point> getSingleLiberties(StoneInfo stone)
	{
		Set<Point> liberties = this.getBaseLiberties(stone);

		for (StoneInfo neighbour : this.getNeighbours(stone))
		{
			liberties.remove(new Point(neighbour.getX(), neighbour.getY()));
		}

		return liberties;
	}

	public Set<Point> getBaseLiberties(StoneInfo stone)
	{
		Set<Point> liberties = new HashSet<Point>();

		for (Point potentialNeighbour : getPotentialNeighbours(stone))
		{
			if (this.isInGoban(potentialNeighbour.x, potentialNeighbour.y))
				liberties.add(potentialNeighbour);
		}

		return liberties;
	}

	private Point[] getPotentialNeighbours(StoneInfo stone)
	{
		return new Point[]{new Point(stone.getX()-1, stone.getY()),
																	 new Point(stone.getX()+1, stone.getY()),
																	 new Point(stone.getX(), stone.getY()-1),
																	 new Point(stone.getX(), stone.getY()+1)};
	}

	public StoneInfo[] getNeighbours(StoneInfo stone)
	{
		Set<StoneInfo> neighboursSet = new HashSet<StoneInfo>();

		for (Point potentialNeighbour : getPotentialNeighbours(stone))
		{
			if (this.isInGoban(potentialNeighbour.x, potentialNeighbour.y) &&
					this.stones[potentialNeighbour.x][potentialNeighbour.y] != null)
			{
				neighboursSet.add(this.stones[potentialNeighbour.x][potentialNeighbour.y]);
			}
		}

		return neighboursSet.toArray(new StoneInfo[neighboursSet.size()]);
	}

	private boolean isInGoban(int x, int y)
	{
		return x >= 0 && y >= 0 && x < this.sideLength.length && y < this.sideLength.length;
	}
}
