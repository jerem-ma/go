import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UndoRedoPanel extends JPanel
{
	private GobanGraph gobanGraph;

	public UndoRedoPanel(GobanGraph gobanGraph)
	{
		this.gobanGraph = gobanGraph;

		JButton undo = new JButton("Undo");
		JButton redo = new JButton("Redo");

		undo.addActionListener(this::undo);
		redo.addActionListener(this::redo);

		this.add(undo);
		this.add(redo);

		this.setVisible(true);
	}

	public void undo(ActionEvent e)
	{
		this.gobanGraph.getGoban().undo();
		this.gobanGraph.repaint();
	}

	public void redo(ActionEvent e)
	{
		this.gobanGraph.getGoban().redo();
		this.gobanGraph.repaint();
	}
}
