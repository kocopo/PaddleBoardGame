package eu.kocka.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import eu.kocka.game.BreakOut;
import eu.kocka.game.gameobjects.Ball;
import eu.kocka.game.gameobjects.Block;
import eu.kocka.game.gameobjects.Paddle;

public class GameScreen implements Screen {

    BreakOut game;
    Ball ball;
    Paddle paddle;
    Array<Block> blocks = new Array<>();

    OrthographicCamera camera;

    public GameScreen(BreakOut game) {
        this.game = game;

        ball = new Ball(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 , 15, 5, 5);
        paddle = new Paddle(100, 20, 100);

        int blockWidth = 63;
        int blockHeight = 20;
        for(int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 10){
            for(int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10){
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();


        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        ball.update();
        ball.checkPaddleCollision(paddle);
        ball.draw(game.drawer);
        paddle.update();
        paddle.draw(game.drawer);
        for(Block block : blocks){
            ball.checkBlockCollision(block);
            if(!block.destroyed) block.draw(game.drawer);
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
