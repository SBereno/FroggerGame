package sbv.frogger.game.scenes;

import com.badlogic.gdx.Screen;
import sbv.frogger.game.FroggerGame;
import sbv.frogger.game.utils.Constants;

public class GameScreen implements Screen {

    FroggerGame game;
    public GameScreen(FroggerGame game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
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
