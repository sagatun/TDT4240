package it.sagatun.helicopter_demo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import it.sagatun.helicopter_demo.HelicopterDemo;

public class Helicopter {
    public static final int GRAVITY = -5;
    private Vector3 position;
    private Vector3 velocity;
    private boolean isFlipped = false;

    private float sw, sh, hw, hh, speedX, speedY;

    //private Texture helicopter;
    private Texture texture;
    private Animation helicopterAnimation;

    private Rectangle helicopterBounds;

    public Helicopter(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        //helicopter = new Texture("helicopter_1.png");
        texture = new Texture("helicopter.png");
        helicopterAnimation = new Animation(new TextureRegion((texture)), 8, 0.5f);
        sw = HelicopterDemo.WIDTH;
        sh = HelicopterDemo.HEIGHT;
        hw = texture.getWidth() / 8;
        hh = texture.getHeight();

        helicopterBounds = new Rectangle(position.x, position.y, hw, hh);

        speedX = sw / 100;
        speedY = sh / 100;
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

    public boolean getIsFlipped(){
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

        if(position.x > sw - (hw - hw / 2) || position.x < 0 - hw / 2){
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
