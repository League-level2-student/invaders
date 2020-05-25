package Invaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
	
	public Rocketship (int x, int y, int width, int height) {
		super(x,y,width,height);
		speed = 10;
		if (needImage) {
			loadImage ("rocket.png");
		}
	}
	
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
	
	void update() {
		super.update();
		if (up == true) {
			y -= speed;
		}
		if(down == true) {
			y += speed;
		}
		if(right == true) {
			x += speed;
		}
		if(left == true) {
			x -= speed;
		}
	}
	
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y, 10, 10);
	}
}
