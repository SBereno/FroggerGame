package sbv.frogger.game.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import sbv.frogger.game.scenes.GameScreen;
import sbv.frogger.game.scenes.MainMenuScreen;
import sbv.frogger.game.utils.Constants;

import java.util.Iterator;

public class Log extends Rectangle {

    private float speed;
    private int row;
    public static boolean playerOnLog = false;
    private Boolean placed = false;
    Texture texture;

    public Log(float speed, int row, Texture texture) {
        this.speed = speed;
        this.row = row;
        this.texture = texture;
    }

    public static void move() {
        Boolean isGoingToDie = true;
        Log logOverlapped = null;
        for (Iterator<Log> iterLog = GameScreen.listaLogs.iterator(); iterLog.hasNext(); ) {
            Log log1 = iterLog.next();

            if (log1.overlaps(GameScreen.player)) {
                playerOnLog = true;
                isGoingToDie = false;
                logOverlapped = log1;
            }

            if (playerOnLog && logOverlapped == log1) {
                if (log1.getRow() % 2 == 1) {
                    GameScreen.player.x += 100 * Gdx.graphics.getDeltaTime() * logOverlapped.getSpeed();
                } else {
                        GameScreen.player.x -= 100 * Gdx.graphics.getDeltaTime() * logOverlapped.getSpeed();
                    }
                }

            if (log1.getRow() % 2 == 1) {
                log1.x += 100 * Gdx.graphics.getDeltaTime() * log1.getSpeed();
            } else {
                log1.x -= 100 * Gdx.graphics.getDeltaTime() * log1.getSpeed();
            }

            if (log1.x > Constants.APP_WIDTH && (log1.getRow() % 2 == 1))
                iterLog.remove();

            if (log1.x < - log1.getWidth() && (log1.getRow() % 2 == 0))
                iterLog.remove();

            if (!playerOnLog && !(GameScreen.player.y > 343 && GameScreen.player.y < 537)) {
                isGoingToDie = false;
            }
        }
        if (isGoingToDie && GameScreen.listaLogs.size > 0) GameScreen.playerDeath();
    }

    public static void draw() {
        for (Log log1 : GameScreen.listaLogs) {
            switch (log1.getRow()) {
                case 1:
                case 3:
                case 5:
                    if (!log1.placed) {
                        log1.x -= (float) Math.random() * 100;
                        log1.placed = true;
                    }
                    MainMenuScreen.batch.draw(log1.texture, log1.getX(), log1.getY());
                    break;
                case 2:
                case 4:
                    if (!log1.placed) {
                        log1.x = (float) (Constants.APP_WIDTH + Math.random() * 100);
                        log1.placed = true;
                    }
                    MainMenuScreen.batch.draw(log1.texture, log1.getX(), log1.getY());
                    break;
            }
        }
    }

    public float getSpeed() {
        return speed;
    }

    public int getRow() {
        return row;
    }

    public static void spawnLog(float speed, int row, float y, Texture texture) {
        Log log1 = new Log(speed, row, texture);
        log1.width = log1.texture.getWidth();
        log1.height = log1.texture.getHeight();
        log1.x = - log1.width;
        log1.y = y;
        GameScreen.listaLogs.add(log1);
    }
}
