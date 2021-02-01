package it.sagatun.helicopter_demo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import it.sagatun.helicopter_demo.HelicopterDemo;

public class Ufo {
    public static final int GRAVITY = -5;
    private Vector3 position;


    private float sw, sh, uw, uh, speedX, speedY, countDownX, countDownY;                           // sw = screen width, sh = screen height, uw = ufo width, uh = ufo height

    private Sprite ufoSprite;

    private Rectangle ufoBounds;

    private Random rand;

    public Ufo(int x, int y) {
        rand = new Random();

        position = new Vector3(x, y, 0);

        Texture ufo = new Texture("ufo.png");
        ufoSprite = new Sprite(ufo);


        countDownX = rand.nextFloat() * 200;
        countDownY = rand.nextFloat() * 200;

        sw = HelicopterDemo.WIDTH;
        sh = HelicopterDemo.HEIGHT;
        uw = (ufoSprite.getWidth() / 7) * 1.33f;
        uh = (ufoSprite.getHeight() / 7) * 1.33f;

        ufoBounds = new Rectangle(position.x, position.y, uw, uh);

        speedX = rand.nextFloat() * 5;
        speedY = rand.nextFloat() * 5;
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(ufoBounds);
    }

    public Vector3 getPosition() {
        return position;
    }


    public Sprite getSprite() {
        return ufoSprite;
    }

    public float getUw() {
        return uw;
    }

    public float getUh() {
        return uh;
    }

    public void negateSpeed() {
        speedX *= -1;
        speedY *= -1;
    }


    public void update(float dt) {

        countDownX -= dt * 20;
        countDownY -= dt * 20;

        if (countDownX <= 0) {
            speedX = rand.nextFloat() * 10 - 5f;
            countDownX = rand.nextFloat() * 200;
        } else if (countDownY <= 0) {
            speedY = rand.nextFloat() * 10 - 5f;
            countDownY = rand.nextFloat() * 200;
        }

        if (position.x > sw - (uw - uw / 2) || position.x < 0 - uw / 2) {
            ufoSprite.flip(true, false);
            speedX = -speedX;
        }
        if (position.y > sh - uh || position.y < 0) {
            speedY = -speedY;
        }

        position.add(speedX, speedY, 0);

        ufoBounds.setPosition(position.x, position.y);
    }

}
