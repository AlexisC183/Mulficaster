package com.alexisc183.receiver;

import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.alexisc183.shared.JLoadingMessage;
import com.alexisc183.shared.MainButton;
import com.alexisc183.shared.MainPanel;

public class RunnableFileReceiver implements Runnable {	
	private JFileChooser fileChooser;
	private MainButton[] buttons;
	private JLoadingMessage loadingMessage;
	private MainPanel mainPanel;
	private ServerSocket serverSocket;
	
	public RunnableFileReceiver(JFileChooser fileChooser, MainButton[] buttons, JLoadingMessage loadingMessage, MainPanel mainPanel) {
		this.fileChooser = fileChooser;
		this.buttons = buttons;
		this.loadingMessage = loadingMessage;
		this.mainPanel = mainPanel;
	}
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(9999);
			
			receiveFile();
			endWait();
		}
		catch (IOException e) {
			endWait();
			JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "Error - Mulficaster", JOptionPane.ERROR_MESSAGE);
		}
		
		if (serverSocket != null) {
			try {
				serverSocket.close();
			}
			catch (IOException e) {
				JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "Error - Mulficaster", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void receiveFile() throws IOException {
		try (FileReceiver receiver = new FileReceiver(serverSocket, fileChooser.getSelectedFile())) {
			while (!receiver.isEndOfFileReached()) {
				receiver.receiveSome(4000000);
			}
		}
	}
	
	private void endWait() {
		for (MainButton button : buttons) {
			button.setEnabled(true);
		}
		buttons[1].setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		loadingMessage.stop();
	}
}
