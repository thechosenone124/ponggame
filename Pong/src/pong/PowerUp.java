package pong;

import jgame.GSprite;
import jgame.ImageCache;
import jgame.controller.PulsateController;

public class PowerUp extends GSprite {
	public PowerUp() {
		super(ImageCache.forClass(Pong.class).get("puck.png"));
		
		PulsateController pc = new PulsateController(0.8, 0.2, 30);
		pc.setProperties(PulsateController.SCALE, PulsateController.ALPHA);
		addController(pc);
	}
}
