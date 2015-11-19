package com.patterns.ui;

import javax.swing.JFrame;

public class Execute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			GUI g1=new GUI();
			g1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			g1.setVisible(true);
			g1.setSize(600,500);
			g1.setTitle("\tGrade Generator");
		}
		
		catch (Exception e)
		{
			e.printStackTrace();// TODO Auto-generated method stub
		}
	}

}
