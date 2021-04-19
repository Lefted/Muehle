package me.moritz.muehle.core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class SettingsGui {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    SettingsGui window = new SettingsGui();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public SettingsGui() {
	initialize();
	postInitialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setTitle("M\u00FChle Settings");
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(new CardLayout(0, 0));
	
	final JPanel mainPanel = new JPanel();
	frame.getContentPane().add(mainPanel, "mainPanel");
	GridBagLayout gbl_mainPanel = new GridBagLayout();
	gbl_mainPanel.columnWidths = new int[]{125, 0, 125, 0};
	gbl_mainPanel.rowHeights = new int[]{0, 40, 8, 40, 0, 0};
	gbl_mainPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	gbl_mainPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	mainPanel.setLayout(gbl_mainPanel);
	
	final JButton btnLocalMultiplayerPanel = new JButton("Local Multiplayer");
	btnLocalMultiplayerPanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnLocalMultiplayerPanel = new GridBagConstraints();
	gbc_btnLocalMultiplayerPanel.fill = GridBagConstraints.BOTH;
	gbc_btnLocalMultiplayerPanel.insets = new Insets(0, 0, 5, 5);
	gbc_btnLocalMultiplayerPanel.gridx = 1;
	gbc_btnLocalMultiplayerPanel.gridy = 1;
	mainPanel.add(btnLocalMultiplayerPanel, gbc_btnLocalMultiplayerPanel);
	
	final JButton btnOnlineMultiplayerPanel = new JButton("Online Multiplayer");
	btnOnlineMultiplayerPanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnOnlineMultiplayerPanel = new GridBagConstraints();
	gbc_btnOnlineMultiplayerPanel.fill = GridBagConstraints.BOTH;
	gbc_btnOnlineMultiplayerPanel.insets = new Insets(0, 0, 5, 5);
	gbc_btnOnlineMultiplayerPanel.gridx = 1;
	gbc_btnOnlineMultiplayerPanel.gridy = 3;
	mainPanel.add(btnOnlineMultiplayerPanel, gbc_btnOnlineMultiplayerPanel);
    }

    private void postInitialize() {
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
	    e.printStackTrace();
	}
	SwingUtilities.updateComponentTreeUI(frame);
    }

}
