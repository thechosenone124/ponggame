package pong;

import jgame.Context;
import jgame.GContainer;
import jgame.GObject;
import jgame.controller.ControlScheme;
import jgame.listener.HitTestListener;

public class PongGameView extends GContainer {
	public PongGameView(){
		setSize(640,480);
		// Add the paddle to the game view.
		PongPaddle paddle = new PongPaddle(ControlScheme.WASD);
	    add(paddle);
	    
	    paddle.setLocation(50, 480/2);
	    
	 // Create a puck.
	    PongPuck puck = new PongPuck();

	    // Add the puck.
	    addAtCenter(puck);
	    
	 // Create another paddle to add.
	    PongPaddle paddle2 = new PongPaddle(ControlScheme.ARROW_KEYS);
	    add(paddle2);

	    // Set the paddle's location.
	    paddle2.setLocation(640 - 50, 480 / 2);
	}

}
