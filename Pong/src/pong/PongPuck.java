package pong;

import jgame.GSprite;
import jgame.ImageCache;
import jgame.controller.ConstantMovementController;

public class PongPuck extends GSprite {
	private ConstantMovementController cmc;
	public PongPuck() {
		super(ImageCache.forClass(Pong.class).get("puck.png"));
		
		// Create the controller.
		cmc = new ConstantMovementController(-5, 0);

		// Add the controller.
		addController(cmc);
	}
}
