package com.alexisc183.shared;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RegistrationReader {
	private ArrayList<String> registeredNames, registeredIps;
	
	public RegistrationReader(File registeredMachines) throws FileNotFoundException, IOException {
		try (
			FileReader reader = new FileReader(registeredMachines);
			BufferedReader bufferedReader = new BufferedReader(reader)
		) {
			registeredNames = new ArrayList<>();
			registeredIps = new ArrayList<>();
			String readLine = bufferedReader.readLine();
			
			while (readLine != null) {
				int indexOfNbsp = readLine.indexOf("&nbsp;");
				
				if (indexOfNbsp == -1) {
					throw new IOException("Content of " + registeredMachines.getAbsolutePath() + " is malformed. Fix it or delete the file");
				}
				
				registeredNames.add(readLine.substring(0, indexOfNbsp));
				registeredIps.add(readLine.substring(indexOfNbsp + 6));
				readLine = bufferedReader.readLine();
			}
		}
	}
	
	public ArrayList<String> getRegisteredNames() {
		return new ArrayList<>(registeredNames);
	}
	
	public ArrayList<String> getRegisteredIps() {
		return new ArrayList<>(registeredIps);
	}
}
