package it.sagatun.helicopter_demo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import it.sagatun.helicopter_demo.HelicopterDemo;

public class Helicopter {

    private static Random rand = new Random();
    private static final Helicopter INSTANCE = new Helicopter();    // Singleton Pattern.  Make sure only one instance of Helicopter is made.

    private Vector3 position;
    private boolean isFlipped = false;

    private float sw, sh, hw, hh, speedX, speedY;

    private Texture texture;
    private Animation helicopterAnimation;

    private Rectangle helicopterBounds;

    private Helicopter() {
        sw = HelicopterDemo.WIDTH;
        sh = HelicopterDemo.HEIGHT;
        position = new Vector3(rand.nextInt((int) sw - 1) + 1, rand.nextInt((int) sh - 1) + 1, 0);

        texture = new Texture("helicopter.png");
        helicopterAnimation = new Animation(new TextureRegion((texture)), 8, 0.5f);


        hw = texture.getWidth() / 8;
        hh = texture.getHeight();

        helicopterBounds = new Rectangle(position.x, position.y, hw, hh);

        speedX = sw / 100;
        speedY = sh / 100;
    }

    public static Helicopter getInstance() {
        return INSTANCE;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return helicopterAnimation.getFrame();
    }


    public void negateSpeed() {
        speedX *= -1;
        speedY *= -1;
    }

    public void flipTexture() {
        System.out.println("flip");
        isFlipped = !isFlipped;
        negateSpeed();
    }

    public boolean getIsFlipped() {
        return isFlipped;
    }

    public float getHh() {
        return hh;
    }

    public float getHw() {
        return hw;
    }

    public Rectangle getHelicopterBounds() {
        return helicopterBounds;
    }

    public void update(float dt) {
        helicopterAnimation.update(dt);

        if (position.x > sw - (hw - hw / 2) || position.x < 0 - hw / 2) {
            flipTexture();
        }

        if (position.y > sh - hh || position.y < 0) {
            speedY = -speedY;
        }
        if (Gdx.input.justTouched()) {
            speedY = -speedY;
        }
        if (Gdx.input.isTouched()) {
            position.y += speedY;
        }

        position.add(speedX, 0, 0);

        helicopterBounds.setPosition(position.x, position.y);

    }

    public void dispose() {
        texture.dispose();
    }
}
