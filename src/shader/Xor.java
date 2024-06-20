package shader;

import java.awt.Color;

import main.Screen;
import main.Settings;

public class Xor implements Shader {
	
	private int w, h;
	
	public Xor(int w, int h) {
		this.w = w;
		this.h = h;
	}

	public void render(Screen screen) {
		
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
		    	screen.getPixels()[x + y * w]  = new Color(r, g, b).getRGB();
		    }
		}
		screen.render();
	}
}