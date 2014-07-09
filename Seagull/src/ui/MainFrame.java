package ui;

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

import control.FrameListener;

public class MainFrame extends JFrame{
	
	private JPanel panel;
	private JTabbedPane tabbedPane;
	private JLabel logoLabel;
	private JButton btnAbout;
	
	private PreProcessingPanel preProcessingPanel;
	private ClassificationPanel classificationPanel;
	
	private FrameListener frameListener = new FrameListener(this);
	
	public MainFrame() {
		super("Seagull");
		
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void create() throws IOException {
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		setIconImage(ImageIO.read(new File("img/seagull-icon.png")));
		
		BufferedImage logo = ImageIO.read(new File("img/seagull-logo2.png"));
		logoLabel = new JLabel(new ImageIcon(logo));
		logoLabel.setBounds(0, 0, 700, 80);
		panel.add(logoLabel);
		 
		btnAbout = new JButton("About");	
		btnAbout.setBounds(680, 30, 100, 25);
		panel.add(btnAbout);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(5, 80, 790, 490);
		preProcessingPanel = new PreProcessingPanel();
		classificationPanel = new ClassificationPanel();
		tabbedPane.addTab("Pre-processing", preProcessingPanel);
		tabbedPane.addTab("Classification", classificationPanel);
		panel.add(tabbedPane);
		
		addListeners();
		
		setResizable(false);
		setVisible(true);
	}
	
	private void addListeners() {
		btnAbout.addActionListener(frameListener);
		
		preProcessingPanel.getBtnBrowse().addActionListener(frameListener);
		preProcessingPanel.getBtnProcess().addActionListener(frameListener);
		preProcessingPanel.getBtnClassify().addActionListener(frameListener);
		
		classificationPanel.getBtnBrowse().addActionListener(frameListener);
		classificationPanel.getBtnProcess().addActionListener(frameListener);
		classificationPanel.getBtnSave().addActionListener(frameListener);
		classificationPanel.getBtnChart().addActionListener(frameListener);
	}
	
	/*ActionListener listenerBtnAbout = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = "Tool done as a research project by Sciece without Borders students in University of Brighton. \n"
					+ "Students: \n"
					+ "- Andrei Martins Silva \n"
					+ "- Camilla Maciel Quitério de Oliveira \n"
					+ "- Humberto Politi de Oliveira \n"
					+ "Project Supervisor: Dr Gulden Uchyigit";
			JOptionPane.showMessageDialog(MainFrame.this, msg, "About Seagull Tool", JOptionPane.INFORMATION_MESSAGE);
		}
	};*/
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		try {
			frame.create();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JButton getBtnAbout() {
		return btnAbout;
	}

	public void setBtnAbout(JButton btnAbout) {
		this.btnAbout = btnAbout;
	}

	public PreProcessingPanel getPreProcessingPanel() {
		return preProcessingPanel;
	}

	public void setPreProcessingPanel(PreProcessingPanel preProcessingPanel) {
		this.preProcessingPanel = preProcessingPanel;
	}

	public ClassificationPanel getClassificationPanel() {
		return classificationPanel;
	}

	public void setClassificationPanel(ClassificationPanel classificationPanel) {
		this.classificationPanel = classificationPanel;
	}
	
}
