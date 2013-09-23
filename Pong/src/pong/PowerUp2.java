package pong;

import jgame.GSprite;
import jgame.ImageCache;
import jgame.controller.PulsateController;

public class PowerUp2 extends GSprite {
	public PowerUp2(){
	super(ImageCache.forClass(Pong.class).get("shroom2.png"));
	
	PulsateController pc1 = new PulsateController(0.8, 0.2, 30);
	pc1.setProperties(PulsateController.SCALE, PulsateController.ALPHA);
	addController(pc1);
	}
}
