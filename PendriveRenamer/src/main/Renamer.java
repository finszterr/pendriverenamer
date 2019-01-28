package main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Renamer {
	
	public static void rename(JLabel destinationPathLabel, JTextField textField) {
		// Create text file
		File file = new File(destinationPathLabel.getText() + "\\autorun.inf");
		
		// Delete file if it already exist
		try {
			Files.deleteIfExists(file.toPath());
		} catch (IOException e2) {
			System.out.println("File has not been deleted.");
		}

		try {
			if (file.createNewFile()) {
				System.out.println("File has been created!");
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e1) {
			System.out.println("File creation has failed.");
		}

		String nameOfUSB = "USB Drive";
		// Get the name of USB drive
		if (textField.getText() != null) {
			nameOfUSB = textField.getText();
		}

		// Write Content
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.write("[autorun]");
			writer.write("\r\n");
			writer.write("label=" + nameOfUSB);
			writer.close();

			// Messagebox notifies about the sucessfully renaming
			JOptionPane.showMessageDialog(null, "Disconnect your USB drive to see the change.",
					"USB drive has been renamed!", JOptionPane.INFORMATION_MESSAGE);

		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Failed to rename the drive.",
					"Failure", JOptionPane.ERROR_MESSAGE);
			System.out.println("Failed to write content.");
		}
	}

}
