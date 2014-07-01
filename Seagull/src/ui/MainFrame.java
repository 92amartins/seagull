package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame{
	
	private JPanel panel;
	private JTabbedPane tabbedPane;
	private JLabel logoLabel;
	private JButton btnAbout;
	
	public MainFrame() {
		super("Seagull");
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void create() throws IOException {
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		BufferedImage myPicture = ImageIO.read(new File("img\\seagull-logo.png"));
		logoLabel = new JLabel(new ImageIcon(myPicture));
		logoLabel.setBounds(0, 0, 240, 69);
		panel.add(logoLabel);
		 
		btnAbout = new JButton("About");	
		btnAbout.setBounds(680, 30, 100, 25);
		panel.add(btnAbout);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(5, 80, 780, 480);
		PreProcessingPanel preProcessingPanel = new PreProcessingPanel();
		ClassificationPanel classificationPanel = new ClassificationPanel();
		tabbedPane.addTab("Pre-processing", preProcessingPanel);
		tabbedPane.addTab("Classification", classificationPanel);
		panel.add(tabbedPane);
		
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		try {
			frame.create();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
