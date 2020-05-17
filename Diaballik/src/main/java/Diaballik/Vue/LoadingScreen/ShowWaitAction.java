package Diaballik.Vue.LoadingScreen;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.*;
import javax.swing.SwingWorker;

public class ShowWaitAction extends AbstractAction {
    protected static final long SLEEP_TIME = 3 * 1000;

    public ShowWaitAction(String name) {
        super(name);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                // mimic some long-running process here...
                Thread.sleep(SLEEP_TIME);
                return null;
            }
        };

        Window win = SwingUtilities.getWindowAncestor((AbstractButton) evt.getSource());
        final JDialog dialog = new JDialog(win, "Dialog", ModalityType.APPLICATION_MODAL);

        mySwingWorker.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                    if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                        dialog.dispose();
                    }
                }
            }
        });
        mySwingWorker.execute();

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(new JLabel("Please wait......."), BorderLayout.PAGE_START);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(win);
        dialog.setVisible(true);
    }
}