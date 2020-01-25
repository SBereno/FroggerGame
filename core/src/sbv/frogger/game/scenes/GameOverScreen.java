package sbv.frogger.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import sbv.frogger.game.FroggerGame;
import sbv.frogger.game.enums.GameState;
import sbv.frogger.game.utils.Constants;
import sbv.frogger.game.utils.Screens;

public class GameOverScreen extends ScreenAdapter {

    FroggerGame game;
    long timer;
    public GameOverScreen(FroggerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        timer = System.currentTimeMillis();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        MainMenuScreen.batch.begin();
        MainMenuScreen.batch.draw(Constants.backgroundTexture, 0, 0);
        if (game.state == GameState.OVER) {
            MainMenuScreen.font.getData().setScale(1.5f);
            MainMenuScreen.font.draw(MainMenuScreen.batch, "GAME OVER", Constants.APP_WIDTH * .275f, Constants.APP_HEIGHT * .75f);
            MainMenuScreen.font.getData().setScale(1f);
            MainMenuScreen.font.draw(MainMenuScreen.batch, (GameScreen.vidas * 50 + GameScreen.tiempo) + " PUNTOS", Constants.APP_WIDTH * .35f, Constants.APP_HEIGHT * .65f);
        }
        MainMenuScreen.batch.end();

        if (game.state == GameState.TO_START) {
            MainMenuScreen.batch = null;
            MainMenuScreen.font = null;
            game.setScreen(Screens.mainMenuScreen);
        }

        if(System.currentTimeMillis()>timer+4000){
            game.state = GameState.TO_START;
        }
    }

    @Override
    public void hide() {

    }

}
