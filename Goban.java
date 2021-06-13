import java.awt.*;
import java.util.*;
import java.util.List;

public class Goban implements Cloneable
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

	@Override
	public Goban clone()
	{
		Goban cloned;
		try
		{
			cloned = (Goban) super.clone();
			cloned.stones = cloneArray(this.stones);
		}
		catch (CloneNotSupportedException e)
		{
			System.out.println("Cloning not allowed. Returning this...");
			cloned = this;
		}

		return cloned;
	}

	private static StoneInfo[][] cloneArray(StoneInfo[][] array)
	{
		StoneInfo[][] cloned = new StoneInfo[array.length][];

		for (int i = 0; i < array.length; i++)
		{
			cloned[i] = new StoneInfo[array[i].length];
			for (int j = 0; j < array[i].length; j++)
			{
				cloned[i][j] = array[i][j];
			}
		}

		return cloned;
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
		return !isSuicide(stone);
	}

	private boolean isSuicide(StoneInfo stone)
	{
		if (this.getNumberOfGroupLiberties(stone) != 0)
			return false;

		for (StoneInfo neighbour : getNeighbours(stone))
		{
			if (!neighbour.getColor().equals(stone.getColor()))
			{
				if (this.getNumberOfGroupLiberties(neighbour) == 1)
					return false;
			}
		}

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

		killStonesAround(stone);
	}

	public void deleteStone(Point point)
	{
		stones[point.x][point.y] = null;
	}

	public void killStonesAround(StoneInfo stone)
	{
		for (StoneInfo neighbour : this.getNeighbours(stone))
		{
			if (this.getNumberOfGroupLiberties(neighbour) == 0)
			{
				this.killGroup(neighbour);
			}
		}
	}

	public void killGroup(StoneInfo stone)
	{
		List<StoneInfo> group = getGroup(stone);

		for (StoneInfo member : group)
		{
			this.deleteStone(new Point(member.getX(), member.getY()));
		}
	}

	public List<StoneInfo> getGroup(StoneInfo stone)
	{
		List<StoneInfo> group = new ArrayList<StoneInfo>();
		group.add(stone);

		ListIterator<StoneInfo> li = group.listIterator(group.size());

		while (li.hasPrevious()) // Reverse order to loop through recently added items. Cf. javadoc
		{
			StoneInfo current = li.previous();

			for (StoneInfo neighbour : this.getNeighbours(current))
			{
				if (neighbour.getColor().equals(stone.getColor()) && !group.contains(neighbour))
					li.add(neighbour);
			}
		}

		return group;
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
		liberties.add(new Point(stone.getX(), stone.getY()));
		liberties.addAll(this.getSingleLiberties(stone));

		for (StoneInfo neighbour : this.getNeighbours(stone))
		{
			if (!visited.contains(neighbour) && neighbour.getColor().equals(stone.getColor()))
				liberties.addAll(getGroupLiberties(neighbour, visited));
		}

		liberties.remove(new Point(stone.getX(), stone.getY()));

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
