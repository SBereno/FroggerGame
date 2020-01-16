package sbv.frogger.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Constants {

    public static final int APP_WIDTH = 480;
    public static final int APP_HEIGHT = 720;

    public static final int FROG_WIDTH = 16;
    public static final int FROG_HEIGHT = 16;
    public static final int FROG_X = APP_WIDTH / 2 - FROG_WIDTH / 2;
    public static final int FROG_Y = 56;
    public static final Texture frogTexture = new Texture(Gdx.files.internal("Frog_player.png"));

}
