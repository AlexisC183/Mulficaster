package com.alexisc183.shared;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ModuleClose extends WindowAdapter {
	private MainFrame mainFrame;
	
	public ModuleClose(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		mainFrame.setVisible(true);
	}
}
