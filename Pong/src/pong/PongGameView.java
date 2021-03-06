package pong;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jgame.Context;
import jgame.GContainer;
import jgame.GObject;
import jgame.GSprite;
import jgame.ImageCache;
import jgame.controller.ControlScheme;
import jgame.controller.MouseRotationController;
import jgame.listener.FrameListener;
import jgame.listener.TimerListener;

public class PongGameView extends GContainer {
	public PongGameView() {
		super(new GSprite(ImageCache.forClass(Pong.class).get(
				"happy_background.png")));
		setSize(640, 480);
		// Add the paddle to the game view.
		PongPaddle paddle = new PongPaddle(ControlScheme.WASD);
		add(paddle);

		paddle.setLocation(50, 480 / 2);

		// Create a puck.
		PongPuck puck = new PongPuck();

		// Add the puck.
		addAtCenter(puck);

		// Create another paddle to add.
		PongPaddle paddle2 = new PongPaddle(ControlScheme.ARROW_KEYS);
		add(paddle2);
		

		// Set the paddle's location.
		paddle2.setLocation(640 - 50, 480 / 2);

		TimerListener tl3 = new TimerListener(30 * 10 ) {

			@Override
			public void invoke(GObject target, Context context) {
				Collection<GObject> children = new ArrayList<>(getObjects());
				for (GObject someChild : children) {
					if (someChild instanceof PowerUp) {
						someChild.removeSelf();
					}
				}
				PowerUp unpredictable = new PowerUp();
				addAt(unpredictable, Math.random() * 360 + 140,Math.random() * 380 + 50);
				
				// Math.random() * a + b
				// random number between b and a+b
			}

		};
		TimerListener tl4 = new TimerListener(30 * 10 + 150) {

			@Override
			public void invoke(GObject target, Context context) {
				Collection<GObject> children = new ArrayList<>(getObjects());
				for (GObject someChild : children) {
					if (someChild instanceof PowerUp2) {
						someChild.removeSelf();
					}
				}
				PowerUp2 predictable = new PowerUp2();
				addAt(predictable, Math.random() * 360 + 140,Math.random() * 380 + 50);
			}
		};

		FrameListener fl = new FrameListener() {
			@Override
			public void invoke(GObject target, Context context) {
				// Get all the pucks.
				List<PongPuck> pucks = context
						.getInstancesOfClass(PongPuck.class);
				// Is it empty?
				boolean noPucksLeft = pucks.isEmpty();
				// Set the current game view.
				if (noPucksLeft == true) {
					context.setCurrentGameView(Pong.View.GAME_OVER);
				}
			}
		};
		addListener(fl);
		addListener(tl3);
		addListener(tl4);
	}

}
