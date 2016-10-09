package main;

import java.awt.Color;

import javax.swing.text.BadLocationException;

/* yolo */

public final class Console {

	private static JFrameConsole frame;
	private static boolean already_open = false;
	private static boolean already_close = false;
	
	public static void open() { open_helper(new JFrameConsole()); }
	
	public static void open(String title) { open_helper(new JFrameConsole(title)); }
	
	public static void open(int width, int height) { open_helper(new JFrameConsole(width, height)); }
	
	public static void open(String title, int width, int height)
	{ open_helper(new JFrameConsole(title, width, height)); }
	
	public static void open(Color foreground, Color background)
	{ open_helper(new JFrameConsole(foreground, background)); }
	
	public static void open(String title, Color foreground, Color background)
	{ open_helper(new JFrameConsole(title, foreground, background)); }
	
	public static void open(int width, int height, Color foreground, Color background)
	{ open_helper(new JFrameConsole(width, height, foreground, background)); }
	
	public static void open(String title, int width, int height, Color foreground, Color background)
	{ open_helper(new JFrameConsole(title, width, height, foreground, background)); }
	
	private static void open_helper(JFrameConsole f)
	{
		if(!already_open)
		{
			frame = f;
			frame.setVisible(true);
			already_open = true;
		}
		else
		{
			throw new RuntimeException("Une console a deja ete ouverte");
		}
	}
	
	public static void close()
	{
		if(already_open && !already_close)
		{
			frame.setVisible(false);
			frame.dispose();
			already_close = true;
		}
		else
		{
			throw new RuntimeException("La console est soit deja fermee, soit pas encore ouverte");
		}
	}
	
	public static void exit()
	{
		if(already_open && !already_close)
		{
			print("\n Appuyez sur Enter pour continuer...");
			frame.addEnterListenerToDisplay();
			next();
			frame.setVisible(false);
			frame.dispose();
			already_close = true;
		}
		else
		{
			throw new RuntimeException("La console est soit deja fermee, soit pas encore ouverte");
		}
		
	}
	
	public static void print(Object o) { frame.print(o.toString()); }
	
	public static void println(Object o) { frame.println(o.toString()); }
	
	public static void print(Object o, Color c) { frame.print(o.toString(), c); }
	
	public static void println(Object o, Color c) {frame.println(o.toString(), c); }
	
	public static String next() { return frame.next(); }
	
	public static String next(Color c) { return frame.next(c); }
	
	public static String next_noPrint() { return frame.next_noPrint(); }
	
	public static String next_noPrint(Color c) { return frame.next_noPrint(c); }
	
	public static String back_and_write(int i, String s) throws BadLocationException
	{ return frame.back_and_write(i, s); }
	
	public static String back_and_write(int i, String s, Color c) throws BadLocationException
	{ return frame.back_and_write(i, s, c); }
	
	public static void setTextColor(Color c) { frame.setTextColor(c); }
	
}
