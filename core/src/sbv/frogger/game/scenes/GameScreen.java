package sbv.frogger.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import sbv.frogger.game.FroggerGame;
import sbv.frogger.game.entities.Frog;
import sbv.frogger.game.enums.Axis;
import sbv.frogger.game.utils.Constants;

public class GameScreen implements Screen {

    FroggerGame game;
    Frog player;

    public GameScreen(FroggerGame game, Frog player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(Constants.backgroundTexture, 0, 0);
        game.batch.draw(Constants.frogTexture, player.getX(), player.getY());
        player.move(Axis.Y, true);
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
