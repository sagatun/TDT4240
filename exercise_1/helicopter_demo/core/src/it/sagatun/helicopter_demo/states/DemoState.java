package it.sagatun.helicopter_demo.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import it.sagatun.helicopter_demo.HelicopterDemo;
import it.sagatun.helicopter_demo.sprites.Helicopter;
import it.sagatun.helicopter_demo.sprites.Ufo;

public class DemoState extends State {
    private static final int ufoCount = 4;
    private Helicopter helicopter;


    private Array<Ufo> ufos;

    private int sw, sh;

    private BitmapFont font;


    private Random rand;


    public DemoState(GameStateManager gsm) {
        super(gsm);
        sw = HelicopterDemo.WIDTH;
        sh = HelicopterDemo.HEIGHT;
        rand = new Random();

        font = new BitmapFont();
        font.getData().scale(sw / 500);

        helicopter = Helicopter.getInstance();
        ufos = new Array<>();

        for (int i = 0; i < ufoCount; i++) {
            ufos.add(new Ufo(rand.nextInt((sw - 2) + 1), rand.nextInt((sh - 2) + 1)));
        }
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        int i = 0;
        for (Ufo ufo : ufos) {
            if (ufo.collides(helicopter.getHelicopterBounds())) {
                helicopter.flipTexture();
                ufo.negateSpeed();
            }
            ufo.update(dt);

        }
        helicopter.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(MenuState.getBg(), 0, 0, sw, sh);
        font.draw(sb, "X: " + helicopter.getPosition().x + "\nY: " + helicopter.getPosition().y, sw - (sw / 6), sh - (sh / 18));
        sb.draw(helicopter.getTexture(), helicopter.getPosition().x, helicopter.getPosition().y, helicopter.getIsFlipped() ? -helicopter.getHw() : helicopter.getHw(), helicopter.getIsFlipped() ? helicopter.getHh() : helicopter.getHh());
        for (Ufo ufo : ufos) {
            sb.draw(ufo.getSprite(), ufo.getPosition().x, ufo.getPosition().y, ufo.getUw(), ufo.getUh());
        }
        sb.end();

    }

    @Override
    public void dispose() {
        MenuState.getBg().dispose();
    }
}
