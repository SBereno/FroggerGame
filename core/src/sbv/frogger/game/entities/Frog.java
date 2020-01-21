package sbv.frogger.game.entities;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import sbv.frogger.game.enums.Axis;
import sbv.frogger.game.utils.Constants;

public class Frog extends Rectangle {

    Sound jumpSound = Constants.jumpSound;

    public Frog() {

    }

    public void move(Axis axis, boolean positive) {
        float x = axis == Axis.X ? positive ? getX() + Constants.FROG_WIDTH : getX() - Constants.FROG_WIDTH : getX(),
                y = axis == Axis.Y ? positive ? getY() + Constants.FROG_HEIGHT : getY() - Constants.FROG_HEIGHT : getY();
        if (x > 0 && x < Constants.APP_WIDTH - Constants.FROG_WIDTH && y >= Constants.FROG_Y) {
            setPosition(x, y);
            jumpSound.play();
        }
    }
}
