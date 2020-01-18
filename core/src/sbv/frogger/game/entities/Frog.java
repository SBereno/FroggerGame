package sbv.frogger.game.entities;


import com.badlogic.gdx.math.Rectangle;
import sbv.frogger.game.enums.Axis;
import sbv.frogger.game.utils.Constants;

public class Frog extends Rectangle {

    Frog() {}

    public void move(Axis axis, boolean positive) {
        float x = axis == Axis.X ? positive ? getX() + Constants.FROG_WIDTH : getX() - Constants.APP_WIDTH : getX(),
              y = axis == Axis.Y ? positive ? getY() + Constants.FROG_HEIGHT : getY() - Constants.APP_HEIGHT : getY();
        setPosition(x, y);
    }

}
