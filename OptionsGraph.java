import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OptionsGraph extends JPanel implements ItemListener, ActionListener
{
	private Options options;
	private MainGraph mainGraph;
	private JFrame parent;

	public OptionsGraph(MainGraph mainGraph, JFrame parent, Options options)
	{
		this.mainGraph = mainGraph;
		this.parent = parent;
		this.options = options;

		JRadioButton small = new JRadioButton("Small");
		JRadioButton medium = new JRadioButton("Medium");
		JRadioButton large = new JRadioButton("Large", true);

		ButtonGroup selectSizeButtons = new ButtonGroup();
		selectSizeButtons.add(small);
		selectSizeButtons.add(medium);
		selectSizeButtons.add(large);

		small.addItemListener(this);
		medium.addItemListener(this);
		large.addItemListener(this);

		this.add(small);
		this.add(medium);
		this.add(large);

		JButton returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		this.add(returnButton);

		this.setVisible(true);
	}

	public void itemStateChanged(ItemEvent e)
	{
		switch (((JRadioButton) e.getSource()).getText())
		{
			case "Small":
				this.options.gobanSize = SideLength.SMALL;
				break;

			case "Medium":
				this.options.gobanSize = SideLength.MEDIUM;
				break;

			case "Large":
				this.options.gobanSize = SideLength.LARGE;
				break;
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		this.setVisible(false);
		this.parent.remove(this);
		this.parent.add(this.mainGraph);
		this.mainGraph.setVisible(true);
	}
}
