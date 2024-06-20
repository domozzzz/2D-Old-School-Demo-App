package shader;

import main.Screen;
import main.Settings;

public class Tunnel implements Shader {
	
	private int w, h;
	private float animation = 0; 
	private int buffer[][];
	
	private int texWidth = 256;
	private int texHeight = 256;
	
	private int[][] texture;
	private int[][] distanceTable;
	private int[][] angleTable;
	
	public Tunnel(int w, int h) {
		
		this.w = w;
		this.h = h;
		
		buffer = new int[h][w];
		texture = new int[h][w];
		distanceTable = new int[h*2][w*2];
		angleTable = new int[h*2][w*2];
		
		
		  //generate texture
		  for(int y = 0; y < texHeight; y++)
		  for(int x = 0; x < texWidth; x++)
		  {
		    texture[y][x] = (x * 256 / texWidth) ^ (y * 256 / texHeight);
		  }
		  
		//generate non-linear transformation table
		  for(int y = 0; y < h * 2; y++)
		  for(int x = 0; x < w * 2; x++)
		  {
		    int angle, distance;
		    float ratio = 32.0f;
		    distance = (int)(ratio * texHeight / Math.sqrt((float)((x - w) * (x - w) + (y - h) * (y - h)))) % texHeight;
		    angle = (int)(0.5 * texWidth * Math.atan2((float)(y - h), (float)(x - w)) / 3.1416);
		    distanceTable[y][x] = distance;
		    angleTable[y][x] = angle + 180;
		  }
	}

	public void render(Screen screen) {
		
		animation += Settings.SPEED / 100.0f;

		int shiftX = (int)(texWidth * 1.0 * animation);
		int shiftY = (int)(texHeight * 0.25 * animation);
		int shiftLookX = w / 2 + (int)(w / 2 * Math.sin(animation));
		int shiftLookY = h / 2 + (int)(h / 2 * Math.sin(animation * 2.0));

		    for(int y = 0; y < h; y++)
		    for(int x = 0; x < w; x++)
		    {
		      // get the texel from the texture by using the tables, shifted with the animation values
		      int color = texture[(int)(distanceTable[x + shiftLookX][y + shiftLookY] + shiftX)  % texWidth]
		          [(int)(angleTable[x + shiftLookX][y + shiftLookY]+ shiftY) % texHeight];
		      buffer[y][x] = color;
		    
		      screen.getPixels()[x + y * w]  = buffer[y][x];	
		    }
		
		screen.render();
	}
}