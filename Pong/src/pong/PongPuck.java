package pong;

import java.util.List;
import java.util.Random;

import jgame.Context;
import jgame.GObject;
import jgame.GSprite;
import jgame.ImageCache;
import jgame.controller.ConstantMovementController;
import jgame.listener.BoundaryRemovalListener;
import jgame.listener.FrameListener;
import jgame.listener.HitTestListener;
import jgame.listener.ParentBoundsListener;

public class PongPuck extends GSprite {
	private ConstantMovementController cmc;
	private double bounceFactor = 5;
	private boolean lastBounceWasLeft;
	private int flipY = 1;

	public PongPuck() {
		super(ImageCache.forClass(Pong.class).get("soccer_ball.png"));
		// Create the controller.

		boolean goRight = new Random().nextBoolean();
		double vx = goRight ? 5 : -5;
		cmc = new ConstantMovementController(vx, Math.random() * 2 - 1);

		lastBounceWasLeft = goRight;

		// Add the controller.
		addController(cmc);
		
		

		HitTestListener htl2 = new HitTestListener(PowerUp.class) {

			@Override
			public void invoke(GObject target, Context context) {
				// Get the current velocity.
				double vx = cmc.getVelocityX();

				// Test the sign.
				if (vx > 0) {
				    vx += 2.5;
				} else if (vx < 0) {
				    vx -= 7.5;
				} else {
				    // It's zero; do nothing.
				}

				// Set the velocity.
				cmc.setVelocityX(vx);
				bounceFactor = bounceFactor * -1;
				List<PowerUp> hit = context.hitTestClass(PowerUp.class);
				for (PowerUp p : hit) {
					p.removeSelf();
				}
				
			}
		};
		
		HitTestListener htl3 = new HitTestListener(PowerUp2.class) {

			@Override
			public void invoke(GObject target, Context context) {
				// Get the current velocity.
				double vx = cmc.getVelocityX();

				// Test the sign.
				if (vx > 0) {
				    vx += 7.5;
				} else if (vx < 0) {
				    vx -= 2.5;
				} else {
				    // It's zero; do nothing.
				}

				// Set the velocity.
				cmc.setVelocityX(vx);
				
				flipY = flipY * -1;
				List<PowerUp2> hit = context.hitTestClass(PowerUp2.class);
				for (PowerUp2 p2 : hit) {
					p2.removeSelf();
				}
			}
		};

		// Create the hit test listener.
		HitTestListener htl = new HitTestListener(PongPaddle.class) {
			@Override
			public void invoke(GObject target, Context context) {
				// Are we to the right of the center?
				boolean rightOfCenter = (getX() > getParent().getWidth() / 2);

				// Can we bounce?
				if (lastBounceWasLeft ^ rightOfCenter) {
					// No, we can't bounce.
					return;
				}
				flip();
				lastBounceWasLeft = !lastBounceWasLeft;
				// Get a list of all paddles hit.
				List<PongPaddle> paddlesHit = context
						.hitTestClass(PongPaddle.class);
				// Get the relevant paddle.
				PongPaddle paddle = paddlesHit.get(0);

				// Get the vertical distance between the centers.
				double offset = getY() - paddle.getY();
				// Move vertically.
				cmc.setVelocityY(cmc.getVelocityY()
						+ (offset / (paddle.getHeight() / 2)) * bounceFactor);

				// Set the primitive shape to a circle.
				setPrimitive(PrimitiveShape.CIRCLE);
			}

		};
		// Create the bounce listener.
		ParentBoundsListener bounce = new ParentBoundsListener() {
			@Override
			public void invoke(GObject target, Context context) {
				cmc.setVelocityY(-1 * cmc.getVelocityY());
			}
		};

		// Create an acceleration listener.
		FrameListener accelerate = new FrameListener() {
			@Override
			public void invoke(GObject target, Context context) {
				// Get the current velocity.
				double vx = cmc.getVelocityX();

				// Test the sign.
				if (vx > 0) {
					vx += 0.001;
				} else if (vx < 0) {
					vx -= 0.001;
				} else {
					// It's zero; do nothing.
				}

				// Set the velocity.
				cmc.setVelocityX(vx);
			}
		};

		// Only bounce vertically.
		bounce.setValidateHorizontal(false);

		// Add the bounce listener.
		addListener(bounce);
		// Add the listener.
		addListener(htl);
		// Add thewsws listener.
		addListener(accelerate);
		// Remove the puck when it's outside the game bounds.
		addListener(new BoundaryRemovalListener());
		// Add powerup listener
		addListener(htl2);
		//add powerup2 listener
		addListener(htl3);

	}

	public void flip() {
		// Set the x velocity to the opposite of the current value.
		cmc.setVelocityX(-cmc.getVelocityX());

		cmc.setVelocityY(flipY * cmc.getVelocityY());

	};

}
