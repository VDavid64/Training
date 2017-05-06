package com.company;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Scene extends JComponent {
	
	private final static int WIDTH = 1000, HEIGHT = 600;


	private static ArrayList<Drawable> objects = new ArrayList<Drawable>();

	public static void InitScene(Game game){
		Scene newScene = new Scene();
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(WIDTH, HEIGHT);
		f.setResizable(false);

		f.setTitle("I like trains");
		JMenuBar menuBar = new JMenuBar();
		JMenu exit = new JMenu("Exit");
		JMenu start = new JMenu("Start");

		menuBar.add(exit);
		menuBar.add(start);
		menuBar.setBackground(java.awt.Color.gray);

		f.add(menuBar);
		f.setJMenuBar(menuBar);
		f.getContentPane().add(newScene);

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
				game.loadMap("testmap");
				objects.add(new Draw_Rail(new Rail()));
			}
		});
		f.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics e) {
		super.paintComponent(e);
		drawAll(e);
	}

	public void addDrawable(Drawable obj){
		objects.add(obj);
	}

	public void drawAll(Graphics g) {
		for(Drawable d : objects){
			d.drawElement((Graphics2D) g);
		}
		revalidate();
		repaint();
	}
}
