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
		fire = new int [h][w];
		
		for (int x = 0; x < 255; x++) {

			palette[x] = Color.HSBtoRGB((float) ((x/255.0) / 3.0), 1, Math.min(255, (x/255.0f) * 2));
		}
	}

	public void render(Screen screen) {
		
		//paletteShift += Settings.SPEED;
		

		for(int x = 0; x < w; x++) {
			fire[h - 1][x] = Math.abs(random.nextInt()) % 256;
		}
		
		for (int y = 0; y < h - 1; y++) {
			for (int x = 0; x < w; x++) {


			      fire[y][x] =
			    	        ((fire[(y + 1) % h][(x - 1 + w) % w]
			    	        + fire[(y + 1) % h][(x) % w]
			    	        + fire[(y + 1) % h][(x + 1) % w]
			    	        + fire[(y + 2) % h][(x) % w]) *1024) / 4097;
			}
		}
		
		for (int y = 0; y < h - 1; y++) {
		for (int x = 0; x < w; x++) {

			      
			    screen.pixels[x + y*w] = palette[fire[y][x]];
			}
		}
		
		screen.render();
	}
}