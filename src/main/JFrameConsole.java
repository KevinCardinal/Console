package main;

/* yolo */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class JFrameConsole extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JTextPane text;
	JTextField prompt;
	JTextField mark;
	boolean finish;
	MutableAttributeSet attribute;
	Color currentColor;
	
	public JFrameConsole()
	{
		init("Console", 600, 480, Color.WHITE, Color.BLACK);
	}
	
	public JFrameConsole(String title)
	{
		init("Console - " + title, 600, 480, Color.WHITE, Color.BLACK);
	}
	
	public JFrameConsole(int width, int height)
	{
		init("Console", width, height, Color.WHITE, Color.BLACK);
	}
	
	public JFrameConsole(String title, int width, int height)
	{
		init("Console - " + title, width, height, Color.WHITE, Color.BLACK);
	}
	
	public JFrameConsole(Color foreground, Color background)
	{
		init("Console", 600, 480, foreground, background);
	}
	
	public JFrameConsole(String title, Color foreground, Color background)
	{
		init("Console - " + title, 600, 480, foreground, background);
	}
	
	public JFrameConsole(int width, int height, Color foreground, Color background)
	{
		init("Console", width, height, foreground, background);
	}
	
	public JFrameConsole(String title, int width, int height, Color foreground, Color background)
	{
		init("Console - " + title, width, height, foreground, background);
	}
	
	public void notify_finish()
	{
		finish = true;
	}
	
	private void init(String title, int width, int height, Color foreground, Color background)
	{
		finish = false;
		attribute = new SimpleAttributeSet();
		currentColor = foreground;
		
		setTitle(title);
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setDefaultLocation();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel south = new JPanel(new BorderLayout());
		add(panel);
		
		text = new JTextPane();
		text.setEditable(false);
		text.setBackground(background);
		text.setForeground(foreground);
		
		JScrollPane textpane = new JScrollPane(text);
		textpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		prompt = new JTextField("");
		south.add(prompt, BorderLayout.CENTER);
		prompt.addKeyListener(new KeyListenerConsole(this));
		
		mark = new JTextField(" >>> ", 0);
		south.add(mark, BorderLayout.WEST);
		mark.setEditable(false);
		mark.setFont(new Font("DIALOG", Font.BOLD, 12));
		mark.setForeground(Color.RED);
		mark.setBackground(Color.BLACK);
		mark.setFocusable(false);
		
		panel.add(textpane,BorderLayout.CENTER);
		panel.add(south, BorderLayout.SOUTH);
	}
	
	private void setDefaultLocation()
	{
		Dimension computer = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int heightC = (int)computer.getHeight();
		int widthC  = (int)computer.getWidth();
		Dimension window = getSize();
		int heightW = (int)window.getHeight();
		int widthW  = (int)window.getWidth();
		
		int height = (heightC - heightW) / 2;
		int width = (widthC - widthW) / 2;
		setLocation(width, height);	
	}
	
	public void print(String s, Color c)
	{
		try
		{
		StyledDocument doc = text.getStyledDocument();
		doc.insertString(doc.getLength(), s ,attribute);
		StyleConstants.setForeground(attribute, c);
        doc.setCharacterAttributes(doc.getLength() - s.length() , doc.getLength() +1, attribute, false);
        StyleConstants.setForeground(attribute, currentColor);
        text.setCaretPosition(text.getDocument().getLength());
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	public void println(String s, Color c)
	{
		print(s + "\n", c);
	}
	
	public void print(String s)
	{
		print(s, currentColor);
	}
	
	public void println(String s)
	{
		print(s + "\n", currentColor);
	}
	
	
	public String next(Color c)
	{
		return next_helper(c, true);
	}
	
	public String next()
	{
		return next(currentColor);
	}
	
	public String next_noPrint(Color c)
	{
		return next_helper(c, false);
	}
	
	public String next_noPrint()
	{
		return next_noPrint(currentColor);
	}
	
	private String next_helper(Color c, boolean display)
	{
		prompt.setEditable(true);
		prompt.grabFocus();
		mark.setForeground(Color.GREEN);
		finish = false;
		while(!finish)
		{
			try {Thread.sleep(100);}
			catch(InterruptedException e) {}
		}
		String res = prompt.getText();
		if(display) { println(res, c); }
		prompt.setText("");
		finish = false;
		mark.setForeground(Color.RED);
		prompt.setEditable(false);
		text.grabFocus();
		return res;
	}
	
	public String back_and_write(int i, String s, Color c) throws BadLocationException
	{
		StyledDocument doc = text.getStyledDocument();
		String res = doc.getText(doc.getLength() - i, i);
		doc.remove(doc.getLength() - i, i);
		doc.insertString(doc.getLength(), s, attribute);
		StyleConstants.setForeground(attribute, c);
        doc.setCharacterAttributes(doc.getLength() - s.length() , doc.getLength() +1, attribute, false);
        StyleConstants.setForeground(attribute, currentColor);
        text.setCaretPosition(text.getDocument().getLength());
        return res;
	}
	
	public String back_and_write(int i, String s) throws BadLocationException
	{
		return back_and_write(i, s, currentColor);
	}
	
	public void addEnterListenerToDisplay()
	{
		text.addKeyListener(new KeyListenerConsole(this));
	}
	
	public void setTextColor(Color c)
	{
		currentColor = c;
	}
}
