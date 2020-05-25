package Invaders;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Rocketship rocket;
	ArrayList<Projectile> bullets = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random rand = new Random();
	int score =  0;
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public ObjectManager (Rocketship rocket) {
		this.rocket = rocket;
	}
	
	
	void addProjectile(Projectile proj) {
		bullets.add(proj);
	}
	
	void addAliens() {
		aliens.add(new Alien(rand.nextInt(LeagueInvaders.width), 0,50, 50));
	}
	
	void update() {
		rocket.update();
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
		}
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).y >= LeagueInvaders.height) {
				aliens.get(i).isActive = false;
			}
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).y >= LeagueInvaders.height) {
				bullets.get(i).isActive = false;
			}
  		}
		checkCollision();
		purgeObjects();
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
	}
	
	void purgeObjects () {
		for (int i = aliens.size()-1; i >= 0; i--) {
			if (aliens.get(i).isActive == false) {  
				aliens.remove(i);
			}
		}
		for (int i = bullets.size()-1; i >= 0;i--) {
			if (bullets.get(i).isActive == false) { 
				bullets.remove(i);
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAliens();
	}
	
	void checkCollision() {
		for (int i = 0; i < aliens.size(); i++) {
			if(aliens.get(i).collisionBox.intersects(rocket.collisionBox)) {
				rocket.isActive = false;
			}
			for (int j = 0; j < bullets.size(); j++) {
				if(aliens.get(i).collisionBox.intersects(bullets.get(j).collisionBox)) {
					aliens.get(i).isActive = false;
					bullets.get(j).isActive = false;
					score ++;
				}
			}
		}
	}
}
