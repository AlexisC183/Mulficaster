package com.alexisc183.sender;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FileSender {
	private File file;
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private FileInputStream fileInputStream;
	private BufferedInputStream bufferedInputStream;
	private boolean isEndOfFileReached;
	
	public FileSender(File file, String destinationIpv4) throws IOException {
		this.file = file;
		socket = new Socket(destinationIpv4, 9999);
		objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		fileInputStream = new FileInputStream(file);
		bufferedInputStream = new BufferedInputStream(fileInputStream);
	}
	
	public boolean isEndOfFileReached() {
		return isEndOfFileReached;
	}
	
	public void sendFileName() throws IOException {
		objectOutputStream.writeUTF(file.getName());
	}
	
	public void sendSome(int howManyBytes) throws IOException {
		byte[] bytes = bufferedInputStream.readNBytes(howManyBytes);
		
		if (bytes.length < howManyBytes) {
			isEndOfFileReached = true;
		}
		
		objectOutputStream.write(bytes);
	}
	
	public void close() throws IOException {
		bufferedInputStream.close();
		fileInputStream.close();
		objectOutputStream.close();
		socket.close();
	}
}
