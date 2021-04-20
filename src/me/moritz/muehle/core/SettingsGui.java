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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import me.moritz.muehle.models.Color;
import javax.swing.JCheckBox;

public class SettingsGui {

    private JFrame frame;
    private JTextField txtFieldOnlineMultiplayerClientIp;

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
	gbl_localMultiplayerPanel.rowHeights = new int[] { 70, 0, 0, 10, 35, 0 };
	gbl_localMultiplayerPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
	gbl_localMultiplayerPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	localMultiplayerPanel.setLayout(gbl_localMultiplayerPanel);

	final JLabel lblNewLabel = new JLabel("Note:\r\nThe first player has the first move");
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.anchor = GridBagConstraints.SOUTH;
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel.gridx = 1;
	gbc_lblNewLabel.gridy = 0;
	localMultiplayerPanel.add(lblNewLabel, gbc_lblNewLabel);

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
	gbc_lblLocalSecondPlayer.gridy = 2;
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
	gbc_comboLocalSecondPlayerColor.gridy = 2;
	localMultiplayerPanel.add(comboLocalSecondPlayerColor, gbc_comboLocalSecondPlayerColor);

	final JButton btnNewButton = new JButton("Start Game");
	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		// create the arguments for the game
		final String localFirstPlayerColor = comboLocalFirstPlayerColor.getSelectedIndex() == 1 ? "white" : "black";
		final String[] args = { "-localFirstPlayerColor", localFirstPlayerColor };

		// dispose the settings frame
		frame.dispose();
		// run the game according to the settings
		Controller.main(args);
	    }
	});
	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	gbc_btnNewButton.fill = GridBagConstraints.BOTH;
	gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
	gbc_btnNewButton.gridx = 1;
	gbc_btnNewButton.gridy = 4;
	localMultiplayerPanel.add(btnNewButton, gbc_btnNewButton);

	final JPanel onlineMultiplayerPanel = new JPanel();
	frame.getContentPane().add(onlineMultiplayerPanel, "onlineMultiplayerPanel");
	GridBagLayout gbl_onlineMultiplayerPanel = new GridBagLayout();
	gbl_onlineMultiplayerPanel.columnWidths = new int[] { 125, 0, 125, 0 };
	gbl_onlineMultiplayerPanel.rowHeights = new int[] { 0, 40, 8, 40, 0, 0 };
	gbl_onlineMultiplayerPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
	gbl_onlineMultiplayerPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
	onlineMultiplayerPanel.setLayout(gbl_onlineMultiplayerPanel);

	final JButton btnJoinGameclient = new JButton("Join Game (Client)");
	btnJoinGameclient.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "onlineMultiplayerClientPanel");
	    }
	});
	btnJoinGameclient.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnJoinGameclient = new GridBagConstraints();
	gbc_btnJoinGameclient.fill = GridBagConstraints.BOTH;
	gbc_btnJoinGameclient.insets = new Insets(0, 0, 5, 5);
	gbc_btnJoinGameclient.gridx = 1;
	gbc_btnJoinGameclient.gridy = 1;
	onlineMultiplayerPanel.add(btnJoinGameclient, gbc_btnJoinGameclient);

	final JButton btnHostGameserver = new JButton("Host Game (Server)");
	btnHostGameserver.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "onlineMultiplayerServerPanel");
	    }
	});
	btnHostGameserver.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnHostGameserver = new GridBagConstraints();
	gbc_btnHostGameserver.fill = GridBagConstraints.BOTH;
	gbc_btnHostGameserver.insets = new Insets(0, 0, 5, 5);
	gbc_btnHostGameserver.gridx = 1;
	gbc_btnHostGameserver.gridy = 3;
	onlineMultiplayerPanel.add(btnHostGameserver, gbc_btnHostGameserver);

	final JPanel onlineMultiplayerClientPanel = new JPanel();
	frame.getContentPane().add(onlineMultiplayerClientPanel, "onlineMultiplayerClientPanel");
	GridBagLayout gbl_onlineMultiplayerClientPanel = new GridBagLayout();
	gbl_onlineMultiplayerClientPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
	gbl_onlineMultiplayerClientPanel.rowHeights = new int[] { 50, 0, 0, 30, 0, 40, 0, 0 };
	gbl_onlineMultiplayerClientPanel.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
	gbl_onlineMultiplayerClientPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	onlineMultiplayerClientPanel.setLayout(gbl_onlineMultiplayerClientPanel);

	final JLabel lblOnlineMultiplayerClientIp = new JLabel("ip");
	GridBagConstraints gbc_lblOnlineMultiplayerClientIp = new GridBagConstraints();
	gbc_lblOnlineMultiplayerClientIp.anchor = GridBagConstraints.EAST;
	gbc_lblOnlineMultiplayerClientIp.insets = new Insets(0, 0, 5, 5);
	gbc_lblOnlineMultiplayerClientIp.gridx = 1;
	gbc_lblOnlineMultiplayerClientIp.gridy = 1;
	onlineMultiplayerClientPanel.add(lblOnlineMultiplayerClientIp, gbc_lblOnlineMultiplayerClientIp);

	txtFieldOnlineMultiplayerClientIp = new JTextField();
	GridBagConstraints gbc_txtFieldOnlineMultiplayerClientIp = new GridBagConstraints();
	gbc_txtFieldOnlineMultiplayerClientIp.insets = new Insets(0, 0, 5, 5);
	gbc_txtFieldOnlineMultiplayerClientIp.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldOnlineMultiplayerClientIp.gridx = 2;
	gbc_txtFieldOnlineMultiplayerClientIp.gridy = 1;
	onlineMultiplayerClientPanel.add(txtFieldOnlineMultiplayerClientIp, gbc_txtFieldOnlineMultiplayerClientIp);
	txtFieldOnlineMultiplayerClientIp.setColumns(10);

	final JLabel lblOnlineMultiplayerClientPort = new JLabel("port");
	GridBagConstraints gbc_lblOnlineMultiplayerClientPort = new GridBagConstraints();
	gbc_lblOnlineMultiplayerClientPort.anchor = GridBagConstraints.EAST;
	gbc_lblOnlineMultiplayerClientPort.insets = new Insets(0, 0, 5, 5);
	gbc_lblOnlineMultiplayerClientPort.gridx = 1;
	gbc_lblOnlineMultiplayerClientPort.gridy = 2;
	onlineMultiplayerClientPanel.add(lblOnlineMultiplayerClientPort, gbc_lblOnlineMultiplayerClientPort);

	final JSpinner spinnerOnlineMultiplayerClientPort = new JSpinner();
	spinnerOnlineMultiplayerClientPort.setModel(new SpinnerNumberModel(new Integer(1224), new Integer(0), null, new Integer(1)));
	GridBagConstraints gbc_spinnerOnlineMultiplayerClientPort = new GridBagConstraints();
	gbc_spinnerOnlineMultiplayerClientPort.fill = GridBagConstraints.BOTH;
	gbc_spinnerOnlineMultiplayerClientPort.insets = new Insets(0, 0, 5, 5);
	gbc_spinnerOnlineMultiplayerClientPort.gridx = 2;
	gbc_spinnerOnlineMultiplayerClientPort.gridy = 2;
	onlineMultiplayerClientPanel.add(spinnerOnlineMultiplayerClientPort, gbc_spinnerOnlineMultiplayerClientPort);

	final JLabel lblOnlineMultiplayerClientColor = new JLabel("Color");
	GridBagConstraints gbc_lblOnlineMultiplayerClientColor = new GridBagConstraints();
	gbc_lblOnlineMultiplayerClientColor.anchor = GridBagConstraints.EAST;
	gbc_lblOnlineMultiplayerClientColor.insets = new Insets(0, 0, 5, 5);
	gbc_lblOnlineMultiplayerClientColor.gridx = 1;
	gbc_lblOnlineMultiplayerClientColor.gridy = 3;
	onlineMultiplayerClientPanel.add(lblOnlineMultiplayerClientColor, gbc_lblOnlineMultiplayerClientColor);

	final JComboBox comboOnlineMultiplayerClientColor = new JComboBox();
	comboOnlineMultiplayerClientColor.setModel(new DefaultComboBoxModel(Color.values()));
	comboOnlineMultiplayerClientColor.setSelectedIndex(1);
	GridBagConstraints gbc_comboOnlineMultiplayerClientColor = new GridBagConstraints();
	gbc_comboOnlineMultiplayerClientColor.insets = new Insets(0, 0, 5, 5);
	gbc_comboOnlineMultiplayerClientColor.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboOnlineMultiplayerClientColor.gridx = 2;
	gbc_comboOnlineMultiplayerClientColor.gridy = 3;
	onlineMultiplayerClientPanel.add(comboOnlineMultiplayerClientColor, gbc_comboOnlineMultiplayerClientColor);

	final JButton btnOnlineMultiplayerClientConnect = new JButton("Connect");

	final JCheckBox chckbxOnlineMultiplayerClientFirstMove = new JCheckBox("first move");
	chckbxOnlineMultiplayerClientFirstMove.setSelected(true);
	GridBagConstraints gbc_chckbxOnlineMultiplayerClientFirstMove = new GridBagConstraints();
	gbc_chckbxOnlineMultiplayerClientFirstMove.anchor = GridBagConstraints.EAST;
	gbc_chckbxOnlineMultiplayerClientFirstMove.insets = new Insets(0, 0, 5, 5);
	gbc_chckbxOnlineMultiplayerClientFirstMove.gridx = 2;
	gbc_chckbxOnlineMultiplayerClientFirstMove.gridy = 4;
	onlineMultiplayerClientPanel.add(chckbxOnlineMultiplayerClientFirstMove, gbc_chckbxOnlineMultiplayerClientFirstMove);
	btnOnlineMultiplayerClientConnect.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnOnlineMultiplayerClientConnect = new GridBagConstraints();
	gbc_btnOnlineMultiplayerClientConnect.gridwidth = 2;
	gbc_btnOnlineMultiplayerClientConnect.insets = new Insets(0, 0, 5, 5);
	gbc_btnOnlineMultiplayerClientConnect.fill = GridBagConstraints.BOTH;
	gbc_btnOnlineMultiplayerClientConnect.gridx = 1;
	gbc_btnOnlineMultiplayerClientConnect.gridy = 5;
	onlineMultiplayerClientPanel.add(btnOnlineMultiplayerClientConnect, gbc_btnOnlineMultiplayerClientConnect);

	final JPanel onlineMultiplayerServerPanel = new JPanel();
	frame.getContentPane().add(onlineMultiplayerServerPanel, "onlineMultiplayerServerPanel");
	GridBagLayout gbl_onlineMultiplayerServerPanel = new GridBagLayout();
	gbl_onlineMultiplayerServerPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
	gbl_onlineMultiplayerServerPanel.rowHeights = new int[] { 50, 0, 0, 0, 40, 0 };
	gbl_onlineMultiplayerServerPanel.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
	gbl_onlineMultiplayerServerPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	onlineMultiplayerServerPanel.setLayout(gbl_onlineMultiplayerServerPanel);

	final JLabel lblOnlineMultiplayerServerPort = new JLabel("port");
	GridBagConstraints gbc_lblOnlineMultiplayerServerPort = new GridBagConstraints();
	gbc_lblOnlineMultiplayerServerPort.insets = new Insets(0, 0, 5, 5);
	gbc_lblOnlineMultiplayerServerPort.gridx = 1;
	gbc_lblOnlineMultiplayerServerPort.gridy = 1;
	onlineMultiplayerServerPanel.add(lblOnlineMultiplayerServerPort, gbc_lblOnlineMultiplayerServerPort);

	final JSpinner spinnerOnlineMultiplayerServerPort = new JSpinner();
	spinnerOnlineMultiplayerServerPort.setModel(new SpinnerNumberModel(new Integer(1224), new Integer(0), null, new Integer(1)));
	GridBagConstraints gbc_spinnerOnlineMultiplayerServerPort = new GridBagConstraints();
	gbc_spinnerOnlineMultiplayerServerPort.fill = GridBagConstraints.HORIZONTAL;
	gbc_spinnerOnlineMultiplayerServerPort.insets = new Insets(0, 0, 5, 5);
	gbc_spinnerOnlineMultiplayerServerPort.gridx = 2;
	gbc_spinnerOnlineMultiplayerServerPort.gridy = 1;
	onlineMultiplayerServerPanel.add(spinnerOnlineMultiplayerServerPort, gbc_spinnerOnlineMultiplayerServerPort);

	final JLabel lblOnlineMultiplayerServerColor = new JLabel("Color");
	GridBagConstraints gbc_lblOnlineMultiplayerServerColor = new GridBagConstraints();
	gbc_lblOnlineMultiplayerServerColor.anchor = GridBagConstraints.EAST;
	gbc_lblOnlineMultiplayerServerColor.insets = new Insets(0, 0, 5, 5);
	gbc_lblOnlineMultiplayerServerColor.gridx = 1;
	gbc_lblOnlineMultiplayerServerColor.gridy = 2;
	onlineMultiplayerServerPanel.add(lblOnlineMultiplayerServerColor, gbc_lblOnlineMultiplayerServerColor);

	final JComboBox comboOnlineMultiplayerServerColor = new JComboBox();
	comboOnlineMultiplayerServerColor.setModel(new DefaultComboBoxModel(Color.values()));
	GridBagConstraints gbc_comboOnlineMultiplayerServerColor = new GridBagConstraints();
	gbc_comboOnlineMultiplayerServerColor.insets = new Insets(0, 0, 5, 5);
	gbc_comboOnlineMultiplayerServerColor.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboOnlineMultiplayerServerColor.gridx = 2;
	gbc_comboOnlineMultiplayerServerColor.gridy = 2;
	onlineMultiplayerServerPanel.add(comboOnlineMultiplayerServerColor, gbc_comboOnlineMultiplayerServerColor);

	final JCheckBox chckbxOnlineMultiplayerServerFirstMove = new JCheckBox("first move");
	GridBagConstraints gbc_chckbxOnlineMultiplayerServerFirstMove = new GridBagConstraints();
	gbc_chckbxOnlineMultiplayerServerFirstMove.insets = new Insets(0, 0, 5, 5);
	gbc_chckbxOnlineMultiplayerServerFirstMove.anchor = GridBagConstraints.EAST;
	gbc_chckbxOnlineMultiplayerServerFirstMove.gridx = 2;
	gbc_chckbxOnlineMultiplayerServerFirstMove.gridy = 3;
	onlineMultiplayerServerPanel.add(chckbxOnlineMultiplayerServerFirstMove, gbc_chckbxOnlineMultiplayerServerFirstMove);

	final JButton btnNewButton_1 = new JButton("Start Server");
	btnNewButton_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// create arguments
		final String port = String.valueOf(spinnerOnlineMultiplayerServerPort.getValue());
		final Color onlineOwnColor = (Color) comboOnlineMultiplayerServerColor.getSelectedItem();
		final String onlineStartsFirst = chckbxOnlineMultiplayerServerFirstMove.isSelected() ? "-onlineStartsFirst" : "";

		final String[] args = { "-online", "-server", onlineStartsFirst, "-onlineOwnColor", onlineOwnColor.toString() , "-port", port};

		// dispose the settings gui
		frame.dispose();
		// start the game according to the settings
		Controller.main(args);
	    }
	});
	btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
	gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
	gbc_btnNewButton_1.gridwidth = 2;
	gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
	gbc_btnNewButton_1.gridx = 1;
	gbc_btnNewButton_1.gridy = 4;
	onlineMultiplayerServerPanel.add(btnNewButton_1, gbc_btnNewButton_1);

	btnOnlineMultiplayerClientConnect.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// create arguments for the game
		final String ip = txtFieldOnlineMultiplayerClientIp.getText();
		final int port = (int) spinnerOnlineMultiplayerClientPort.getValue();
		final Color ownColor = (Color) comboOnlineMultiplayerClientColor.getSelectedItem();
		final String onlineStartsFirst = chckbxOnlineMultiplayerClientFirstMove.isSelected() ? "-onlineStartsFirst" : "";

		final String[] args = { "-online", "-ip", ip, "-port", String.valueOf(port), "-onlineOwnColor", ownColor.toString(), onlineStartsFirst };

		// dispose the settings frame
		frame.dispose();

		// start the game according to the settings
		Controller.main(args);
	    }
	});
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
