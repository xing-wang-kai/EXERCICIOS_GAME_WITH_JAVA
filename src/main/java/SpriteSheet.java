import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage spriteSheet;
	
	public SpriteSheet ( String path)
	{
		try {
			this.spriteSheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage GetSprite( int x, int y, int WIDTH, int HEIGHT)
	{
		return this.spriteSheet.getSubimage(x, y, WIDTH, HEIGHT);
	}
}
