import java.util.HashSet;
import java.util.Set;

public class Goban
{
	private SideLength sideLength;

	private Set<StoneInfo> stones;
	private Goban previous;
	private Goban next;

	public Goban(SideLength length)
	{
		if (length == null)
			throw new NullPointerException();

		this.sideLength = length;
		this.stones = new HashSet<StoneInfo>();
		this.previous = null;
		this.next = null;
	}

	public Set<StoneInfo> getStones()
	{
		return this.stones;
	}

	public SideLength getSideLength()
	{
		return this.sideLength;
	}
}
