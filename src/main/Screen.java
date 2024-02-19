package main;

public class Screen {
	
	private int w;
	private int h;
	private int[] pixels;
	private int yOffs = 0;
	private int xOffs = 0;
	
	public void init(int width, int height) {
		this.w = width;
		this.h = width;
		this.pixels = new int[width * height];
	}
	
	public void render() {
		for (int y = 0; y < h; y++) {
			int yPix = y + yOffs;
			if (yPix < 0 || yPix >= h) continue;

			for (int x = 0; x < w; x++) {
				int xPix = x + xOffs;
				if (xPix < 0 || xPix >= w) continue;

				int src = pixels[x + y * w];
				pixels[xPix + yPix * w] = src;
			}
		}
	}

	public int[] getPixels() {
		return pixels;
	}


}
