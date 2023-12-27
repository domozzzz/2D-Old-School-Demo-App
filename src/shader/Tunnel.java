package shader;

import java.awt.Color;
import java.util.Random;

import main.Screen;

public class Tunnel {
	
	int w;
	int h;
	Screen testBitmap;
	int palette[];
	int fire[][];
	int paletteShift = 0;
	Random random;
	
	int texWidth = 256;
	int texHeight = 256;
	
	int[][] texture;
	int[][] distanceTable;
	int[][] angleTable;
	
	int shiftX = 0;
	int shiftY = 0;
	
	public Tunnel(int w, int h) {
		
		this.w = w;
		this.h = h;
		
		
	}

	public void render(Screen screen) {
		
		texture = new int[texWidth][texHeight];
		random = new Random();
		palette = new int[255];
		fire = new int [w][h];
		distanceTable = new int[w][h];
		angleTable = new int[w][h];
		
		for(int y = 0; y < texHeight; y++)
		for(int x = 0; x < texWidth; x++)
	    texture[y][x] = (x * 256 / texWidth) ^ (y * 256 / texHeight);
		 
		 for(int y = 0; y < h; y++) {
	         for(int x = 0; x < w; x++) {
			    int angle;
			    int distance;
			    float ratio = 32.0f;
			    distance = (int)(ratio * texHeight / Math.sqrt((x - w / 2.0) * (x - w / 2.0) + (y - h / 2.0) * (y - h / 2.0))) % texHeight;
			    angle = (int)(0.5 * texWidth * Math.atan2(y - h / 2.0, x - w / 2.0) / 3.1416);
			    distanceTable[y][x] = distance;
			    angleTable[y][x] = angle;
	         }
		 }
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
	
				int color = texture[(int)(distanceTable[y][x] + shiftX) % w][(int)(angleTable[y][x] + shiftY) % h];
			      
			    testBitmap.pixels[x + y * w] = color;
			}
		}
		
		screen.render();
	}
}