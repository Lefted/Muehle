package me.moritz.muehle.core;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.moritz.muehle.models.Color;

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
	gbl_mainPanel.columnWidths = new int[] { 125, 0, 125, 0 };
	gbl_mainPanel.rowHeights = new int[] { 0, 40, 8, 40, 0, 0 };
	gbl_mainPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
	gbl_mainPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
	mainPanel.setLayout(gbl_mainPanel);

	final JButton btnLocalMultiplayerPanel = new JButton("Local Multiplayer");
	btnLocalMultiplayerPanel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "localMultiplayerPanel");
	    }
	});
	btnLocalMultiplayerPanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnLocalMultiplayerPanel = new GridBagConstraints();
	gbc_btnLocalMultiplayerPanel.fill = GridBagConstraints.BOTH;
	gbc_btnLocalMultiplayerPanel.insets = new Insets(0, 0, 5, 5);
	gbc_btnLocalMultiplayerPanel.gridx = 1;
	gbc_btnLocalMultiplayerPanel.gridy = 1;
	mainPanel.add(btnLocalMultiplayerPanel, gbc_btnLocalMultiplayerPanel);

	final JButton btnOnlineMultiplayerPanel = new JButton("Online Multiplayer");
	btnOnlineMultiplayerPanel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    ((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "onlineMultiplayerPanel");
		}
	});
	btnOnlineMultiplayerPanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnOnlineMultiplayerPanel = new GridBagConstraints();
	gbc_btnOnlineMultiplayerPanel.fill = GridBagConstraints.BOTH;
	gbc_btnOnlineMultiplayerPanel.insets = new Insets(0, 0, 5, 5);
	gbc_btnOnlineMultiplayerPanel.gridx = 1;
	gbc_btnOnlineMultiplayerPanel.gridy = 3;
	mainPanel.add(btnOnlineMultiplayerPanel, gbc_btnOnlineMultiplayerPanel);

	final JPanel localMultiplayerPanel = new JPanel();
	frame.getContentPane().add(localMultiplayerPanel, "localMultiplayerPanel");
	GridBagLayout gbl_localMultiplayerPanel = new GridBagLayout();
	gbl_localMultiplayerPanel.columnWidths = new int[] { 150, 172, 0, 0 };
	gbl_localMultiplayerPanel.rowHeights = new int[] { 70, 0, 0, 0, 10, 35, 0 };
	gbl_localMultiplayerPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
	gbl_localMultiplayerPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	localMultiplayerPanel.setLayout(gbl_localMultiplayerPanel);

	final JLabel lblLocalFirstPlayer = new JLabel("First player");
	GridBagConstraints gbc_lblLocalFirstPlayer = new GridBagConstraints();
	gbc_lblLocalFirstPlayer.anchor = GridBagConstraints.EAST;
	gbc_lblLocalFirstPlayer.insets = new Insets(0, 0, 5, 5);
	gbc_lblLocalFirstPlayer.gridx = 0;
	gbc_lblLocalFirstPlayer.gridy = 1;
	localMultiplayerPanel.add(lblLocalFirstPlayer, gbc_lblLocalFirstPlayer);


	final JComboBox comboLocalFirstPlayerColor = new JComboBox();
	final JComboBox comboLocalSecondPlayerColor = new JComboBox();
	comboLocalFirstPlayerColor.setModel(new DefaultComboBoxModel(Color.values()));
	comboLocalFirstPlayerColor.addActionListener((actionEvent) -> {
	    final int otherIndex = comboLocalFirstPlayerColor.getSelectedIndex() == 0 ? 1 : 0;
	    comboLocalSecondPlayerColor.setSelectedIndex(otherIndex);
	});
	
	GridBagConstraints gbc_comboLocalFirstPlayerColor = new GridBagConstraints();
	gbc_comboLocalFirstPlayerColor.insets = new Insets(0, 0, 5, 5);
	gbc_comboLocalFirstPlayerColor.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboLocalFirstPlayerColor.gridx = 1;
	gbc_comboLocalFirstPlayerColor.gridy = 1;
	localMultiplayerPanel.add(comboLocalFirstPlayerColor, gbc_comboLocalFirstPlayerColor);

	final JLabel lblLocalSecondPlayer = new JLabel("Second player");
	GridBagConstraints gbc_lblLocalSecondPlayer = new GridBagConstraints();
	gbc_lblLocalSecondPlayer.anchor = GridBagConstraints.EAST;
	gbc_lblLocalSecondPlayer.insets = new Insets(0, 0, 5, 5);
	gbc_lblLocalSecondPlayer.gridx = 0;
	gbc_lblLocalSecondPlayer.gridy = 3;
	localMultiplayerPanel.add(lblLocalSecondPlayer, gbc_lblLocalSecondPlayer);

	comboLocalSecondPlayerColor.setModel(new DefaultComboBoxModel(Color.values()));
	comboLocalSecondPlayerColor.setSelectedIndex(1);
	comboLocalSecondPlayerColor.addActionListener((actionEvent) -> {
	    final int otherIndex = comboLocalSecondPlayerColor.getSelectedIndex() == 0 ? 1 : 0;
	    comboLocalFirstPlayerColor.setSelectedIndex(otherIndex);
	});
	GridBagConstraints gbc_comboLocalSecondPlayerColor = new GridBagConstraints();
	gbc_comboLocalSecondPlayerColor.insets = new Insets(0, 0, 5, 5);
	gbc_comboLocalSecondPlayerColor.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboLocalSecondPlayerColor.gridx = 1;
	gbc_comboLocalSecondPlayerColor.gridy = 3;
	localMultiplayerPanel.add(comboLocalSecondPlayerColor, gbc_comboLocalSecondPlayerColor);

	final JButton btnNewButton = new JButton("Start Game");
	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	gbc_btnNewButton.fill = GridBagConstraints.BOTH;
	gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
	gbc_btnNewButton.gridx = 1;
	gbc_btnNewButton.gridy = 5;
	localMultiplayerPanel.add(btnNewButton, gbc_btnNewButton);
	
	final JPanel onlineMultiplayerPanel = new JPanel();
	frame.getContentPane().add(onlineMultiplayerPanel, "onlineMultiplayerPanel");
	GridBagLayout gbl_onlineMultiplayerPanel = new GridBagLayout();
	gbl_onlineMultiplayerPanel.columnWidths = new int[]{125, 0, 125, 0};
	gbl_onlineMultiplayerPanel.rowHeights = new int[]{0, 40, 8, 40, 0, 0};
	gbl_onlineMultiplayerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	gbl_onlineMultiplayerPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	onlineMultiplayerPanel.setLayout(gbl_onlineMultiplayerPanel);
	
	final JButton btnJoinGameclient = new JButton("Join Game (Client)");
	btnJoinGameclient.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnJoinGameclient = new GridBagConstraints();
	gbc_btnJoinGameclient.fill = GridBagConstraints.BOTH;
	gbc_btnJoinGameclient.insets = new Insets(0, 0, 5, 5);
	gbc_btnJoinGameclient.gridx = 1;
	gbc_btnJoinGameclient.gridy = 1;
	onlineMultiplayerPanel.add(btnJoinGameclient, gbc_btnJoinGameclient);
	
	final JButton btnHostGameserver = new JButton("Host Game (Server)");
	btnHostGameserver.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnHostGameserver = new GridBagConstraints();
	gbc_btnHostGameserver.fill = GridBagConstraints.BOTH;
	gbc_btnHostGameserver.insets = new Insets(0, 0, 5, 5);
	gbc_btnHostGameserver.gridx = 1;
	gbc_btnHostGameserver.gridy = 3;
	onlineMultiplayerPanel.add(btnHostGameserver, gbc_btnHostGameserver);
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
