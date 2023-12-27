package shader;

import java.awt.Color;

import main.Screen;

public class Plasma implements Shader {
	
	int w;
	int h;
	int palette[];
	int paletteShift = 0;
	public static int speed = 1;
	public static int RED = 1;
	public static int GREEN = 1;
	public static int BLUE = 1;
	
	public static String colorMode = "Blue";
	public static String[] colorModes = {"Blue", "Red", "Green"};
		
	public Plasma(int w, int h) {
		this.w = w;
		this.h = h;
		
		palette = new int[256];
		
		for (int x = 0; x < 256; x++) {

			int r = (int)((RED)*(128.0 + 128 * Math.sin(3.1415 * x / 64.0)));
			int g = (int)((GREEN)*(128.0 + 128 * Math.sin(3.1415 * x / 64.0)));
			int b = (int)((BLUE)*(128.0 + 128 * Math.sin(3.1415 * x / 128.0)));
			palette[x] = new Color(r, g, b).getRGB();
		}
	}
	
	public void render(Screen screen) {
		
		paletteShift += speed;
		 

		for (int x = 0; x < screen.w; x++) {
			for (int y = 0; y < screen.h; y++) {
				
			    int grayScale = (int) (
			            128.0 + (128.0 * Math.sin(x / 16.0))
			            + 128.0 + (128.0 * Math.sin(y / 32.0))
			            + 128.0 + (128.0 * Math.sin(Math.sqrt(((x - w / 2.0)* (x - w / 2.0) + (y - h / 2.0) * (y - h / 2.0))) / 8.0))
			            + 128.0 + (128.0 * Math.sin(Math.sqrt((x * x + y * y)) / 8.0))
			          ) / 2;
			    
//			    int grayScale = (int) (
//			            128.0 + (128.0 * Math.sin(Math.sqrt(((x - w / 2.0)* (x - w / 2.0) + (y - h / 2.0) * (y - h / 2.0))) / 8.0)));
				
//			    int grayScale = (int) (
//			        128.0 + (128.0 * Math.sin(x / 16.0))
//			        + 128.0 + (128.0 * Math.sin(y / 16.0)));
//				
//			    int grayScale = (int) (
//			            128.0 + (128.0 * Math.sin(x / 16.0))
//			            + 128.0 + (128.0 * Math.sin(y / 32.0))
//			            + 128.0 + (128.0 * Math.sin(Math.sqrt(((x - screen.w / 2.0)* (x - screen.w / 2.0) + (y - screen.h / 2.0) * (y - screen.h / 2.0))) / 8.0))
//			            + 128.0 + (128.0 * Math.sin(Math.sqrt((x * x + y * y)) / 8.0))
//			          ) / 2;
			    screen.pixels[x + y*screen.w] = palette[(grayScale + paletteShift) % 255];
			}
		}
	}
}