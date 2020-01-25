package sbv.frogger.game.scenes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

import sbv.frogger.game.FroggerGame;
import sbv.frogger.game.entities.Car;
import sbv.frogger.game.entities.Frog;
import sbv.frogger.game.entities.Log;
import sbv.frogger.game.enums.Axis;
import sbv.frogger.game.enums.GameState;
import sbv.frogger.game.utils.Constants;
import sbv.frogger.game.utils.Screens;

import java.sql.Time;
import java.util.ArrayList;

public class GameScreen extends ScreenAdapter {

    ArrayList<Timer.Task> logTimers = new ArrayList<Timer.Task>();
    public static FroggerGame game;
    public static Frog player;
    public static Array<Car> listaCars;
    public static Array<Log> listaLogs;
    Sound startSound = Constants.startSound;
    Sound victorySound = Constants.victorySound;
    static Sound deathSound = Constants.deathSound;
    public static int vidas;
    public static int tiempo;
    long contadorSegundos;
    public static Sprite car1Flipped, car2Flipped;
    public static boolean isMoving = false;

    Timer.Task CarTimer = new Timer.Task() {
        @Override
        public void run() {
            Car.spawnCar1();
            Car.spawnCar2();
            Car.spawnCar3();
            Car.spawnCar4();
        }
    };

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
        Frog.lastJump = TimeUtils.nanoTime();
        startSound.play();
        contadorSegundos = TimeUtils.nanoTime();
        listaCars = new Array<Car>();
        listaLogs = new Array<Log>();
        vidas = 3;
        tiempo = 60;
        startTimer();
    }

    @Override
    public void render(float delta) {
        if (game.state == GameState.RUNNING){
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            MainMenuScreen.batch.begin();
            MainMenuScreen.batch.draw(Constants.backgroundTexture, 0, 0);
            Log.draw();
            if (!isMoving) {
                MainMenuScreen.batch.draw(Constants.frogTexture, player.getX(), player.getY());
            } else {
                MainMenuScreen.batch.draw(Constants.frogTextureJump, player.getX(), player.getY());
                if (TimeUtils.timeSinceNanos(player.lastJump) > 300000000)
                isMoving = false;
            }
            Log.move();
            Car.draw();
            Car.move();
            mostrarVidas();
            mostrarTiempo();
            MainMenuScreen.batch.end();

            if (vidas == 0 || tiempo == 0) {
                game.state = GameState.OVER;
            }
        } else if (game.state == GameState.OVER) {
            MainMenuScreen.mainTheme.stop();
            Screens.gameOverScreen = new GameOverScreen(game);
            game.setScreen(Screens.gameOverScreen);
        }
    }

    public static void playerDeath() {
        Log.playerOnLog = false;
        deathSound.play();
        vidas--;
        player.x = Constants.FROG_X;
        player.y = Constants.FROG_Y;
        Frog.lastJump = TimeUtils.nanoTime();
    }

    public void mostrarVidas() {
        for (int i = 0; i < vidas; i++ ) {
            MainMenuScreen.batch.draw(Constants.frogTexture, Constants.APP_WIDTH * .1f * i, 0);
        }
    }

    public void mostrarTiempo() {
        MainMenuScreen.font.draw(MainMenuScreen.batch, "TIEMPO  " + tiempo, Constants.APP_WIDTH * .65f, Constants.APP_HEIGHT * .05f);
        if (TimeUtils.timeSinceNanos(contadorSegundos) > 1000000000) {
            tiempo--;
            contadorSegundos = TimeUtils.nanoTime();
        }
    }

    public void startTimer(){
        try {
            Timer.schedule(CarTimer, 0f, 2.5f);
            car1Flipped = Constants.car1Sprite;
            car2Flipped = Constants.car2Sprite;
            car1Flipped.flip(true, false);
            car2Flipped.flip(true, false);
            for (int i = 0; i < 5; i++) {
                final int finalI = i;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        Log.spawnLog(1.5f, finalI + 1, 344 + (finalI * 48), finalI % 2 == 1 ?
                                Constants.log1Texture : Constants.log2Texture);
                    }
                }, 1f, ((float) Math.random() * 2) + 3);
            }
        } catch (Exception e) {}
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
