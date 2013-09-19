package pong;

import java.util.List;

import jgame.Context;
import jgame.GContainer;
import jgame.GObject;
import jgame.GSprite;
import jgame.ImageCache;
import jgame.controller.ControlScheme;
import jgame.listener.FrameListener;
import jgame.listener.HitTestListener;

public class PongGameView extends GContainer {
	public PongGameView(){
		super(new GSprite(ImageCache.forClass(Pong.class).get("explosion.png")));
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
	    
	    FrameListener fl = new FrameListener() {
	        @Override
	        public void invoke(GObject target, Context context) {
	            // Get all the pucks.
	            List<PongPuck> pucks = context.getInstancesOfClass(PongPuck.class);
	            // Is it empty?
	            boolean noPucksLeft = pucks.isEmpty();
	         // Set the current game view.
	            if (noPucksLeft == true)
	            	{
	            	context.setCurrentGameView(Pong.View.GAME_OVER);
	            	}
	        }
	    };
	    addListener(fl);
	}

}
