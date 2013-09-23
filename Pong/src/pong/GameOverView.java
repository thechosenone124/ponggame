package pong;

import jgame.GContainer;
import jgame.GSprite;
import jgame.ImageCache;

public class GameOverView extends GContainer {
	public GameOverView(){
	super(new GSprite(ImageCache.forClass(Pong.class).get("explosion.png")));
	}

}
