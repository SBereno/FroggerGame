package sbv.frogger.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import sbv.frogger.game.FroggerGame;
import sbv.frogger.game.utils.Constants;

import java.awt.*;

public class MainMenuScreen implements Screen {

    FroggerGame game;
    private Rectangle player;
    private TextureRegion backgroundTexture;

    public MainMenuScreen(FroggerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Falta añadir la camara
        backgroundTexture = new TextureRegion(new Texture("Background.png"), 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        player = new Rectangle();
        player.x = Constants.FROG_X;
        player.y = Constants.FROG_Y;
        player.width = Constants.APP_WIDTH;
        player.height = Constants.APP_HEIGHT;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0);
        game.batch.draw(Constants.frogTexture, player.x, player.y);
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
