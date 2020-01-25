package sbv.frogger.game.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import sbv.frogger.game.scenes.GameScreen;
import sbv.frogger.game.utils.Constants;

import java.util.Iterator;

public class Log extends Rectangle {

    private float speed;
    private int row;

    public Log(float speed, int row) {
        this.speed = speed;
        this.row = row;
    }

    public static void move() {
        for (Iterator<Log> iterLog = GameScreen.listaLogs.iterator(); iterLog.hasNext(); ) {
            Log log1 = iterLog.next();

            if (log1.getRow() < 3 || log1.getRow() == 5)
                log1.x += 100 * Gdx.graphics.getDeltaTime() * log1.getSpeed();

            if (log1.getRow() > 2 && !(log1.getRow() == 5))
                log1.x -= 100 * Gdx.graphics.getDeltaTime() * log1.getSpeed();

            if (log1.x > Constants.APP_WIDTH && (log1.getRow() < 3 || log1.getRow() == 5))
                iterLog.remove();

            if (log1.x < - log1.getWidth() && (log1.getRow() > 2 && !(log1.getRow() == 5)))
                iterLog.remove();

            if (GameScreen.player.y > 336 && GameScreen.player.y < 616 && !log1.overlaps(GameScreen.player)) {
                GameScreen.playerDeath();
            } else if (GameScreen.player.y > 336 && GameScreen.player.y < 616 && GameScreen.player.overlaps(log1)) {

            }
        }
    }

    public static void draw() {
        for (Log log1 : GameScreen.listaLogs) {
            switch (log1.getRow()) {
                case 1:
                    GameScreen.game.batch.draw(Constants.log1Texture, log1.getX(), log1.getY());
                    break;
                case 2:
                    GameScreen.game.batch.draw(GameScreen.car2Flipped, log1.getX(), log1.getY());
                    break;
                case 3:
                    GameScreen.game.batch.draw(GameScreen.car1Flipped, log1.getX(), log1.getY());
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

    public static void spawnLog1() {
        Log log1 = new Log(1.5f, 1);
        log1.width = Constants.log1Texture.getWidth();
        log1.height = Constants.log1Texture.getHeight();
        log1.x = - log1.width;
        log1.y = 344;
        GameScreen.listaLogs.add(log1);
    }
}
