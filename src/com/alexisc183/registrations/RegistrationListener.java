package com.alexisc183.registrations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.alexisc183.registrations.RegistrationValidator.Validation;

public class RegistrationListener implements ActionListener {
	private RegistrationsPanel registrationsPanel;
	private FormPanel formPanel;
	
	public RegistrationListener(RegistrationsPanel registrationsPanel, FormPanel formPanel) {
		this.registrationsPanel = registrationsPanel;
		this.formPanel = formPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = formPanel.getNameTextArea().getText().trim().replace('\n', ' ');
		String ip = formPanel.getIpTextArea().getText().trim().replace('\n', ' ');
		Validation validation = new RegistrationValidator(name, ip).validate();
		
		switch (validation) {
			case EMPTY_NAME:
				JOptionPane.showMessageDialog(registrationsPanel, "Please enter a machine name", "Warning - Mulficaster", JOptionPane.WARNING_MESSAGE);
				formPanel.getNameTextArea().setText("");
				formPanel.getNameTextArea().requestFocus();
				break;
			case EMPTY_IP:
				JOptionPane.showMessageDialog(registrationsPanel, "Please enter an IP address", "Warning - Mulficaster", JOptionPane.WARNING_MESSAGE);
				formPanel.getIpTextArea().setText("");
				formPanel.getIpTextArea().requestFocus();
				break;
			case BAD_IP_FORMAT:
			case IP_WITH_A_VALUE_EXCEEDING_255:
				JOptionPane.showMessageDialog(registrationsPanel, "Please enter a valid IPv4 address", "Warning - Mulficaster", JOptionPane.WARNING_MESSAGE);
				formPanel.getIpTextArea().requestFocus();
				formPanel.getIpTextArea().selectAll();
				break;
			default:
				try {
					new Registrations(name, ip);
					formPanel.getNameTextArea().setText("");
					formPanel.getIpTextArea().setText("");
					formPanel.getNameTextArea().requestFocus();
				}
				catch (IOException exception) {
					JOptionPane.showMessageDialog(registrationsPanel, exception.getMessage(), "Error - Mulficaster", JOptionPane.ERROR_MESSAGE);
				}
				break;
		}
	}
}
