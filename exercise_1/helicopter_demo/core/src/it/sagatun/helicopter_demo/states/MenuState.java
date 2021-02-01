package it.sagatun.helicopter_demo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import it.sagatun.helicopter_demo.HelicopterDemo;

public class MenuState extends State{

    private static Texture bg;
    private Texture title;
    private int sw, sh;

    private BitmapFont font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("background.png");
        title = new Texture("title.png");
        sw = HelicopterDemo.WIDTH;
        sh = HelicopterDemo.HEIGHT;
        font = new BitmapFont();
        font.getData().setScale(sw/500);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new DemoState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0,0, sw, sh);
        sb.draw(title, (sw / 2) - (title.getWidth()/2), sh / 2, sw / 2, sh / 4);
        font.draw(sb, "Touch to play", sw/2 - sw/12, sh/3);
        sb.end();
    }

    public static Texture getBg() {
        return bg;
    }

    @Override
    public void dispose() {
        //bg.dispose();
        title.dispose();
    }
}
