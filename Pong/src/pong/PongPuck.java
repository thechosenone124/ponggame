package pong;

import java.util.List;

import jgame.Context;
import jgame.GObject;
import jgame.GSprite;
import jgame.ImageCache;
import jgame.controller.ConstantMovementController;
import jgame.listener.BoundaryRemovalListener;
import jgame.listener.HitTestListener;
import jgame.listener.ParentBoundsListener;

public class PongPuck extends GSprite {
	private ConstantMovementController cmc;
	public PongPuck() {
		super(ImageCache.forClass(Pong.class).get("soccer_ball.png"));
		
		// Create the controller.
		cmc = new ConstantMovementController(-5, Math.random() * 2 - 1);

		// Add the controller.
		addController(cmc);
		

				
				// Create the hit test listener.
				HitTestListener htl = new HitTestListener(PongPaddle.class) {
				    @Override
				    public void invoke(GObject target, Context context) {
				        flip();
				     // Get a list of all paddles hit.
				        List<PongPaddle> paddlesHit = context.hitTestClass(PongPaddle.class);
				     // Get the relevant paddle.
				        PongPaddle paddle = paddlesHit.get(0);
				        
				     // Get the vertical distance between the centers.
				        double offset = getY() - paddle.getY();
				     // Move vertically.
				        cmc.setVelocityY(cmc.getVelocityY()
				                + (offset / (paddle.getHeight() / 2)) * 5);
				        
				        
				     // Set the primitive shape to a circle.
				        setPrimitive(PrimitiveShape.CIRCLE);
				    }
				    
				    
				};
				// Create the bounce listener.
				ParentBoundsListener bounce = new ParentBoundsListener() {
				    @Override
				    public void invoke(GObject target, Context context) {
				        cmc.setVelocityY(-cmc.getVelocityY());
				    }
				};

				// Only bounce vertically.
				bounce.setValidateHorizontal(false);

				// Add the bounce listener.
				addListener(bounce);
				// Add the listener.
				addListener(htl);
				
				// Remove the puck when it's outside the game bounds.
				addListener(new BoundaryRemovalListener());
				
			}
			public void flip() {
				 // Set the x velocity to the opposite of the current value.
			    cmc.setVelocityX(-cmc.getVelocityX());
			    
			    
	    
	}
	
}
