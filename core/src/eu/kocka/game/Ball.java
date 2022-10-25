package eu.kocka.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import space.earlygrey.shapedrawer.ShapeDrawer;


public class Ball extends Circle {
    float xSpeed, ySpeed, maxXSpeed;
    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.radius = size;
        this.maxXSpeed = xSpeed;
    }

    public void update(){
        x += xSpeed;
        y += ySpeed;
        if(x < radius || x > Gdx.graphics.getWidth() - radius){
            xSpeed = -xSpeed;
        }
        if(y < radius || y > Gdx.graphics.getHeight() - radius){
            ySpeed = -ySpeed;
        }
    }

    public void checkPaddleCollision(Paddle paddle){
        if(Intersector.overlaps(this, paddle)){
            ySpeed = -ySpeed;
            xSpeed = (xSpeed > 0 ? 1 : -1) * maxXSpeed * paddle.normalize(getCollisionX(paddle));
            y = paddle.y + paddle.height + radius;
        }
    }

    public void checkBlockCollision(Block block){
        if(Intersector.overlaps(this, block) && !block.destroyed){
            ySpeed = -ySpeed;
            block.destroyed = true;
        }
    }


    private float getCollisionX(Rectangle rectangle){
        return this.x - rectangle.x;
    }

    public void draw(ShapeDrawer drawer){
        drawer.filledCircle(x, y, radius, color);
    }
}
