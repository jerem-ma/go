import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGraph extends JPanel
{
	private JFrame parent;
	private Options options;

	public MainGraph(JFrame parent)
	{
		this.parent = parent;
		this.options = new Options();

		JButton play = new JButton("Play");
		play.addActionListener(this::playClicked);
		this.add(play);

		JButton options = new JButton("Options");
		options.addActionListener(this::optionsClicked);
		this.add(options);

		JButton quit = new JButton("Quit");
		quit.addActionListener(this::quitClicked);
		this.add(quit);
	}

	private void playClicked(ActionEvent e)
	{

	}

	private void optionsClicked(ActionEvent e)
	{
		this.setVisible(false);
		this.parent.remove(this);
		this.parent.add(new OptionsGraph(this, this.parent, this.options));
	}

	private void quitClicked(ActionEvent e)
	{
		System.exit(0);
	}
}
