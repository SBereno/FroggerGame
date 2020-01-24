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

public class GameScreen extends ScreenAdapter {

    public static FroggerGame game;
    public static Frog player;
    public static Array<Car> listaCars;
    public static Array<Log> listaLogs;
    Sound startSound = Constants.startSound;
    Sound victorySound = Constants.victorySound;
    static Sound deathSound = Constants.deathSound;
    static int vidas = 3;
    int tiempo = 60;
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

    Timer.Task LogTimer = new Timer.Task() {
        @Override
        public void run() {
            Log.spawnLog1();
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
        car1Flipped = Constants.car1Sprite;
        car2Flipped = Constants.car2Sprite;
        car1Flipped.flip(true, false);
        car2Flipped.flip(true, false);
        startTimer();
    }

    @Override
    public void render(float delta) {
        if (game.state == GameState.RUNNING){
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.batch.begin();
            game.batch.draw(Constants.backgroundTexture, 0, 0);
            Log.draw();
            if (!isMoving) {
                game.batch.draw(Constants.frogTexture, player.getX(), player.getY());
            } else {
                game.batch.draw(Constants.frogTextureJump, player.getX(), player.getY());
                if (TimeUtils.timeSinceNanos(player.lastJump) > 300000000)
                isMoving = false;
            }
            Log.move();
            Car.draw();
            Car.move();
            mostrarVidas();
            mostrarTiempo();
            game.batch.end();

            if (vidas == 0 || tiempo == 0) {
                game.state = GameState.OVER;
            }
        } else if (game.state == GameState.OVER) {
            game.setScreen(new GameOverScreen(game));
        }
    }

    public static void playerDeath() {
        deathSound.play();
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
            contadorSegundos = TimeUtils.nanoTime();
        }
    }

    public void startTimer(){
        Timer.schedule(CarTimer, 0f, 2.5f);
        Timer.schedule(LogTimer, 1f, 2.5f);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
