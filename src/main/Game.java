package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JFrame;

import shader.Fire;
import shader.Noise;
import shader.Plasma;
import shader.Tunnel;
import shader.Xor;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;

	public static final int FPS = 60;
	public static final int WIDTH = 512;
	public static final int HEIGHT = 512;
	public static final int SCALE = 1;
	
	public static String DEMO = "None";
	public static String[] values = {"None", "Fire", "Plasma", "Noise", "XOR"};
	
	Plasma plasma;
	Fire fire;
	Noise noise;
	Xor xor;
	private int[] pixels;
	public boolean running;
	private Screen screen;
	private BufferedImage displayImg;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		screen = new Screen();
		screen.init(WIDTH, HEIGHT);
		
		displayImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)displayImg.getRaster().getDataBuffer()).getData();
	
		plasma = new Plasma(WIDTH, HEIGHT);
		fire = new Fire(WIDTH, HEIGHT);
		noise = new Noise(WIDTH, HEIGHT, screen);
		xor = new Xor(WIDTH, HEIGHT);		
	}
	
	public void start() {
		running = true;
		new Thread(this).start();
	}
	
	public void stop() {
		running = false;
	}
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double tickTime = 1000000000.0/FPS;
		double i = 0;
		
		while (running)  {
			
			long currentTime = System.nanoTime();
			i += (currentTime - lastTime)/tickTime;
			lastTime = currentTime;
			
			if (i >= 1) {
				tick();
				render();
				i--;
			}
		}
	}
	
	private void tick() {
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		switch (Game.DEMO) {
		case "Plasma" -> plasma.render(screen);
		case "XOR" -> xor.render(screen);
		case "Fire" -> fire.render(screen);
		case "Noise"-> noise.render(screen);
		case "None"-> Arrays.fill(screen.pixels, 0x000000);
		}
				
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(displayImg, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		bs.show();
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Game game = new Game();
		int panelWidth = 300;
		SidePanel sp = new SidePanel(panelWidth, HEIGHT);
		
		sp.setLocation(WIDTH, 0);
		frame.setPreferredSize(new Dimension((WIDTH + panelWidth) * SCALE, HEIGHT * SCALE));
		frame.add(game, BorderLayout.WEST);
		frame.add(sp);
		frame.setTitle("beebee");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		game.start();
	}
}