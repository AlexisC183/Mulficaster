package com.alexisc183.shared;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {
	public MainFrame() {
		Toolkit userSystem = Toolkit.getDefaultToolkit();
		Dimension screenSize = userSystem.getScreenSize();
		
		setTitle("Home - Mulficaster");
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 300) / 2, 400, 300);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		try {
			Image icon = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("resources/icon.png"));
			
			setIconImage(icon);
		} catch (IOException e) {

		}
		
		add(new MainPanel(this));
	}
}
