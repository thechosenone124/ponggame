package pong;

import jgame.GSprite;
import jgame.ImageCache;
import jgame.controller.ControlScheme;
import jgame.controller.KeyboardLocationController;


public class PongPaddle extends GSprite {
	public PongPaddle(ControlScheme cs) {
		super(ImageCache.forClass(Pong.class).get("stick_figure.png"));
		// Create a keyboard movement controller.
		KeyboardLocationController klc = new KeyboardLocationController(cs,10);
		// Add the new controller.
		addController(klc);
		// Disable horizontal movement.
		klc.setHorizontalAllowed(false);
	}
	

}
