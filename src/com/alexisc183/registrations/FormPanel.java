package com.alexisc183.registrations;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultEditorKit.InsertTabAction;

public class FormPanel extends JPanel {
	private JTextArea nameTextArea, ipTextArea;
	
	public FormPanel() {
		setLayout(new GridLayout(2, 2));
		
		setComponents();
	}
	
	private void setComponents() {
		nameTextArea = new JTextArea();
		ipTextArea = new JTextArea();
		KeyStroke tabStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
		KeyStroke backTabStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_DOWN_MASK);
		
		nameTextArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		ipTextArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		nameTextArea.setForeground(Color.BLACK);
		ipTextArea.setForeground(Color.BLACK);
		nameTextArea.setLineWrap(true);
		ipTextArea.setLineWrap(true);
		nameTextArea.setTabSize(0);
		ipTextArea.setTabSize(0);
		
		nameTextArea.getInputMap().put(tabStroke, "tab");
		nameTextArea.getActionMap().put("tab", new InsertTabAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nameTextArea.transferFocus();
			}
		});
		nameTextArea.getInputMap().put(backTabStroke, "backTab");
		nameTextArea.getActionMap().put("backTab", new InsertTabAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nameTextArea.transferFocusBackward();
			}
		});
		ipTextArea.getInputMap().put(tabStroke, "tab");
		ipTextArea.getActionMap().put("tab", new InsertTabAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ipTextArea.transferFocus();
			}
		});
		ipTextArea.getInputMap().put(backTabStroke, "backTab");
		ipTextArea.getActionMap().put("backTab", new InsertTabAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ipTextArea.transferFocusBackward();
			}
		});
		
		JComponent[] components = new JComponent[4];
		
		components[0] = new JLabel("<html>Enter machine name (for example \"Alex's laptop\"):</html>", SwingConstants.CENTER);
		components[1] = new JScrollPane(nameTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		components[2] = new JLabel("<html>Enter the IPv4 address of the machine (for example 192.168.0.183):</html>", SwingConstants.CENTER);
		components[3] = new JScrollPane(ipTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		components[0].setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		components[2].setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		components[0].setForeground(Color.BLACK);
		components[2].setForeground(Color.BLACK);
		
		for (JComponent component : components) {
			component.setBorder(new LineBorder(getBackground(), 5));
			add(component);
		}
	}
	
	public JTextArea getNameTextArea() {
		return nameTextArea;
	}
	
	public JTextArea getIpTextArea() {
		return ipTextArea;
	}
}
