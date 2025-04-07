package com.alexisc183.receiver;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiver implements Closeable {
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private FileOutputStream fileOutputStream;
	private BufferedOutputStream bufferedOutputStream;
	private boolean isEndOfFileReached;
	
	public FileReceiver(ServerSocket serverSocket, File directory) throws IOException {		
		serverSocket.setSoTimeout(60000);
		
		socket = serverSocket.accept();
		objectInputStream = new ObjectInputStream(socket.getInputStream());
		fileOutputStream = new FileOutputStream(directory.getAbsolutePath() + File.separatorChar + objectInputStream.readUTF());
		bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
	}
	
	public boolean isEndOfFileReached() {
		return isEndOfFileReached;
	}
	
	public void receiveSome(int howManyBytes) throws IOException {
		byte[] bytes = objectInputStream.readNBytes(4000000);
		
		if (bytes.length < howManyBytes) {
			isEndOfFileReached = true;
		}
		
		bufferedOutputStream.write(bytes);
	}
	
	@Override
	public void close() throws IOException {
		bufferedOutputStream.close();
		fileOutputStream.close();
		objectInputStream.close();
		socket.close();
	}
}
