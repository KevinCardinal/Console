package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerConsole implements KeyListener {

	JFrameConsole frame;
	
	public KeyListenerConsole(JFrameConsole frame)
	{
		this.frame = frame;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			frame.notify_finish();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
