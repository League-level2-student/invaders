package Invaders;
import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	public static final int width = 500;
	public static final int height = 800;
	public static void main(String[] args) {
		LeagueInvaders invaders = new LeagueInvaders();
		invaders.setup();
	}
	
	public LeagueInvaders () {
		frame = new JFrame();
	}
	
	void setup() {
		GamePanel gpanel = new GamePanel();
		frame.addKeyListener(gpanel);
		frame.add(gpanel);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

