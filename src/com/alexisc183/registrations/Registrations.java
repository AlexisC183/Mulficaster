package com.alexisc183.registrations;

import com.alexisc183.shared.RegistrationReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Registrations {
	private String name, ip;
	private File registeredMachines;
	private ArrayList<String> registeredNames, registeredIps;
	
	public Registrations(String name, String ip) throws IOException {
		this.name = name;
		this.ip = ip;
		registeredMachines = new File("registeredMachines.txt");
		
		if (registeredMachines.exists()) {
			updateRegisteredMachines(indexOfRegisteredMachine());
		}
		else {
			writeRegisteredMachines();
		}
	}
	
	private int indexOfRegisteredMachine() throws IOException {
		RegistrationReader reader = new RegistrationReader(registeredMachines);
		registeredNames = reader.getRegisteredNames();
		registeredIps = reader.getRegisteredIps();
		int registeredMachineIndex = registeredNames.indexOf(name);
		
		return (registeredMachineIndex == -1) ? registeredIps.indexOf(ip) : registeredMachineIndex;
	}
	
	private void writeRegisteredMachines() throws IOException {
		try (
			FileWriter writer = new FileWriter(registeredMachines);
			BufferedWriter bufferedWriter = new BufferedWriter(writer)
		) {
			bufferedWriter.write(name + "&nbsp;" + ip);
		}
	}
	
	private void updateRegisteredMachines(int registeredMachineIndex) throws IOException {
		if (registeredMachineIndex == -1) {
			registeredNames.add(name);
			registeredIps.add(ip);
		}
		else {
			registeredNames.set(registeredMachineIndex, name);
			registeredIps.set(registeredMachineIndex, ip);
		}
		
		registeredMachines.delete();
		
		try (
			FileWriter writer = new FileWriter(registeredMachines);
			BufferedWriter bufferedWriter = new BufferedWriter(writer)
		) {
			final int LAST_INDEX = registeredNames.size() - 1;
			
			for (int i = 0; i <= LAST_INDEX; i++) {
				bufferedWriter.write(registeredNames.get(i) + "&nbsp;" + registeredIps.get(i));
				
				if (i < LAST_INDEX) {
					bufferedWriter.newLine();
				}
			}
		}
	}
}
