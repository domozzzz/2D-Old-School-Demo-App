package shader;

import java.awt.Color;
import java.util.Random;

import main.Screen;
import main.Settings;

public class Tunnel {
	
	int w;
	int h;
	Screen testBitmap;
	int palette[];
	int fire[][];
	int paletteShift = 0;
	Random random;
	float animation = 0; 
	int buffer[][];
	
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
		
		buffer = new int[h][w];
		texture = new int[h][w];
		distanceTable = new int[h][w];
		angleTable = new int[h][w];
		
		
		  //generate texture
		  for(int y = 0; y < texHeight; y++)
		  for(int x = 0; x < texWidth; x++)
		  {
		    texture[y][x] = (x * 256 / texWidth) ^ (y * 256 / texHeight);
		  }
		  
		//generate non-linear transformation table
		  for(int y = 0; y < h; y++)
		  for(int x = 0; x < w; x++)
		  {
		    int angle, distance;
		    float ratio = 32.0f;
		    distance = (int)(ratio * texHeight / Math.sqrt((x - w / 2.0) * (x - w / 2.0) + (y - h / 2.0) * (y - h / 2.0))) % texHeight;
		    angle = (int)(0.5 * texWidth * (Math.atan2(y - h / 2.0, x - w / 2.0) / 3.1416));
		    distanceTable[y][x] = distance;
		    angleTable[y][x] = angle + 180;
		  }
		
	}

	public void render(Screen screen) {
		
		animation += Settings.SPEED / 100.0f;

		  int shiftX = (int)(texWidth * 1.0 * animation);
		  int shiftY = (int)(texHeight * 0.25 * animation);

		    for(int y = 0; y < h; y++)
		    for(int x = 0; x < w; x++)
		    {
		      //get the texel from the texture by using the tables, shifted with the animation values
		      int color = texture[(int)(distanceTable[y][x] + shiftX)  % texWidth][(int)(Math.abs(angleTable[y][x])+ shiftY) % texHeight];
		      buffer[y][x] = color;
		    
		    screen.pixels[x + y * w]  = buffer[y][x];	
		    }
		
		screen.render();
	}
}