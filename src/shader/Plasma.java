package shader;

import java.awt.Color;

import main.Screen;
import main.Settings;

public class Plasma implements Shader {
	
	private int w, h;
	private int palette[];
	private int paletteShift = 0;
		
	public Plasma(int w, int h) {
		this.w = w;
		this.h = h;
		
		init();
	}
	
	private void init() {
		palette = new int[256];
		
		for (int x = 0; x < 256; x++) {
			
			int r = 0;
			int g = 0;
			int b = 0;

			if (Settings.RED) {
				r = (int)(128.0 + 128 * Math.sin(3.1415 * x / 64.0));
			}
			if (Settings.GREEN) {
				g = (int)(128.0 + 128 * Math.sin(3.1415 * x / 64.0));
			}
			if (Settings.BLUE) {
				b = (int)(128.0 + 128 * Math.sin(3.1415 * x / 128.0));
			}
			
			palette[x] = new Color(r, g, b).getRGB();
		}
	}
	
	public void render(Screen screen) {
		
		paletteShift += Settings.SPEED;
		 

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				
				int grayScale = 0;
				
				switch (Settings.PATTERN) {
				case 1:
				
				    grayScale = (int) (
				            128.0 + (128.0 * Math.sin(x / 16.0))
				            + 128.0 + (128.0 * Math.sin(y / 32.0))
				            + 128.0 + (128.0 * Math.sin(Math.sqrt(((x - w / 2.0)* (x - w / 2.0) + (y - h / 2.0) * (y - h / 2.0))) / 8.0))
				            + 128.0 + (128.0 * Math.sin(Math.sqrt((x * x + y * y)) / 8.0))
				          ) / 2;
				    break;
				    
				case 2:
				    
				    grayScale = (int) (
				            128.0 + (128.0 * Math.sin(Math.sqrt(((x - w / 2.0)* (x - w / 2.0) + (y - h / 2.0) * (y - h / 2.0))) / 8.0)));
				    break;
				case 3:
				    grayScale = (int) (
				        128.0 + (128.0 * Math.sin(x / 16.0))
				        + 128.0 + (128.0 * Math.sin(y / 16.0)));
					break;
				case 4:
				    grayScale = (int) (
				            128.0 + (128.0 * Math.sin(x / 16.0))
				            + 128.0 + (128.0 * Math.sin(y / 8.0))
				            + 128.0 + (128.0 * Math.sin(Math.sqrt(((x - w / 2.0)* (x - w / 2.0) + (y - h / 2.0) * (y - h / 2.0))) / 16.0))
				            + 128.0 + (128.0 * Math.sin(Math.sqrt((x * x + y * y)) / 8.0))
				          ) / 2;
				    break; 
				}
				screen.getPixels()[x + y*w] = palette[(grayScale + paletteShift) % 255];
			}
		}
	}
}