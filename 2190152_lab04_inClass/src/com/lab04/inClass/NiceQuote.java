package com.lab04.inClass;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


//import topic4.MenuActionDemo;
//import topic4.MenuActionDemo.LoadAction;

public class NiceQuote extends JFrame implements ActionListener{
	JPanel pan1;
	MyPanel pan2 = new MyPanel();
	BufferedImage background;
	String typedText ="";
	File file;
	String fileName="";
	
	public NiceQuote(String title){
		super.setTitle(title);
		this.setPreferredSize(new Dimension(500,500));
		this.setLayout(new BorderLayout());
		createMenuBar();
		createPanel();
		//PANEL 1
		this.add(pan1,BorderLayout.NORTH);
		JLabel quote = new JLabel();
		quote.setText("Quote:");
		pan1.add(quote,BorderLayout.NORTH);
		JTextField enterText = new JTextField();
		enterText.setPreferredSize(new Dimension(300,30));
		enterText.addKeyListener(
		            new KeyListener(){

		                public void keyPressed(KeyEvent e){
		                }

						@Override
						public void keyTyped(KeyEvent e) {
						}

						@Override
						public void keyReleased(KeyEvent e) {
							// TODO Auto-generated method stub
							JTextField textField = (JTextField) e.getSource();
		                    typedText = textField.getText();
							 pan2.repaint();
							
						}
		            }
		        );
		pan1.add(enterText,BorderLayout.SOUTH);

	}
	
	public void createPanel(){
		pan1 = new JPanel();
		pan1.setPreferredSize(new Dimension(500,50));
		//create MyPanel
		pan2 = new MyPanel();
		pan2.setPreferredSize(new Dimension(500,400));
		
		this.add(pan2,BorderLayout.SOUTH);
		pan2.repaint();
	}
	
	///
	public class MyPanel extends JPanel{
	private BufferedImage image;
	
	public MyPanel(){
	}

	 @Override
     public void paintComponent(Graphics g) {
         super.paintComponent(g);  //THIS LINE WAS ADDED
         g.drawImage(background, 0, 0, null); // see javadoc for more info on the parameters
         g.drawString(typedText, 10, 10);
	 }
	}

	
	public void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem itemNew=new JMenuItem("New");
		menuFile.add(itemNew);
		
		//add load option
		JMenuItem itemLoad = new JMenuItem(new LoadAction());
		menuFile.add(itemLoad);
	
		//add quit option
		JMenuItem itemExit = new JMenuItem("Exit");
		itemExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int choice = JOptionPane.showConfirmDialog(
						NiceQuote.this,
						"Are you sure?",
						"Please confirm",
						JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		menuFile.add(itemExit);
		menuBar.add(menuFile);
		//pan1.add(menuBar,BorderLayout.NORTH);
		this.setJMenuBar(menuBar);
	}
	
	class LoadAction extends AbstractAction{
		public LoadAction(){
			super("Load Background",new ImageIcon("icon_load.png"));
			putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
			putValue(AbstractAction.SHORT_DESCRIPTION,"Load a file");
		}
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(NiceQuote.this);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				file=chooser.getSelectedFile();
				try {
					background = ImageIO.read(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pan2.repaint();
			}
		}			
	}
	
	
	private static void createAndShowGUI() {
	 	NiceQuote frame = new NiceQuote("Hello this is my JFrame Homework");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);       
    }
	
	public static void main(String[] args) {
	       SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	createAndShowGUI();
	            }
	        });
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
