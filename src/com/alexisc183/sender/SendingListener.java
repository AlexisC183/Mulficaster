package com.alexisc183.sender;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import com.alexisc183.shared.RegistrationReader;

public class SendingListener implements ActionListener {
	private SenderPanel senderPanel;
	private RegistrationReader reader;
	private JList<String> machineList;
	
	public SendingListener(SenderPanel senderPanel, RegistrationReader reader, JList<String> machineList) {
		this.senderPanel = senderPanel;
		this.reader = reader;
		this.machineList = machineList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setDialogTitle("Open - Mulficaster");
		
		if (fileChooser.showOpenDialog(senderPanel) == JFileChooser.APPROVE_OPTION) {
			try {
				final String DESTINATION_IPV4 = reader.getRegisteredIps().get(reader.getRegisteredNames().indexOf(machineList.getSelectedValue()));
				FileSender sender = new FileSender(fileChooser.getSelectedFile(), DESTINATION_IPV4);
				
				sender.sendFileName();
				
				while (!sender.isEndOfFileReached()) {
					sender.sendSome(4000000);
				}
				
				sender.close();
			}
			catch (IOException exception) {
				JOptionPane.showMessageDialog(senderPanel, exception.getMessage(), "Error - Mulficaster", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}