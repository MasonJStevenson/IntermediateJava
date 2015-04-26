package edu.miracosta.cs113.FinalPjt;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SudokuSolver extends JFrame
{

	private JPanel contentPane;
	private JPanel panel;
	private SudokuSolver thisSolver = this;
	private JFrame thisFrame = this;
	private LinkedList<JButton> buttonList = new LinkedList<JButton>();
	
	JFileChooser fc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
	    //create a new frame and display it.
		SudokuSolver frame = new SudokuSolver();
		frame.setVisible(true);	      
		
	}

	/**
	 * Create the frame.
	 */
	public SudokuSolver() 
	{
		setResizable(false);
		setTitle("Sudoku Solver");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 750);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				SudokuSolver newSolver = new SudokuSolver();
				newSolver.setVisible(true);
				
			}
			
		});
		mnNewMenu.add(mntmNew);
		
		fc = new JFileChooser();
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(thisFrame);
				 
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                InputStream inputStream = null;
	                ObjectInput objectInput = null;
	                
	                try 
	                {
	                	inputStream = new FileInputStream(file.getAbsolutePath());
						objectInput = new ObjectInputStream (inputStream);
						
						int[][] puzzle = (int[][]) objectInput.readObject();
						setPuzzle(puzzle);
					} 
	                
	                catch (IOException e) 
	                {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	                catch (ClassNotFoundException e)
	                {
	                    e.printStackTrace();
	                }
	                
	                finally
	                {
	                	try 
	                	{
							inputStream.close();
							objectInput.close();
						} 
	                	
	                	catch (IOException e) 
	                	{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	
	                }
	            } 
	            
	            else {
	            }
			}
			
		});
		mnNewMenu.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showSaveDialog(thisFrame);
				//This is where a real application would save the file.
	            if (returnVal == JFileChooser.APPROVE_OPTION) 
	            {
	                File file = fc.getSelectedFile();
	                String path = file.getAbsolutePath();
	                String ext = ".SU";
	                file = new File(path + ext);
	                
	                FileOutputStream fos = null;
					OutputStream buffer = null;
				    ObjectOutput output = null;
	                
	                try 
	                {
	                	file.createNewFile();
						fos = new FileOutputStream(file.getAbsolutePath());
					    output = new ObjectOutputStream(fos);
						
						output.writeObject(getPuzzle());
						JOptionPane.showMessageDialog(thisFrame, "file created");
					} 
	                
	                catch (FileNotFoundException e) 
	                {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	                catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	                finally
	                {
	                	try 
	                	{
							fos.close();
							output.close();
						} 
	                	
	                	catch (IOException e) 
	                	{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	
	                }
	            } 
	            
	            else {
	            }
			}		
		});
		mnNewMenu.add(mntmSave);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(5, 11, 784, 647);
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(2, 0, 84, 68);
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_1.setBackground(Color.WHITE);
		panel.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setBounds(89, 0, 84, 68);
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_2.setBackground(Color.WHITE);
		panel.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(176, 0, 84, 68);
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_3.setBackground(Color.WHITE);
		panel.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setBounds(263, 0, 84, 68);
		button_4.setForeground(Color.BLACK);
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_4.setBackground(Color.LIGHT_GRAY);
		panel.add(button_4);
		
		JButton button_5 = new JButton("");
		button_5.setBounds(350, 0, 84, 68);
		button_5.setForeground(Color.BLACK);
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_5.setBackground(Color.LIGHT_GRAY);
		panel.add(button_5);
		
		JButton button_6 = new JButton("");
		button_6.setBounds(437, 0, 84, 68);
		button_6.setForeground(Color.BLACK);
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_6.setBackground(Color.LIGHT_GRAY);
		panel.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.setBounds(524, 0, 84, 68);
		button_7.setForeground(Color.BLACK);
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_7.setBackground(Color.WHITE);
		panel.add(button_7);
		
		JButton button_8 = new JButton("");
		button_8.setBounds(611, 0, 84, 68);
		button_8.setForeground(Color.BLACK);
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_8.setBackground(Color.WHITE);
		panel.add(button_8);
		
		JButton button_9 = new JButton("");
		button_9.setBounds(698, 0, 84, 68);
		button_9.setForeground(Color.BLACK);
		button_9.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_9.setBackground(Color.WHITE);
		panel.add(button_9);
		
		JButton button_10 = new JButton("");
		button_10.setBounds(2, 71, 84, 68);
		button_10.setForeground(Color.BLACK);
		button_10.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_10.setBackground(Color.WHITE);
		panel.add(button_10);
		
		JButton button_11 = new JButton("");
		button_11.setBounds(89, 71, 84, 68);
		button_11.setForeground(Color.BLACK);
		button_11.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_11.setBackground(Color.WHITE);
		panel.add(button_11);
		
		JButton button_12 = new JButton("");
		button_12.setBounds(176, 71, 84, 68);
		button_12.setForeground(Color.BLACK);
		button_12.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_12.setBackground(Color.WHITE);
		panel.add(button_12);
		
		JButton button_13 = new JButton("");
		button_13.setBounds(263, 71, 84, 68);
		button_13.setForeground(Color.BLACK);
		button_13.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_13.setBackground(Color.LIGHT_GRAY);
		panel.add(button_13);
		
		JButton button_14 = new JButton("");
		button_14.setBounds(350, 71, 84, 68);
		button_14.setForeground(Color.BLACK);
		button_14.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_14.setBackground(Color.LIGHT_GRAY);
		panel.add(button_14);
		
		JButton button_15 = new JButton("");
		button_15.setBounds(437, 71, 84, 68);
		button_15.setForeground(Color.BLACK);
		button_15.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_15.setBackground(Color.LIGHT_GRAY);
		panel.add(button_15);
		
		JButton button_16 = new JButton("");
		button_16.setBounds(524, 71, 84, 68);
		button_16.setForeground(Color.BLACK);
		button_16.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_16.setBackground(Color.WHITE);
		panel.add(button_16);
		
		JButton button_17 = new JButton("");
		button_17.setBounds(611, 71, 84, 68);
		button_17.setForeground(Color.BLACK);
		button_17.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_17.setBackground(Color.WHITE);
		panel.add(button_17);
		
		JButton button_18 = new JButton("");
		button_18.setBounds(698, 71, 84, 68);
		button_18.setForeground(Color.BLACK);
		button_18.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_18.setBackground(Color.WHITE);
		panel.add(button_18);
		
		JButton button_19 = new JButton("");
		button_19.setBounds(2, 142, 84, 68);
		button_19.setForeground(Color.BLACK);
		button_19.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_19.setBackground(Color.WHITE);
		panel.add(button_19);
		
		JButton button_20 = new JButton("");
		button_20.setBounds(89, 142, 84, 68);
		button_20.setForeground(Color.BLACK);
		button_20.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_20.setBackground(Color.WHITE);
		panel.add(button_20);
		
		JButton button_21 = new JButton("");
		button_21.setBounds(176, 142, 84, 68);
		button_21.setForeground(Color.BLACK);
		button_21.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_21.setBackground(Color.WHITE);
		panel.add(button_21);
		
		JButton button_22 = new JButton("");
		button_22.setBounds(263, 142, 84, 68);
		button_22.setForeground(Color.BLACK);
		button_22.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_22.setBackground(Color.LIGHT_GRAY);
		panel.add(button_22);
		
		JButton button_23 = new JButton("");
		button_23.setBounds(350, 142, 84, 68);
		button_23.setForeground(Color.BLACK);
		button_23.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_23.setBackground(Color.LIGHT_GRAY);
		panel.add(button_23);
		
		JButton button_24 = new JButton("");
		button_24.setBounds(437, 142, 84, 68);
		button_24.setForeground(Color.BLACK);
		button_24.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_24.setBackground(Color.LIGHT_GRAY);
		panel.add(button_24);
		
		JButton button_25 = new JButton("");
		button_25.setBounds(524, 142, 84, 68);
		button_25.setForeground(Color.BLACK);
		button_25.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_25.setBackground(Color.WHITE);
		panel.add(button_25);
		
		JButton button_26 = new JButton("");
		button_26.setBounds(611, 142, 84, 68);
		button_26.setForeground(Color.BLACK);
		button_26.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_26.setBackground(Color.WHITE);
		panel.add(button_26);
		
		JButton button_27 = new JButton("");
		button_27.setBounds(698, 142, 84, 68);
		button_27.setForeground(Color.BLACK);
		button_27.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_27.setBackground(Color.WHITE);
		panel.add(button_27);
		
		JButton button_28 = new JButton("");
		button_28.setBounds(2, 213, 84, 68);
		button_28.setForeground(Color.BLACK);
		button_28.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_28.setBackground(Color.LIGHT_GRAY);
		panel.add(button_28);
		
		JButton button_29 = new JButton("");
		button_29.setBounds(89, 213, 84, 68);
		button_29.setForeground(Color.BLACK);
		button_29.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_29.setBackground(Color.LIGHT_GRAY);
		panel.add(button_29);
		
		JButton button_30 = new JButton("");
		button_30.setBounds(176, 213, 84, 68);
		button_30.setForeground(Color.BLACK);
		button_30.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_30.setBackground(Color.LIGHT_GRAY);
		panel.add(button_30);
		
		JButton button_31 = new JButton("");
		button_31.setBounds(263, 213, 84, 68);
		button_31.setForeground(Color.BLACK);
		button_31.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_31.setBackground(Color.WHITE);
		panel.add(button_31);
		
		JButton button_32 = new JButton("");
		button_32.setBounds(350, 213, 84, 68);
		button_32.setForeground(Color.BLACK);
		button_32.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_32.setBackground(Color.WHITE);
		panel.add(button_32);
		
		JButton button_33 = new JButton("");
		button_33.setBounds(437, 213, 84, 68);
		button_33.setForeground(Color.BLACK);
		button_33.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_33.setBackground(Color.WHITE);
		panel.add(button_33);
		
		JButton button_34 = new JButton("");
		button_34.setBounds(524, 213, 84, 68);
		button_34.setForeground(Color.BLACK);
		button_34.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_34.setBackground(Color.LIGHT_GRAY);
		panel.add(button_34);
		
		JButton button_35 = new JButton("");
		button_35.setBounds(611, 213, 84, 68);
		button_35.setForeground(Color.BLACK);
		button_35.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_35.setBackground(Color.LIGHT_GRAY);
		panel.add(button_35);
		
		JButton button_36 = new JButton("");
		button_36.setBounds(698, 213, 84, 68);
		button_36.setForeground(Color.BLACK);
		button_36.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_36.setBackground(Color.LIGHT_GRAY);
		panel.add(button_36);
		
		JButton button_37 = new JButton("");
		button_37.setBounds(2, 284, 84, 68);
		button_37.setForeground(Color.BLACK);
		button_37.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_37.setBackground(Color.LIGHT_GRAY);
		panel.add(button_37);
		
		JButton button_38 = new JButton("");
		button_38.setBounds(89, 284, 84, 68);
		button_38.setForeground(Color.BLACK);
		button_38.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_38.setBackground(Color.LIGHT_GRAY);
		panel.add(button_38);
		
		JButton button_39 = new JButton("");
		button_39.setBounds(176, 284, 84, 68);
		button_39.setForeground(Color.BLACK);
		button_39.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_39.setBackground(Color.LIGHT_GRAY);
		panel.add(button_39);
		
		JButton button_40 = new JButton("");
		button_40.setBounds(263, 284, 84, 68);
		button_40.setForeground(Color.BLACK);
		button_40.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_40.setBackground(Color.WHITE);
		panel.add(button_40);
		
		JButton button_41 = new JButton("");
		button_41.setBounds(350, 284, 84, 68);
		button_41.setForeground(Color.BLACK);
		button_41.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_41.setBackground(Color.WHITE);
		panel.add(button_41);
		
		JButton button_42 = new JButton("");
		button_42.setBounds(437, 284, 84, 68);
		button_42.setForeground(Color.BLACK);
		button_42.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_42.setBackground(Color.WHITE);
		panel.add(button_42);
		
		JButton button_43 = new JButton("");
		button_43.setBounds(524, 284, 84, 68);
		button_43.setForeground(Color.BLACK);
		button_43.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_43.setBackground(Color.LIGHT_GRAY);
		panel.add(button_43);
		
		JButton button_44 = new JButton("");
		button_44.setBounds(611, 284, 84, 68);
		button_44.setForeground(Color.BLACK);
		button_44.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_44.setBackground(Color.LIGHT_GRAY);
		panel.add(button_44);
		
		JButton button_45 = new JButton("");
		button_45.setBounds(698, 284, 84, 68);
		button_45.setForeground(Color.BLACK);
		button_45.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_45.setBackground(Color.LIGHT_GRAY);
		panel.add(button_45);
		
		JButton button_46 = new JButton("");
		button_46.setBounds(2, 355, 84, 68);
		button_46.setForeground(Color.BLACK);
		button_46.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_46.setBackground(Color.LIGHT_GRAY);
		panel.add(button_46);
		
		JButton button_47 = new JButton("");
		button_47.setBounds(89, 355, 84, 68);
		button_47.setForeground(Color.BLACK);
		button_47.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_47.setBackground(Color.LIGHT_GRAY);
		panel.add(button_47);
		
		JButton button_48 = new JButton("");
		button_48.setBounds(176, 355, 84, 68);
		button_48.setForeground(Color.BLACK);
		button_48.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_48.setBackground(Color.LIGHT_GRAY);
		panel.add(button_48);
		
		JButton button_49 = new JButton("");
		button_49.setBounds(263, 355, 84, 68);
		button_49.setForeground(Color.BLACK);
		button_49.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_49.setBackground(Color.WHITE);
		panel.add(button_49);
		
		JButton button_50 = new JButton("");
		button_50.setBounds(350, 355, 84, 68);
		button_50.setForeground(Color.BLACK);
		button_50.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_50.setBackground(Color.WHITE);
		panel.add(button_50);
		
		JButton button_51 = new JButton("");
		button_51.setBounds(437, 355, 84, 68);
		button_51.setForeground(Color.BLACK);
		button_51.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_51.setBackground(Color.WHITE);
		panel.add(button_51);
		
		JButton button_52 = new JButton("");
		button_52.setBounds(524, 355, 84, 68);
		button_52.setForeground(Color.BLACK);
		button_52.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_52.setBackground(Color.LIGHT_GRAY);
		panel.add(button_52);
		
		JButton button_53 = new JButton("");
		button_53.setBounds(611, 355, 84, 68);
		button_53.setForeground(Color.BLACK);
		button_53.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_53.setBackground(Color.LIGHT_GRAY);
		panel.add(button_53);
		
		JButton button_54 = new JButton("");
		button_54.setBounds(698, 355, 84, 68);
		button_54.setForeground(Color.BLACK);
		button_54.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_54.setBackground(Color.LIGHT_GRAY);
		panel.add(button_54);
		
		JButton button_55 = new JButton("");
		button_55.setBounds(2, 426, 84, 68);
		button_55.setForeground(Color.BLACK);
		button_55.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_55.setBackground(Color.WHITE);
		panel.add(button_55);
		
		JButton button_56 = new JButton("");
		button_56.setBounds(89, 426, 84, 68);
		button_56.setForeground(Color.BLACK);
		button_56.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_56.setBackground(Color.WHITE);
		panel.add(button_56);
		
		JButton button_57 = new JButton("");
		button_57.setBounds(176, 426, 84, 68);
		button_57.setForeground(Color.BLACK);
		button_57.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_57.setBackground(Color.WHITE);
		panel.add(button_57);
		
		JButton button_58 = new JButton("");
		button_58.setBounds(263, 426, 84, 68);
		button_58.setForeground(Color.BLACK);
		button_58.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_58.setBackground(Color.LIGHT_GRAY);
		panel.add(button_58);
		
		JButton button_59 = new JButton("");
		button_59.setBounds(350, 426, 84, 68);
		button_59.setForeground(Color.BLACK);
		button_59.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_59.setBackground(Color.LIGHT_GRAY);
		panel.add(button_59);
		
		JButton button_60 = new JButton("");
		button_60.setBounds(437, 426, 84, 68);
		button_60.setForeground(Color.BLACK);
		button_60.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_60.setBackground(Color.LIGHT_GRAY);
		panel.add(button_60);
		
		JButton button_61 = new JButton("");
		button_61.setBounds(524, 426, 84, 68);
		button_61.setForeground(Color.BLACK);
		button_61.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_61.setBackground(Color.WHITE);
		panel.add(button_61);
		
		JButton button_62 = new JButton("");
		button_62.setBounds(611, 426, 84, 68);
		button_62.setForeground(Color.BLACK);
		button_62.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_62.setBackground(Color.WHITE);
		panel.add(button_62);
		
		JButton button_63 = new JButton("");
		button_63.setBounds(698, 426, 84, 68);
		button_63.setForeground(Color.BLACK);
		button_63.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_63.setBackground(Color.WHITE);
		panel.add(button_63);
		
		JButton button_64 = new JButton("");
		button_64.setBounds(2, 497, 84, 68);
		button_64.setForeground(Color.BLACK);
		button_64.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_64.setBackground(Color.WHITE);
		panel.add(button_64);
		
		JButton button_65 = new JButton("");
		button_65.setBounds(89, 497, 84, 68);
		button_65.setForeground(Color.BLACK);
		button_65.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_65.setBackground(Color.WHITE);
		panel.add(button_65);
		
		JButton button_66 = new JButton("");
		button_66.setBounds(176, 497, 84, 68);
		button_66.setForeground(Color.BLACK);
		button_66.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_66.setBackground(Color.WHITE);
		panel.add(button_66);
		
		JButton button_67 = new JButton("");
		button_67.setBounds(263, 497, 84, 68);
		button_67.setForeground(Color.BLACK);
		button_67.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_67.setBackground(Color.LIGHT_GRAY);
		panel.add(button_67);
		
		JButton button_68 = new JButton("");
		button_68.setBounds(350, 497, 84, 68);
		button_68.setForeground(Color.BLACK);
		button_68.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_68.setBackground(Color.LIGHT_GRAY);
		panel.add(button_68);
		
		JButton button_69 = new JButton("");
		button_69.setBounds(437, 497, 84, 68);
		button_69.setForeground(Color.BLACK);
		button_69.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_69.setBackground(Color.LIGHT_GRAY);
		panel.add(button_69);
		
		JButton button_70 = new JButton("");
		button_70.setBounds(524, 497, 84, 68);
		button_70.setForeground(Color.BLACK);
		button_70.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_70.setBackground(Color.WHITE);
		panel.add(button_70);
		
		JButton button_71 = new JButton("");
		button_71.setBounds(611, 497, 84, 68);
		button_71.setForeground(Color.BLACK);
		button_71.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_71.setBackground(Color.WHITE);
		panel.add(button_71);
		
		JButton button_72 = new JButton("");
		button_72.setBounds(698, 497, 84, 68);
		button_72.setForeground(Color.BLACK);
		button_72.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_72.setBackground(Color.WHITE);
		panel.add(button_72);
		
		JButton button_73 = new JButton("");
		button_73.setBounds(2, 568, 84, 68);
		button_73.setForeground(Color.BLACK);
		button_73.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_73.setBackground(Color.WHITE);
		panel.add(button_73);
		
		JButton button_74 = new JButton("");
		button_74.setBounds(89, 568, 84, 68);
		button_74.setForeground(Color.BLACK);
		button_74.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_74.setBackground(Color.WHITE);
		panel.add(button_74);
		
		JButton button_75 = new JButton("");
		button_75.setBounds(176, 568, 84, 68);
		button_75.setForeground(Color.BLACK);
		button_75.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_75.setBackground(Color.WHITE);
		panel.add(button_75);
		
		JButton button_76 = new JButton("");
		button_76.setBounds(263, 568, 84, 68);
		button_76.setForeground(Color.BLACK);
		button_76.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_76.setBackground(Color.LIGHT_GRAY);
		panel.add(button_76);
		
		JButton button_77 = new JButton("");
		button_77.setBounds(350, 568, 84, 68);
		button_77.setForeground(Color.BLACK);
		button_77.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_77.setBackground(Color.LIGHT_GRAY);
		panel.add(button_77);
		
		JButton button_78 = new JButton("");
		button_78.setBounds(437, 568, 84, 68);
		button_78.setForeground(Color.BLACK);
		button_78.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_78.setBackground(Color.LIGHT_GRAY);
		panel.add(button_78);
		
		JButton button_79 = new JButton("");
		button_79.setBounds(524, 568, 84, 68);
		button_79.setForeground(Color.BLACK);
		button_79.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_79.setBackground(Color.WHITE);
		panel.add(button_79);
		
		JButton button_80 = new JButton("");
		button_80.setBounds(611, 568, 84, 68);
		button_80.setForeground(Color.BLACK);
		button_80.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_80.setBackground(Color.WHITE);
		panel.add(button_80);
		
		JButton button_81 = new JButton("");
		button_81.setBounds(698, 568, 84, 68);
		button_81.setForeground(Color.BLACK);
		button_81.setFont(new Font("Tahoma", Font.PLAIN, 40));
		button_81.setBackground(Color.WHITE);
		panel.add(button_81);
		
		for(int i = 0; i < 81; i++)
		{
			JButton button = (JButton) panel.getComponent(i);
			button.addActionListener(new ButtonListener(button));
			buttonList.add(button);
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(5, 657, 784, 33);
		contentPane.add(panel_1);
		
		JButton btnNewButton = new JButton("Solve");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				final int[][] puzzle = getPuzzle();
			      
			      printPuzzle(puzzle);
			      System.out.println();	      
			      
			      Thread thread = new Thread()
					{
					    public void run()
					    {
					    	if(!solve(0, 0, puzzle))
					    	{
					    		JOptionPane.showMessageDialog(null, "Puzzle is Unsolvable!", "Alert", JOptionPane.ERROR_MESSAGE);
					    	}
						    
					    	else
					    	{
					    		JOptionPane.showMessageDialog(null, "Solved!", "Alert", JOptionPane.INFORMATION_MESSAGE);
					    	}
					    	System.out.println("\n\n");
					    	printPuzzle(puzzle);
					    }
					};
					 
					thread.start();
			      
			      
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(Color.WHITE);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Iterator<JButton> it = buttonList.iterator();
				
				while(it.hasNext())
				{
					JButton button = it.next();
					if(button.getForeground() == Color.BLACK)
						button.setText("");
				}
			}
		});
		panel_1.add(btnClear);
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.setBackground(Color.LIGHT_GRAY);
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Iterator<JButton> it = buttonList.iterator();
				
				while(it.hasNext())
				{
					JButton button = it.next();
					button.setText("");
					
					if(button.getForeground() == Color.RED)
						button.setForeground(Color.BLACK);
				}
			}
		});
		panel_1.add(btnClearAll);
	}
	
	/**
	 * prinNum prints a value on a button at a chosen position
	 * @param num the number to print
	 * @param pos the position of the button
	 */
	public void printNum(int num, int pos)
	{
		
		JButton myButton = (JButton) panel.getComponent(pos);
		
		if(num == 0)
			myButton.setText(" ");
		else
			myButton.setText(num + "");
	}
	
	/**
	 * solve- Recursive method that uses backtracking to solve a sudoku puzzle
	 * @param row the current row
	 * @param col the current collumn
	 * @param array the puzzle
	 * @return returns true if the puzzle was solved, false if otherwise
	 */
	public boolean solve(int row, int col, int[][] array)
	{	
		while(array[row][col] != 0) //find a blank space
		{
			if(row == 8 && col == 8)
				return true;
			
			row = incrementRow(row, col);
			col = incrementCol(row, col);
		}
		
		
		for(int num = 1; num < 10; num++)
		{
			//if number fits
			if(checkRow(col, array, num) && checkCol(row, array, num) && checkSquare(row, col, array, num))
			{
				array[row][col] = num;
				printNum(num, findPos(row, col));
				
				try 
				{
				    //give the user time to see the program run
					Thread.currentThread().sleep(5);
				} 
				
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(solve(row, col, array))
				{
					return true;
				}
				
				else
				{
					array[row][col] = 0;
					printNum(num, findPos(row, col));
					
					try 
					{
						Thread.currentThread().sleep(5);
					} 
					
					catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		printNum(0, findPos(row, col));
		
		try 
		{
			Thread.currentThread().sleep(5);
		} 
		
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * checks to see if a number is in a particular row
	 * @param posCol- the column position of the number
	 * @param array- the puzzle
	 * @param num- the number
	 * @return true if the number is absent, false if the number exists
	 */
	private boolean checkRow(int posCol, int[][] array, int num)
	{
		for(int count = 0; count < 9; count++)
		{
			if(array[count][posCol] == num)
				return false;
		}
		
		return true;
	}
	
	
	/**
     * checks to see if a number is in a particular column
     * @param posCol- the row position of the number
     * @param array- the puzzle
     * @param num- the number
     * @return true if the number is absent, false if the number exists
     */
	private boolean checkCol(int posRow, int[][] array, int num)
	{
		for(int count = 0; count < 9; count++)
		{
			if(array[posRow][count] == num)
				return false;
		}
		
		return true;
	}
	/**
     * checks to see if a number is in a particular 9x9 square
     * @param posRow- the row position 
     * @param posCol- the column position of the number
     * @param array- the puzzle
     * @param num- the number
     * @return true if the number is absent, false if the number exists
     */
	private boolean checkSquare(int posRow, int posCol, int[][] array, int num)
	{
		posRow = (posRow / 3) * 3 ;
		posCol = (posCol / 3) * 3 ;

	      for(int r = 0; r < 3; r++)
	      {
	    	  for(int c = 0; c < 3; c++)
	    	  {
	    		  if(array[posRow + r][posCol + c] == num)
		 	            return false ;
	    	  } 	         
	      }     
	      return true ;
	}
	
	/**
	 * incrementCol- moves a column counter up
	 * @param row- current row counter 
	 * @param col- current column counter
	 * @return- the incremented column
	 */
	private int incrementCol(int row, int col)
	{
		//if at last column
		if(col == 8) 
		{
			col = 0;
		}
		
		//else increment column
		else
			col++;
		
		return col;
	}
	
	/**
     * incrementRow- moves a row counter up
     * @param row- current row counter 
     * @param col- current column counter
     * @return- the incremented row
     */
	private int incrementRow(int row, int col)
	{
		//if at last column
		if(col == 8) 
		{
			row++;
		}
		
		return row;
	}
	
	/**
	 * print puzzle- prints out the puzzle in the command window
	 * @param array- the puzzle
	 */
	public void printPuzzle(int array[][])
	{
		for(int count = 0; count < 9; count++)
		{
			for(int count2 = 0; count2 < 9; count2++)
			{
				if(array[count][count2] == 0)
					System.out.print("_ ");
				//if(count2 % 3 == 0)
					//System.out.print(" ");
				else
					System.out.print(array[count][count2] + " ");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * findPos- finds the one dimensional position of two dimensional value
	 * @param row- the row position
	 * @param col- the column position
	 * @return- the one dimensional value
	 */
	public int findPos(int row, int col)
	{
		if(row == 0)
			return col;
		
		else
			return (row * 9) + col;
	}
	
	/**
	 * getPuzzle- builds a puzzle (two-dimensional int array) from the current UI
	 * @return returns the puzzle
	 */
	private int[][] getPuzzle()
	{
		java.util.Iterator<JButton> it = buttonList.iterator();
		int[][] newPuzzle = new int[9][9];
		
		for(int rowCount = 0; rowCount < 9; rowCount++)
		{
			for(int colCount = 0; colCount < 9; colCount++)
			{
				String buttonText = it.next().getText();
				int num;
				
				if(buttonText.equals(""))
					num = 0;
				else
					num = Integer.parseInt(buttonText);
				
				newPuzzle[rowCount][colCount] = num;
			}
		}
		
		return newPuzzle;
	}
	
	/**
	 * prints an entire puzzle to the UI
	 * @param newPuzzle- the puzzle
	 */
	private void setPuzzle(int[][] newPuzzle)
	{
		java.util.Iterator<JButton> it = buttonList.iterator();
		
		for(int rowCount = 0; rowCount < 9; rowCount++)
		{
			for(int colCount = 0; colCount < 9; colCount++)
			{
				JButton button = it.next();
				String num = newPuzzle[rowCount][colCount] + "";
				if(num.equals("0"))
				{
					num = "";
					button.setForeground(Color.BLACK);
				}
				
				else
					button.setForeground(Color.RED);
				
				button.setText(num);
			}
		}
	}
	
	class ButtonListener implements ActionListener
	{
		JButton button;
		
		public ButtonListener(JButton thisButton)
		{
			button = thisButton;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				button.setText(JOptionPane.showInputDialog(button.getParent(), "Please Select a Value", button.getText()));
				
				if(button.getText().equals(""))
					button.setForeground(Color.BLACK);
				else
					button.setForeground(Color.RED);
			}
			
			catch(java.lang.NullPointerException ex) 
			{
				button.setText("");
				button.setForeground(Color.BLACK);
			}
			
		}
		
	}

}
