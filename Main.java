import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new GobanGraph(SideLength.SMALL));

		frame.setMinimumSize(new Dimension(800, 800));

		frame.setVisible(true);
	}
}
