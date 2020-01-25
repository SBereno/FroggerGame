package sbv.frogger.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import sbv.frogger.game.scenes.GameScreen;
import sbv.frogger.game.scenes.MainMenuScreen;
import sbv.frogger.game.utils.Constants;

import java.util.Iterator;

public class Car extends Rectangle {

    private float speed;
    private int row;

    public Car(float speed, int row) {
        this.speed = speed;
        this.row = row;
    }

    public static void move() {
        for (Iterator<Car> iterCarL1 = GameScreen.listaCars.iterator(); iterCarL1.hasNext(); ) {
            Car car1 = iterCarL1.next();

            if (car1.getRow() < 3)
                car1.x += 100 * Gdx.graphics.getDeltaTime() * car1.getSpeed();

            if (car1.getRow() > 2)
                car1.x -= 100 * Gdx.graphics.getDeltaTime() * car1.getSpeed();

            if (car1.x > Constants.APP_WIDTH && car1.getRow() < 3)
                iterCarL1.remove();

            if (car1.x < - car1.getWidth() && car1.getRow() > 2)
                iterCarL1.remove();

            if (car1.overlaps(GameScreen.player))
                GameScreen.playerDeath();
        }
    }

    public static void draw() {
        for (Car car1 : GameScreen.listaCars) {
            switch (car1.getRow()) {
                case 1:
                    MainMenuScreen.batch.draw(Constants.car1Texture, car1.getX(), car1.getY());
                    break;
                case 2:
                    MainMenuScreen.batch.draw(GameScreen.car2Flipped, car1.getX(), car1.getY());
                    break;
                case 3:
                    MainMenuScreen.batch.draw(GameScreen.car1Flipped, car1.getX(), car1.getY());
                    break;
                case 4:
                    MainMenuScreen.batch.draw(Constants.car2Texture, car1.getX(), car1.getY());
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

    public static void spawnCar1() {
        Car car1 = new Car(1.5f, 1);
        car1.width = Constants.FROG_WIDTH;
        car1.height = Constants.FROG_HEIGHT;
        car1.x = - car1.width;
        car1.y = 104;
        GameScreen.listaCars.add(car1);
    }

    public static void spawnCar2() {
        Car car1 = new Car(3.5f, 2);
        car1.width = Constants.FROG_WIDTH;
        car1.height = Constants.FROG_HEIGHT;
        car1.x = - car1.width;
        car1.y = 152;
        GameScreen.listaCars.add(car1);
    }

    public static void spawnCar3() {
        Car car1 = new Car(2.5f, 3);
        car1.width = Constants.FROG_WIDTH;
        car1.height = Constants.FROG_HEIGHT;
        car1.x = Constants.APP_WIDTH + car1.width * 2;
        car1.y = 200;
        GameScreen.listaCars.add(car1);
    }

    public static void spawnCar4() {
        Car car1 = new Car(4.5f, 4);
        car1.width = Constants.FROG_WIDTH;
        car1.height = Constants.FROG_HEIGHT;
        car1.x = Constants.APP_WIDTH + car1.width * 2;
        car1.y = 248;
        GameScreen.listaCars.add(car1);
    }
}
