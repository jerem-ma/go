import java.util.HashSet;
import java.util.Set;

public class Goban
{
	private Set<StoneInfo> stones;
	private Goban previous;
	private Goban next;

	public Goban()
	{
		this.stones = new HashSet<StoneInfo>();
		this.previous = null;
		this.next = null;
	}

	public Set<StoneInfo> getStones()
	{
		return this.stones;
	}
}
