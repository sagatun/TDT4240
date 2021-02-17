package it.sagatun.helicopter_demo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

import it.sagatun.helicopter_demo.HelicopterDemo;
import it.sagatun.helicopter_demo.Observer;
import it.sagatun.helicopter_demo.Subject;

public class Helicopter implements Subject {

    private ArrayList<Observer> observers = new ArrayList<>();

    private static Random rand = new Random();
    private static volatile Helicopter instance = null;    // Singleton Pattern.  Make sure only one instance of Helicopter is made.

    private Vector3 position;
    private boolean isFlipped = false;

    private float sw, sh, hw, hh, speedX, speedY;

    private Texture texture;
    private Animation helicopterAnimation;

    private Rectangle helicopterBounds;

    private Helicopter() {                     // private constructor because of singleton
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
        // lazy Singleton. Only instantiate when needed
        if(instance == null){
            synchronized (Helicopter.class){
                if(instance == null){
                    instance = new Helicopter();
                }
            }
        }
        return instance;
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

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyUpdate() {
        for(Observer o: observers){
            o.update();
        }
    }
}
