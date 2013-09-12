package pong;

import jgame.GSprite;
import jgame.ImageCache;


public class PongPaddle extends GSprite {
	public PongPaddle() {
		super(ImageCache.forClass(Pong.class).get("paddle.png"));
	}

}
