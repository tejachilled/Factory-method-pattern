package com.patterns.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.patterns.singleton.GradeFactory;


public class GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JRadioButton GreatBritain,UnitedStates;
	JRadioButton Dinner,Evening,AllDay;
	JRadioButton Text,XML,HTML;
	ButtonGroup buttonGroup1 = new ButtonGroup();
	ButtonGroup buttonGroup2 = new ButtonGroup();
	JButton Submit;
	JLabel filepath;
	JTextField path;
	
	public GUI()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.ipadx=6;
		gbc.ipady=11;

		JRadioButton Undergraduate = new JRadioButton("Undergraduate",true);
		gbc.fill= GridBagConstraints.HORIZONTAL;
		gbc.gridx=1;
		gbc.gridy=1;
		add(Undergraduate,gbc);
		Undergraduate.setActionCommand("UG");
		buttonGroup1.add(Undergraduate);

		JRadioButton Graduate = new JRadioButton("Graduate",false);
		gbc.fill= GridBagConstraints.HORIZONTAL;
		gbc.gridx=1;
		gbc.gridy=2;
		add(Graduate,gbc);
		Graduate.setActionCommand("GR");
		buttonGroup1.add(Graduate);

		JRadioButton CSV = new JRadioButton("CSV",true);
		gbc.fill= GridBagConstraints.HORIZONTAL;
		gbc.gridx=3;
		gbc.gridy=1;
		add(CSV,gbc);
		CSV.setActionCommand("CSV");
		buttonGroup2.add(CSV);

		JRadioButton XML = new JRadioButton("XML",false);
		gbc.fill= GridBagConstraints.HORIZONTAL;
		gbc.gridx=3;
		gbc.gridy=2;
		add(XML,gbc);
		XML.setActionCommand("XML");
		buttonGroup2.add(XML);

		JRadioButton HTML = new JRadioButton("HTML",false);
		gbc.fill= GridBagConstraints.HORIZONTAL;
		gbc.gridx=3;
		gbc.gridy=3;
		add(HTML,gbc);
		HTML.setActionCommand("HTML");
		buttonGroup2.add(HTML);

		Submit= new JButton("Submit");
		gbc.fill= GridBagConstraints.HORIZONTAL;
		gbc.gridx=1;
		gbc.gridy=5;
		gbc.gridwidth=3;
		add(Submit,gbc);
		
		filepath=new JLabel("File Location:");
		gbc.fill= GridBagConstraints.HORIZONTAL;
		gbc.gridx=1;
		gbc.gridy=14;
		add(filepath,gbc);

		path= new JTextField(7);
		path.setText("");
		gbc.fill= GridBagConstraints.HORIZONTAL;
		gbc.gridx=2;
		gbc.gridy=14;
		gbc.gridwidth=3;
		add(path,gbc);

		Submit.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getActionCommand().equals("Submit"))
		{
			try
			{
				ButtonModel choice1 = buttonGroup1.getSelection();
				ButtonModel choice2 = buttonGroup2.getSelection();

				if (choice1 != null || choice2 != null  ) 
				{
					// System.out.println("checks the choice");
					String com1,com2;
					com1=choice1.getActionCommand();
					com2=choice2.getActionCommand();
					System.out.println(choice1.getActionCommand());
					System.out.println(choice2.getActionCommand());
					GradeFactory factory = GradeFactory.getInstance();
					factory.grade(com1, com2);
					path();
				}
				else System.out.println("Sorry");
			}

			catch(Exception ee)
			{
				System.out.println("Exception caught here");
			}

		}
		
	}
	public void path() {
		String filepath = String.format("%s", System.getProperty("user.dir"), this.getName().replace(".", "/"));
		System.out.println(filepath+"\\OutputFiles");
		path.setText(filepath+"\\OutputFiles");
	}

}
