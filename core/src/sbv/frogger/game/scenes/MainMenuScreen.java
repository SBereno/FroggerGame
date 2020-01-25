package sbv.frogger.game.scenes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sbv.frogger.game.FroggerGame;
import sbv.frogger.game.entities.Frog;
import sbv.frogger.game.enums.GameState;
import sbv.frogger.game.utils.Constants;
import sbv.frogger.game.utils.Screens;

public class MainMenuScreen extends ScreenAdapter {

    FroggerGame game;
    Frog player;
    public static SpriteBatch batch;
    public static BitmapFont font;
    public static Music mainTheme = Constants.mainTheme;

    public MainMenuScreen(FroggerGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("ARCADE.fnt"), Gdx.files.internal("ARCADE.png"), false);
        font.setColor(Color.GREEN);
        mainTheme.setLooping(true);
        mainTheme.setVolume(.6f);
        mainTheme.play();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ENTER) {
                    game.state = GameState.RUNNING;
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
        //game.camera.update();
        //batch.setProjectionMatrix(game.camera.combined);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(Constants.backgroundTexture, 0, 0);
        batch.draw(Constants.frogTexture, player.x, player.y);
        if (game.state == GameState.TO_START) {
            font.getData().setScale(1.5f);
            font.draw(batch, "FROGGER", Constants.APP_WIDTH * .30f, Constants.APP_HEIGHT * .75f);
            font.getData().setScale(1f);
            font.draw(batch, "PULSA  INTRO  PARA  EMPEZAR", Constants.APP_WIDTH * .075f, Constants.APP_HEIGHT * .65f);
        }
        batch.end();
        if (game.state == GameState.RUNNING) {
            if (Screens.gameScreen == null)
                Screens.gameScreen = new GameScreen(game, player);
            game.setScreen(Screens.gameScreen);
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
