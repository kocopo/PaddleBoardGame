package eu.kocka.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

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
        return 1 - (value % (width / 2)) * (1 / (width / 2));
    }

    public void draw(ShapeDrawer drawer){
        drawer.filledRectangle(x, y, width, height, color);
    }
}
