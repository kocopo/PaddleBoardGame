package eu.kocka.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.concurrent.RecursiveAction;


public class Paddle extends Rectangle {

    Color color = Color.WHITE;

    public Paddle(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = 20;

    }

    public void update(){
        x = Gdx.input.getX() - width / 2;
        if(x < 0){
            x = 0;
        } else if (x > Gdx.graphics.getWidth() - width ) {
            x = Gdx.graphics.getWidth() - width;
        }
    }

    public float normalize(float value){
        value = MathUtils.clamp(value, 0, width);
        if(value <= (width / 2)){
            return 1 - (value * (1 / (width / 2)));
        }else{
            return (value % (width / 2)) * (1 / (width / 2));
        }

    }

    public void draw(ShapeDrawer drawer){
        drawer.filledRectangle(x, y, width, height, color);
    }
}
