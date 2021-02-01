package it.sagatun.pong.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import it.sagatun.pong.Pong;

public class Paddle {
    private Texture paddle;
    private Vector2 position;
    private Rectangle boundsPaddle;
    private boolean isBot;
    private float speedX, speedY, sw, sh, pw, ph;

    public Paddle(float x, boolean isBot) {
        this.isBot = isBot;
        paddle = new Texture("Paddle.png");
        pw = paddle.getWidth();ph = paddle.getHeight();
        position = new Vector2(x, Pong.HEIGHT/2);
        sw = Pong.WIDTH;sh = Pong.HEIGHT;
        speedX = 15;
        speedY = 15;

        boundsPaddle = new Rectangle(position.x, position.y, paddle.getWidth(), paddle.getHeight());

    }

    public void update(float dt, Ball ball){
        if(position.x > sw - (pw - pw/2) || position.x < 0 - pw/2){
            speedX = - speedX;
        }
        if(position.y > sh - ph || position.y < 0){
            speedY = - speedY;
        }
        if(!isBot) {
            if (Gdx.input.justTouched()) {
                speedY = -speedY;
            }
            if (Gdx.input.isTouched()) {
                position.y += speedY;
            }
        }
        if(isBot) {
            position.y = ball.getPosition().y;
        }

        boundsPaddle.setPosition(position.x, position.y);
    }

    public void render(){

    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose(){
        paddle.dispose();
    }

    public Texture getPaddle() {
        return paddle;
    }

    public boolean collide(Rectangle player){
        return player.overlaps(boundsPaddle);
    }
}
