package Invaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	Font titleFont;
	Font instructionFont;
	Timer frameDraw;
	Timer alienSpawn;
	Rocketship rocket = new Rocketship (250, 700, 50, 50);
	ObjectManager manager = new ObjectManager(rocket);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	
	int currentState = MENU;
	
	public GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		instructionFont = new Font ("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream("space.png"));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	}
	
	
	void updateMenuState() {
		
	}
	
	void updateGameState() {
		manager.update();
		manager.draw(getGraphics());
		if (rocket.isActive == false) {
			currentState = END;
	    	rocket = new Rocketship(250, 700, 50, 50);
	    	manager = new ObjectManager(rocket);
		}
	}
	
	void updateEndState() {
		
	}
	
	void drawMenuState (Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 25, 100);
		g.setFont(instructionFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to start", 125, 400);
		g.setFont(instructionFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press SPACE for instructions", 85, 600);
	}
	
	void drawGameState (Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawImage(image, 0, 0, LeagueInvaders.width, LeagueInvaders.height, null);
		manager.draw(g);
		
	}
	
	void drawEndState (Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 100, 100);
		g.setFont(instructionFont);
		g.setColor(Color.YELLOW);
		g.drawString("You killed " + manager.getScore() + " enemies", 125, 400);
		g.setFont(instructionFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to restart", 85, 600);
		
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
//		System.out.println("action");
		repaint();
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		    	alienSpawn.stop();
		    	rocket = new Rocketship(250, 700, 50, 50);
		    	manager = new ObjectManager(rocket);
		    	rocket.isActive = true;
		        currentState = MENU;
		    } else {
		        currentState++;
		        startGame();
		    }
		}  
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    rocket.up = true;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    rocket.down = true;
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    rocket.right = true;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    rocket.left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			manager.addProjectile(rocket.getProjectile());
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    rocket.up = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    rocket.down = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    rocket.right = false;
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    rocket.left = false;
		}
	}
	
	void startGame() {
		alienSpawn = new Timer(1000, manager);
		alienSpawn.start();
	}
	
}
