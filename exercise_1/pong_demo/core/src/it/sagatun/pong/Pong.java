package it.sagatun.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import it.sagatun.pong.sprites.Ball;
import it.sagatun.pong.sprites.Paddle;

public class Pong extends ApplicationAdapter {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Pong";

	private BitmapFont font;

	private int state;

	private Texture bg;
	private SpriteBatch batch;
	private BitmapFont p1scoreStr, p2scoreStr;
	public static int p1score, p2score;

	private Paddle player1;
	private Paddle player2;
	private Ball ball;

	private float dt;




	@Override
	public void create () {
		state = 0;
		batch = new SpriteBatch();
		bg = new Texture("background.png");
		ball = new Ball();
		player1 = new Paddle(0 + WIDTH/30, true);
		player2 = new Paddle(WIDTH - WIDTH/20, false);
		dt = Gdx.graphics.getDeltaTime();

		p1scoreStr = new BitmapFont();
		p1scoreStr.getData().scale(3);
		p2scoreStr = new BitmapFont();
		p2scoreStr.getData().scale(3);
		p1score = 0;
		p2score = 0;


	}

	public void handleInput(){

	}

	public void update(){
		handleInput();
		ball.update(dt);
		player1.update(dt, ball);
		player2.update(dt, ball);
		if(player1.collide(ball.getBoundsBall())){
			ball.update(dt, true);
			p1score += 1;
		}
		if(player2.collide(ball.getBoundsBall())){
			ball.update(dt,true);
			p2score += 1;
		}
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg,0,0);
		batch.draw(ball.getBall(),ball.getPosition().x,ball.getPosition().y);
		batch.draw(player1.getPaddle(),player1.getPosition().x,player1.getPosition().y);
		batch.draw(player2.getPaddle(),player2.getPosition().x,player2.getPosition().y);
		p1scoreStr.draw(batch, ""+ p1score, 0 + WIDTH/6, HEIGHT - (HEIGHT/10));
		p2scoreStr.draw(batch, ""+ p2score, WIDTH - WIDTH/6, HEIGHT - (HEIGHT/10));
		batch.end();


	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
