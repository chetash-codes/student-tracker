package starter;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import util.DBConnector;

public class AppLauncher {

	public static void main(String[] args) {
		// Check DB connectivity before launching UI
		try {
			DBConnector.getConnection().close(); // test & close immediately
			SwingUtilities.invokeLater(() -> new ui.LoginFrame().setVisible(true));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to connect to the database. \nPlease check your DB settings and ensure MySQL is running.", 
					"Startup Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
