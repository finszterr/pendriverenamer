package main;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {
	static String defaultPath = "C:\\Users\\";
	static String username = System.getProperty("user.name");
	static JFileChooser chChange = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

	public static void main(String[] args) {
		JFrame f = new JFrame("Flash drive renamer");
		JTextField textField;

		// Set native look
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		} catch (InstantiationException e3) {
			e3.printStackTrace();
		} catch (IllegalAccessException e3) {
			e3.printStackTrace();
		} catch (UnsupportedLookAndFeelException e3) {
			e3.printStackTrace();
		}

		// Frame settings
		f.setSize(350, 200);
		f.setLocationRelativeTo(null);
		f.getContentPane().setLayout(null);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// GUI-s
		JButton selectDeviceButton = new JButton("Select device");
		selectDeviceButton.setBounds(84, 11, 140, 23);
		f.getContentPane().add(selectDeviceButton);

		JLabel destinationPathLabel = new JLabel("...");
		destinationPathLabel.setBounds(10, 35, 214, 14);
		f.getContentPane().add(destinationPathLabel);

		JLabel nameLabel = new JLabel("New name of the flash drive:");
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		nameLabel.setBounds(67, 60, 190, 14);
		f.getContentPane().add(nameLabel);

		textField = new JTextField();
		textField.setBounds(10, 85, 314, 20);
		f.getContentPane().add(textField);
		textField.setColumns(10);

		JButton setNameButton = new JButton("Set");
		setNameButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		setNameButton.setBounds(84, 116, 140, 23);
		f.getContentPane().add(setNameButton);

		// Select the USB device (and set it's path)
		selectDeviceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chChange.setDialogTitle("Select destination folder");
				chChange.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (chChange.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					System.out.println(chChange.getCurrentDirectory());
					System.out.println(chChange.getSelectedFile());

					destinationPathLabel.setText(chChange.getSelectedFile() + "");

				} else {
					System.out.println("No Selection ");
				}
			}
		});

		// Set the new name of the device
		setNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Renamer.rename(destinationPathLabel, textField);
			}
		});

		f.setVisible(true);

	}

}
