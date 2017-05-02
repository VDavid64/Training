package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Scene extends JFrame{
	
	private final static int WIDTH = 1000, HEIGHT = 600;

	ArrayList<Drawable> objects = new ArrayList<Drawable>();

	public Scene(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setVisible(true);
		setTitle("I like trains");
		JMenuBar menuBar = new JMenuBar();
		JMenuItem exit = new JRadioButtonMenuItem("Exit");
		menuBar.add(exit);
		menuBar.setBackground(java.awt.Color.gray);
		this.add(menuBar);
		setJMenuBar(menuBar);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public void drawAll() {};
}
