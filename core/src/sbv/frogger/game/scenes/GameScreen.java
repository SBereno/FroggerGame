package sbv.frogger.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.TimeUtils;
import sbv.frogger.game.FroggerGame;
import sbv.frogger.game.entities.Frog;
import sbv.frogger.game.enums.Axis;
import sbv.frogger.game.utils.Constants;

public class GameScreen implements Screen {

    FroggerGame game;
    Frog player;
    Sound startSound = Constants.startSound,
          victorySound = Constants.victorySound,
          deathSound = Constants.deathSound;
    int vidas = 3;
    int tiempo = 60;
    long contadorSegundos;

    public GameScreen(FroggerGame game, Frog player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                switch (keyCode) {
                    case Input.Keys.DPAD_UP:
                        player.move(Axis.Y, true);
                        break;
                    case Input.Keys.DPAD_DOWN:
                        player.move(Axis.Y, false);
                        break;
                    case Input.Keys.DPAD_RIGHT:
                        player.move(Axis.X, true);
                        break;
                    case Input.Keys.DPAD_LEFT:
                        player.move(Axis.X, false);
                        break;
                }
                return true;
            }
        });
        startSound.play();
        contadorSegundos = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(Constants.backgroundTexture, 0, 0);
        game.batch.draw(Constants.frogTexture, player.getX(), player.getY());
        mostrarVidas();
        mostrarTiempo();
        game.batch.end();
    }

    public void playerDeath() {
        deathSound.play();
        tiempo = 60;
        vidas--;
        player.x = Constants.FROG_X;
        player.y = Constants.FROG_Y;
    }

    public void mostrarVidas() {
        for (int i = 0; i < vidas; i++ ) {
            game.batch.draw(Constants.frogTexture, Constants.APP_WIDTH * .1f * i, 0);
        }
    }

    public void mostrarTiempo() {
        game.font.draw(game.batch, "TIEMPO  " + tiempo, Constants.APP_WIDTH * .65f, Constants.APP_HEIGHT * .05f);
        if (TimeUtils.timeSinceNanos(contadorSegundos) > 1000000000) {
            tiempo--;
            if (tiempo == 0) {
                playerDeath();
            }
            contadorSegundos = TimeUtils.nanoTime();
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
