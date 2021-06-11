import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Goban
{
	private SideLength sideLength;

	private Set<StoneInfo> stones;
	private Goban previous;
	private Goban next;
	private Color turn;

	public Goban(SideLength length)
	{
		if (length == null)
			throw new NullPointerException();

		this.sideLength = length;
		this.stones = new HashSet<StoneInfo>();
		this.previous = null;
		this.next = null;
		this.turn = Color.BLACK;
	}

	public Set<StoneInfo> getStones()
	{
		return this.stones;
	}

	public SideLength getSideLength()
	{
		return this.sideLength;
	}

	public Color getTurn()
	{
		returnt this.turn;
	}

	public void switchColor()
	{
		this.turn = this.turn == Color.BLACK ? Color.WHITE : Color.BLACK;
	}

	public boolean isLegal(StoneInfo stone)
	{
		// Fill this method to tell if a move is legal
		return true;
	}
}
