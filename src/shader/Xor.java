package shader;

import java.awt.Color;
import java.util.Random;

import main.Screen;
import main.Settings;

public class Xor {
	
	private int w;
	private int h;
	private int palette[];
	private int fire[][];
	private int paletteShift = 0;
	private Random random;
	
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
				    
		    	int c = 0;
		    	
		    	switch (Settings.PATTERN) {
		    	case 1:
		    		c = x ^ y;
		    		break;
		    		
		    	case 2:
		    		c = x | y;
		    		break;
		    	
		    	case 3:
		    		c = x & y;
		    		break;
		    	
//		    	case 4:
//					c = Color.HSBtoRGB((x & y)/255.0f, 1, 1);
//					System.out.println(c);
//
		    	}	
		    	
	    		int r = c, g = c, b = c;
		    	if (Settings.RED) {
		    		r = 255 - c;
		    	}
		    	if (Settings.GREEN) {
		    		g = 255 - c;
		    	}
		    	if (Settings.BLUE) {
		    		b = 255 - c;
		    	}
			    	screen.pixels[x + y * w]  = new Color(r, g, b).getRGB();
//		    	} else {
//		    		screen.pixels[x + y * w]  = c;
//		    	}
		    

		    }
		}
		screen.render();
	}
}