package pong;

import jgame.GContainer;

public class PongGameView extends GContainer {
	public PongGameView(){
		setSize(640,480);
		// Add the paddle to the game view.
		PongPaddle paddle = new PongPaddle();
	    add(paddle);// Set the paddle's x position.
	    
	    paddle.setX(50);

	    // Set the paddle's y position.
	    paddle.setY(480 / 2);
	}

}
