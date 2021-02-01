package it.sagatun.pong.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

import it.sagatun.pong.Pong;

public class Ball {
    private Texture ball;
    private Vector2 position;
    private Rectangle boundsBall;


    private float speedX, speedY, sw,sh,bw,bh;

    public Ball() {
        ball = new Texture("Ball.png");
        position = new Vector2(Pong.WIDTH/2, Pong.HEIGHT/2);
        boundsBall = new Rectangle(position.x, position.y, ball.getWidth(), ball.getHeight());

        sw = Pong.WIDTH;sh = Pong.HEIGHT;
        bw = ball.getWidth();bh = ball.getHeight();

        speedX = 10;
        speedY = 10;

    }

    public void update(float dt){
        if(position.x > sw - (bw - bw/2)) {
            speedX = -speedX;
            Pong.p1score++;
        }
        if(position.x < 0 - bw/2){
            speedX = -speedX;
            Pong.p2score++;
        }
        if(position.y > sh - bh || position.y < 0){
            speedY = - speedY;
        }
        position.add(speedX, speedY);

        boundsBall.setPosition(position.x,position.y);
    }

    public void update(float dt, boolean isHit){
        if(position.x > sw - (bw - bw/2) || position.x < 0 - bw/2){
            speedX = - speedX;
        }
        if(position.y > sh - bh || position.y < 0){
            speedY = - speedY;
        }

        speedX *= -1;speedY *= -1;

        position.add(speedX, speedY);

        boundsBall.setPosition(position.x,position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void render(){
    }

    public Texture getBall() {
        return ball;
    }

    public Rectangle getBoundsBall() {
        return boundsBall;
    }

    public void dispose(){
        ball.dispose();
    }



}

