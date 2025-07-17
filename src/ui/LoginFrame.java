package ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import service.LoginService;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	
	public LoginFrame() {
		setTitle("Faculty Login");
		setSize(300, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel(new GridLayout(3, 2));
		panel.add(new JLabel("Username:"));
		usernameField = new JTextField();
		panel.add(usernameField);
		
		panel.add(new JLabel("Password:"));
		passwordField = new JPasswordField();
		panel.add(passwordField);
		
		loginButton = new JButton("Login");
		panel.add(new JLabel()); // filler cell
		panel.add(loginButton);
		
		add(panel);
		setupEvents();
	}
	
	private void setupEvents() {
		loginButton.addActionListener(e -> {
			String user = usernameField.getText();
			String pass = new String(passwordField.getPassword());
			
			LoginService loginService = new LoginService();
			String role = loginService.getUserRole(user, pass);
			
			if (role != null) {
				JOptionPane.showMessageDialog(this, "Login successful as " + role);
				dispose(); // close login window
				new MainFrame(role).setVisible(true); // launch main app
			} else {
				JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}
