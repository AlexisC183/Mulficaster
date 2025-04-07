package com.alexisc183.shared;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.KeyStroke;

public class MainButton extends JButton implements StringSetter {
	private final class Hover extends MouseAdapter {
		private MainButton button;
		private Color background;
		
		public Hover(MainButton button) {
			this.button = button;
			background = button.getBackground();
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if (button.isEnabled()) {
				button.setBackground(Color.WHITE);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			button.setBackground(background);
		}
	}
	
	public MainButton(String label, Color background) {
		super(label);
		
		setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		setForeground(Color.BLACK);
		setBackground(background);
		
		addMouseListener(new Hover(this));
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "action");
		getActionMap().put("action", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainButton.this.actionListener.actionPerformed(e);
			}
		});
	}
	
	@Override
	public void set(String text) {
		setText(text);
	}
}
