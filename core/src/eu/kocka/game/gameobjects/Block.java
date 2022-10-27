package eu.kocka.game.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Block extends Rectangle {

    Color color = Color.WHITE;
    public boolean destroyed = false;

    public Block(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void draw(ShapeDrawer drawer){
        drawer.filledRectangle(x, y, width, height, color);
    }
}
