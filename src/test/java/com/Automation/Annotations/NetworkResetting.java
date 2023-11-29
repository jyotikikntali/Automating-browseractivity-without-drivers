package com.Automation.Annotations;

import java.io.IOException;

public class NetworkResetting {
	public static void main(String[] args) {
		 try {
	            // Specify the network reset command for Windows
	            String command = "ipconfig /release && ipconfig /renew";

	            // Create a process builder
	            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);

	            // Redirect output to the console
	            processBuilder.inheritIO();

	            // Start the process
	            Process process = processBuilder.start();

	            // Wait for the process to complete
	            int exitCode = process.waitFor();
	            System.out.println("Network reset completed with exit code: " + exitCode);

	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	}

}
