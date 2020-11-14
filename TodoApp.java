import javax.swing.SwingUtilities;

import view.AppFrame;

public class TodoApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CriarMostrarGUI();
            }
        });
    }

    private static void CriarMostrarGUI() {

        AppFrame frame = new AppFrame();

        frame.mostrar();

    }
}