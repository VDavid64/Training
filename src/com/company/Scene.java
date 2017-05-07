package com.company;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Scene extends JComponent {
	
	private final static int WIDTH = 1000, HEIGHT = 600;
	public static ArrayList<Drawable> objects = new ArrayList<Drawable>();
	private static String text = "X:  " + " Y:  ";
	public static boolean mapLoaded;

	public static void InitScene(Game game){
		mapLoaded = false;
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
				mapLoaded = true;
			}
		});

		newScene.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				//csak teszt
				text = "X: " + x + " Y: " + y;

				String clickedElementName = getClickedElement();
				game.getMap().onMouseClickedEvent(clickedElementName);
				newScene.revalidate();
				newScene.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});

		f.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics e) {
		super.paintComponent(e);

		// teszt-text bal alulra
		e.setFont(new Font("TimesRoman", Font.BOLD, 14));
		e.setColor(Color.black);
		e.drawString(text, 0, 540);

		drawAll(e);
		revalidate();
		repaint();
	}

	//TODO: fv ami visszaadja a klikkelt objektum nevét
	public static String getClickedElement() {
		return "";
	}

	public static void addDrawable(Drawable obj){
		objects.add(obj);
	}

	public void drawAll(Graphics g) {
		for(Drawable d : objects){
			d.drawElement((Graphics2D) g);
		}
	}
}
