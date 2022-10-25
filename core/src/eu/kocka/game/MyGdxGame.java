package eu.kocka.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class MyGdxGame extends ApplicationAdapter {

	PolygonSpriteBatch batch;
	ShapeDrawer drawer;
	Ball ball;
	Paddle paddle;
	TextureRegion textureRegion;
	Texture texture;
	Array<Block> blocks = new Array<>();


	@Override
	public void create() {
		batch = new PolygonSpriteBatch();
		createTextureRegion();
		drawer = new ShapeDrawer(batch, textureRegion);
		ball = new Ball(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 , 15, 5, 5);
		paddle = new Paddle(100, 20, 100);

		int blockWidth = 63;
		int blockHeight = 20;
		for(int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 10){
			for(int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10){
				blocks.add(new Block(x, y, blockWidth, blockHeight));
			}
		}
	}


	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		ball.update();
		ball.checkPaddleCollision(paddle);
		ball.draw(drawer);
		paddle.update();
		paddle.draw(drawer);
		for(Block block : blocks){
			ball.checkBlockCollision(block);
			if(!block.destroyed) block.draw(drawer);
		}
		batch.end();


	}

	@Override
	public void dispose() {
		super.dispose();
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
