package com.alexisc183.registrations;

import com.alexisc183.shared.MainButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class RegistrationsPanel extends JPanel {
	public RegistrationsPanel() {
		setLayout(new BorderLayout());
		
		setComponents();
	}
	
	private void setComponents() {
		FormPanel formPanel = new FormPanel();
		MainButton register = new MainButton("Register", new Color(128, 255, 128));
		
		register.addActionListener(new RegistrationListener(this, formPanel));
		
		add(formPanel, BorderLayout.CENTER);
		add(register, BorderLayout.SOUTH);
	}
}
