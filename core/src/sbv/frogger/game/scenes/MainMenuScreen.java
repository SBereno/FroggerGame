package sbv.frogger.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import sbv.frogger.game.FroggerGame;
import sbv.frogger.game.entities.Frog;
import sbv.frogger.game.enums.GameState;
import sbv.frogger.game.utils.Constants;

import java.awt.*;

public class MainMenuScreen implements Screen {

    FroggerGame game;
    Frog player;
    GameState state = GameState.TO_START;
    Music mainTheme = Constants.mainTheme;

    public MainMenuScreen(FroggerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        mainTheme.setLooping(true);
        mainTheme.play();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ENTER) {
                    state = GameState.RUNNING;
                }
                return true;
            }
        });
        player = new Frog();
        player.x = Constants.FROG_X;
        player.y = Constants.FROG_Y;
        player.width = Constants.FROG_WIDTH;
        player.height = Constants.FROG_HEIGHT;
    }

    @Override
    public void render(float delta) {
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(Constants.backgroundTexture, 0, 0);
        game.batch.draw(Constants.frogTexture, player.x, player.y);
        if (state == GameState.TO_START) {
            game.font.getData().setScale(1.5f);
            game.font.draw(game.batch, "FROGGER", Constants.APP_WIDTH * .30f, Constants.APP_HEIGHT * .75f);
            game.font.getData().setScale(1f);
            game.font.draw(game.batch, "PULSA  INTRO  PARA  EMPEZAR", Constants.APP_WIDTH * .075f, Constants.APP_HEIGHT * .65f);
        }
        game.batch.end();
        if (state == GameState.RUNNING) {
            game.setScreen(new GameScreen(game, player));
        }
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
