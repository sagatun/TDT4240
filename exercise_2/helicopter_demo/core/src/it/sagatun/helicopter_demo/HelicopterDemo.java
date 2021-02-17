package it.sagatun.helicopter_demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import it.sagatun.helicopter_demo.states.GameStateManager;
import it.sagatun.helicopter_demo.states.MenuState;

public class HelicopterDemo extends ApplicationAdapter {
	public static final String TITLE = "HelicopterDemo";
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 1200;


	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		Gdx.gl.glClearColor(1, 0, 0, 1);

		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
