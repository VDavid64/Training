package com.company;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

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
		JMenu exit = new JMenu("Exit");
		JMenu start = new JMenu("Start");
		menuBar.add(exit);
		menuBar.add(start);
		menuBar.setBackground(java.awt.Color.gray);
		this.add(menuBar);
		setJMenuBar(menuBar);
		exit.addMenuListener(new MenuListener() {
			 @Override
		        public void menuSelected(MenuEvent e) {
				System.exit(0);
			}

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		start.addMenuListener(new MenuListener(){

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuSelected(MenuEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
	}

	public void drawAll(Graphics2D g) {
	};
}
