package com.alexisc183.sender;

import com.alexisc183.shared.MainButton;
import com.alexisc183.shared.RegistrationReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SenderPanel extends JPanel {
	private JList<String> machineList;
	private MainButton send;
	
	public SenderPanel() {
		setLayout(new BorderLayout());
		
		setComponents();
	}
	
	private void setComponents() {
		JLabel instructions = new JLabel("<html>Send file to...</html>");
		machineList = new JList<>();
		send = new MainButton("Send", new Color(255, 192, 192));
		
		instructions.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		instructions.setForeground(Color.BLACK);
		instructions.setBorder(new LineBorder(getBackground(), 5));
		machineList.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		machineList.setForeground(Color.BLACK);
		send.setEnabled(false);
		
		JScrollPane machineListScrollPane = new JScrollPane(machineList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		machineListScrollPane.setBorder(new LineBorder(getBackground(), 5));
		
		machineList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!machineList.getValueIsAdjusting()) {
					send.setEnabled(true);
				}
			}
		});
				
		add(instructions, BorderLayout.NORTH);
		add(machineListScrollPane, BorderLayout.CENTER);
		add(send, BorderLayout.SOUTH);
		
		try {
			fillMachineList();
		}
		catch (FileNotFoundException e) {
			instructions.setText("No registered machines");
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error - Mulficaster", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void fillMachineList() throws FileNotFoundException, IOException {
		RegistrationReader reader = new RegistrationReader(new File("registeredMachines.txt"));
		String[] sortedNames = reader.getRegisteredNames().toArray(new String[0]);
			
		Arrays.sort(sortedNames, String.CASE_INSENSITIVE_ORDER);
			
		machineList.setListData(sortedNames);
			
		send.addActionListener(new SendingListener(this, reader, machineList));
	}
}
