import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Main
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		frame.add(new MainGraph(frame));

		frame.setMinimumSize(new Dimension(800, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}
}
