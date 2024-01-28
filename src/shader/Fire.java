package shader;

import java.awt.Color;
import java.util.Random;

import main.Screen;
import main.Settings;

public class Fire {
	
	int w;
	int h;
	int palette[];
	int fire[][];
	int paletteShift = 0;
	Random random;

	
	public Fire(int w, int h) {
		
		this.w = w;
		this.h = h;
		
		random = new Random();
		palette = new int[255];
		fire = new int [w][h];
		
		for (int x = 0; x < 255; x++) {

			palette[x] = Color.HSBtoRGB((float) ((x/255.0) / 3.0), 1, Math.min(255, (x/255.0f) * 2));
		}
	}

	public void render(Screen screen) {
		
		paletteShift += Settings.SPEED;
		

		for(int x = 0; x < w; x++) {
			fire[h - 1][x] = Math.abs(random.nextInt()) % 256;
		}
				
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				
//			    int grayScale = (int) (
//			            128.0 + (128.0 * Math.sin(x / 16.0))
//			            + 128.0 + (128.0 * Math.sin(y / 32.0))
//			            + 128.0 + (128.0 * Math.sin(Math.sqrt(((x - w / 2.0)* (x - w / 2.0) + (y - h / 2.0) * (y - h / 2.0))) / 8.0))
//			            + 128.0 + (128.0 * Math.sin(Math.sqrt((x * x + y * y)) / 8.0))
//			          ) / 2;
			    
//			    int grayScale = (int) (
//			            128.0 + (128.0 * Math.sin(Math.sqrt(((x - w / 2.0)* (x - w / 2.0) + (y - h / 2.0) * (y - h / 2.0))) / 8.0)));
				
//			    int grayScale = (int) (
//			        128.0 + (128.0 * Math.sin(x / 16.0))
//			        + 128.0 + (128.0 * Math.sin(y / 16.0)));
				
    	      fire[y][x] =
	    		  (int) ((fire[(y + 1) % h][(x - 1 + w) % w]
			        + fire[(y + 1) % h][(x) % w]
			        + fire[(y + 1) % h][(x + 1) % w]
			        + fire[(y + 2) % h][(x) % w])
    	        / 4.0);
			    screen.pixels[x + y*w] = palette[fire[y][x]];
			}
		}
		
		screen.render();
	}
}