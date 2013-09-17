package pong;

import java.awt.Color;
import jgame.GRootContainer;
import jgame.Game;
import jgame.ImageCache;

import jgame.Game;

public class Pong extends Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pong pongGame = new Pong();
		pongGame.startGame("Pong");

	    
	}
	public Pong() {
		ImageCache.create(Pong.class, "/pong/rsc/");
		
		GRootContainer root = new GRootContainer(Color.BLACK);
		setRootContainer(root);
		
		PongGameView gameView = new PongGameView();
		root.addView(View.GAME, gameView);
		
		GameOverView gameOverView = new GameOverView();
		root.addView(View.GAME_OVER, gameOverView);
		
		
	}
	public enum View {
		GAME, GAME_OVER;
	}

}
