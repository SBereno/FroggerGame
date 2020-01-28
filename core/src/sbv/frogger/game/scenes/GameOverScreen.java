package sbv.frogger.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import sbv.frogger.game.FroggerGame;
import sbv.frogger.game.enums.GameState;
import sbv.frogger.game.utils.Constants;
import sbv.frogger.game.utils.Screens;

public class GameOverScreen extends ScreenAdapter {

    FroggerGame game;
    long timer;
    String text;
    boolean won;
    String returnedScore;

    public GameOverScreen(FroggerGame game, String text, boolean won) {
        this.game = game;
        this.text = text;
        this.won = won;
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
            MainMenuScreen.font.draw(MainMenuScreen.batch, text,Constants.APP_WIDTH * .275f, Constants.APP_HEIGHT * .75f);
            MainMenuScreen.font.getData().setScale(1f);
            if (won) {
                MainMenuScreen.font.draw(MainMenuScreen.batch, (GameScreen.vidas * 50 + GameScreen.tiempo) + "  PUNTOS", Constants.APP_WIDTH * .35f, Constants.APP_HEIGHT * .65f);
                leerScore();
                if (GameScreen.vidas * 50 + GameScreen.tiempo > Integer.parseInt(returnedScore)) {
                    guardarScore(GameScreen.vidas * 50 + GameScreen.tiempo);
                }
            }
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

    private void guardarScore(int score) {
        Constants.file.writeString(Integer.toString(score), false);
    }

    private void leerScore() {
        returnedScore = Constants.file.readString();
    }

    @Override
    public void hide() {

    }

}
