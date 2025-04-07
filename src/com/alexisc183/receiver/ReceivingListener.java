package com.alexisc183.receiver;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import com.alexisc183.shared.JLoadingMessage;
import com.alexisc183.shared.JLoadingMessage.LoadingStyle;
import com.alexisc183.shared.MainButton;
import com.alexisc183.shared.MainPanel;

public class ReceivingListener implements ActionListener {
	private MainPanel mainPanel;
	private MainButton[] buttons;
	private JLoadingMessage loadingMessage;
	
	public ReceivingListener(MainPanel mainPanel, MainButton... buttons) {
		this.mainPanel = mainPanel;
		this.buttons = buttons;
		
		loadingMessage = new JLoadingMessage("Please wait", buttons[1], buttons[1].getText());
		
		loadingMessage.setLoadingStyle(LoadingStyle.ROTARY_LINE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setDialogTitle("Where to receive - Mulficaster");
		fileChooser.setApproveButtonToolTipText("Choose destination folder");
		fileChooser.setApproveButtonText("Choose");
		
		if (fileChooser.showOpenDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
			startWait();
			
			Thread receiveFileThread = new Thread(new RunnableFileReceiver(fileChooser, buttons, loadingMessage, mainPanel));
			
			receiveFileThread.start();
		}
	}
	
	private void startWait() {
		for (MainButton button : buttons) {
			button.setEnabled(false);
		}
		buttons[1].setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		loadingMessage.start();
	}
}
