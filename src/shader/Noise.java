package shader;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import main.Screen;

public class Noise {
	
	int w;
	int h;
	
	int noiseWidth = 128;
	int noiseHeight = 128;
	
	Screen testBitmap;
	int palette[];
	double noise[][];
	int paletteShift = 0;
	Random random;
	
	int texWidth = 256;
	int texHeight = 256;
	
	int shiftX = 0;
	int shiftY = 0;
	
	public Noise(int w, int h ,Screen screen) {
		
		this.w = w;
		this.h = h;
	}
	
	double smoothNoise(double x, double y)
	{
	   //get fractional part of x and y
	   double fractX = x - (int)(x);
	   double fractY = y - (int)(y);
	
	   //wrap around
	   int x1 = ((int)(x) + noiseWidth) % noiseWidth;
	   int y1 = ((int)(y) + noiseHeight) % noiseHeight;
	
	   //neighbor values
	   int x2 = (x1 + noiseWidth - 1) % noiseWidth;
	   int y2 = (y1 + noiseHeight - 1) % noiseHeight;
	
	   //smooth the noise with bilinear interpolation
	   double value = 0.0;
	   value += fractX     * fractY     * noise[y1][x1];
	   value += (1 - fractX) * fractY     * noise[y1][x2];
	   value += fractX     * (1 - fractY) * noise[y2][x1];
	   value += (1 - fractX) * (1 - fractY) * noise[y2][x2];
	
	   return value;
	}

	public void render(Screen screen) {
		noise = new double[256][256];
		random = new Random();
		
		for(int y = 0; y < h; y++) {
		    for(int x = 0; x < w; x++) {
				    
			    //[x][y] = Math.abs(random.nextInt() % 32768 / 32768.0);
			    
			    //int c = (int) (256 * smoothNoise(x/8.0, y/8.0));
			    int c = Math.abs(random.nextInt() % 255);

			    screen.getPixels()[x + y * w]  = new Color(c, c, c).getRGB();
			  }
		 }
		screen.render();
	}
}