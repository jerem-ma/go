import java.awt.Color;
import java.util.*;
import java.util.*;

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
		return Arrays.asList(this.stones).contains(stone);
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
}
