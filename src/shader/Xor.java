package shader;

import java.awt.Color;
import java.util.Random;

import main.Screen;

public class Xor {
	
	int w;
	int h;
	int palette[];
	int fire[][];
	int paletteShift = 0;
	Random random;
	
	int shiftX = 0;
	int shiftY = 0;
	
	public Xor(int w, int h) {
		
		this.w = w;
		this.h = h;
	}

	public void render(Screen screen) {
		random = new Random();
		
		for(int y = 0; y < h; y++) {
		    for(int x = 0; x < w; x++) {
				    
			    int c = (x | y) % 255;

			    screen.pixels[x + y * w]  = new Color(c, c, c).getRGB();
			  }
		 }
		
		screen.render();
	}
}