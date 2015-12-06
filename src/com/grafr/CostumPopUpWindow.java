package com.grafr;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CostumPopUpWindow {

    public int show(String imageLink) {
        Object[] options = { "Ok", "Copy", "Cancel" };

        JPanel panel = new JPanel(new GridLayout(2, 1));
        GridBagConstraints g = new GridBagConstraints();
        g.gridy = 0;
        g.gridx = 0;
        panel.add(new JLabel("Your image has been uploaded!" + System.lineSeparator()), g);
        JTextField textField = new JTextField(imageLink, 20);
        g.gridy++;
        panel.add(textField, g);

        int c = JOptionPane.showOptionDialog(Grafr.graph, panel, null,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, null);
        return c;
    }
}
