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

/**
 * Responsible for visualization. Extends from JComponent class.
 * 
 * @author i_did_iit team
 *
 */
public class Scene extends JComponent {

	/**
	 * Final static integer value of Window's width. It's value is 1000.
	 */
	private final static int WIDTH = 1000;
	/**
	 * Final static integer value of Window's height. It's value is 1000.
	 */
	private final static int HEIGHT = 720;
	/**
	 * Arraylist of Drawable objects. Objects need to draw.
	 */
	public static ArrayList<Drawable> objects = new ArrayList<Drawable>();

	/**
	 * mapLoaded whether map is loaded.
	 */
	public static int mapLoaded = 0;
	
	/**
	 * JFrame
	 */
	public static JFrame f;

	/**
	 * Arraylist of clickable objects.
	 */
	public static ArrayList<Clickable> clickables = new ArrayList<Clickable>();
	
	/**
	 * Controller
	 */
	public static Controller controller = new Controller();

	/**
	 * Initialization of Scene. Create JFrame, JMenubar, JMenus, sets texts,
	 * background color, etc.
	 * 
	 * @param game
	 *            Game object what need a window.
	 */
	public static void InitScene(Game game) {
		controller.game = game;
		Scene newScene = new Scene();
		controller.scene = newScene;
		f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(WIDTH, HEIGHT);
		f.setResizable(false);
		newScene.addMouseListener(controller);
		f.setTitle("I like trains");
		JMenuBar menuBar = new JMenuBar();
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem start = new JMenuItem("Start");

		menuBar.add(exit);
		menuBar.add(start);
		menuBar.setBackground(java.awt.Color.gray);

		f.add(menuBar);
		f.setJMenuBar(menuBar);
		f.getContentPane().add(newScene);

		exit.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		start.addActionListener(controller);
		start.setActionCommand("START");
		controller.start = start;
		
	/*	start.addActionistener(new ActionListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mapLoaded%2 == 1){
					f.dispose();
				}
				game.loadMap("gridmap_1");
				mapLoaded ++;
				start.setText("Restart");
				game.timer.start();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
/*
		newScene.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				// csak teszt
				text = "X: " + x + " Y: " + y;

				String clickedElementName = getClickedElement();
				game.getMap().onMouseClickedEvent(clickedElementName);
				newScene.revalidate();
				newScene.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});*/

		f.setVisible(true);
	}

	/*
	 * Overridden function. Calls the UI delegate's paint method, if the UI
	 * delegate is non-null. Calls drawAll,
	 * revalidate and repaint functions.
	 * 
	 * @param e the Graphics context in which to paint
	 */
	@Override
	public void paintComponent(Graphics e) {
		super.paintComponent(e);
		drawAll(e);
		revalidate();
		repaint();
	}

	/**
	 * Getter of clicked element's name.
	 * 
	 * @return Name of clicked element.
	 */
	public static String getClickedElement(int x, int y) {
		for (Clickable c : clickables){
			System.out.println(c.getFrameTile().getX() + "   " + c.getFrameTile().getY() );
			if((c.getFrameTile().contains(x, y))) {
				return c.getName();
			}
		}
		return "";
	}

	/**
	 * Adds Drawable object to objects arraylist.
	 * 
	 * @param obj
	 *            Drawable object what need to add.
	 */
	public static void addDrawable(Drawable obj) {
		objects.add(obj);
	}

	/**
	 * Paints all components of objects.
	 * 
	 * @param g
	 *            the graphics context to use for painting
	 */
	public void drawAll(Graphics g) {
		
		((Graphics2D)g).scale(0.65, 0.65);
		for (Drawable d : objects) {

			d.drawElement((Graphics2D) g);
			
		}
		g.setColor(this.getBackground());
		g.fillRect(0*64+32-18, 0*64+17+15-8, 37, 16);
	}
}
