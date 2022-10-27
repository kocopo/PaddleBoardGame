package eu.kocka.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import eu.kocka.game.screen.MainMenuScreen;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class BreakOut extends Game {

    public BitmapFont font;
    public PolygonSpriteBatch batch;
    public ShapeDrawer drawer;

    TextureRegion textureRegion;
    Texture texture;

    @Override
    public void create() {
        createTextureRegion();
        batch = new PolygonSpriteBatch();
        drawer = new ShapeDrawer(batch, textureRegion);
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        getScreen().dispose();
        batch.dispose();
        texture.dispose();
    }

    private void createTextureRegion(){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0,0);
        texture = new Texture(pixmap);
        pixmap.dispose();
        textureRegion = new TextureRegion(texture, 0,0,1,1);
    }
}
