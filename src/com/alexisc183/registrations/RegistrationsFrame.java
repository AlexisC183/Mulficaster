package com.alexisc183.registrations;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.alexisc183.shared.MainFrame;
import com.alexisc183.shared.ModuleClose;

public class RegistrationsFrame extends JFrame {
	public RegistrationsFrame(MainFrame mainFrame) {
		Toolkit userSystem = Toolkit.getDefaultToolkit();
		Dimension screenSize = userSystem.getScreenSize();
		
		setTitle("Register machine - Mulficaster");
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 300) / 2, 400, 300);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		try {
			Image icon = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("resources/icon.png"));
			
			setIconImage(icon);
		} catch (IOException e) {

		}
		
		addWindowListener(new ModuleClose(mainFrame));
		
		add(new RegistrationsPanel());
	}
}
