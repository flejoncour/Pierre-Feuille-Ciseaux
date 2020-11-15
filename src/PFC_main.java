import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class PFC_main {
	public static void main(String[] args) throws InterruptedException, UnsupportedLookAndFeelException, IOException {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Application essai = new Application();
	}
}
