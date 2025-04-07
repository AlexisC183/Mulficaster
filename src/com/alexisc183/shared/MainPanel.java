package com.alexisc183.shared;

import com.alexisc183.receiver.ReceivingListener;
import com.alexisc183.registrations.RegistrationsFrame;
import com.alexisc183.sender.SenderFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private MainFrame mainFrame;
	
	public MainPanel(MainFrame mainFrame) {
		setLayout(new GridLayout(3, 1));
		
		this.mainFrame = mainFrame;
		
		setComponents();
	}
	
	private void setComponents() {
		MainButton send = new MainButton("Send file", new Color(255, 191, 191));
		MainButton receive = new MainButton("Receive file", new Color(255, 255, 127));
		MainButton registerMachine = new MainButton("Register machine", new Color(127, 255, 127));
		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				new SenderFrame(mainFrame).setVisible(true);
			}
		});
		receive.addActionListener(new ReceivingListener(this, send, receive, registerMachine));
		registerMachine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				new RegistrationsFrame(mainFrame).setVisible(true);
			}
		});
		
		add(send);
		add(receive);
		add(registerMachine);
	}
}
