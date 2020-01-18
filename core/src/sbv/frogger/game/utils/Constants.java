package sbv.frogger.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Constants {

    public static final int APP_WIDTH = 480;
    public static final int APP_HEIGHT = 720;

    public static final int FROG_WIDTH = 16;
    public static final int FROG_HEIGHT = 16;
    public static final int FROG_X = APP_WIDTH / 2 - FROG_WIDTH * 2;
    public static final int FROG_Y = 56;

    public static final Texture frogTexture = new Texture(Gdx.files.internal("Frog_player.png"));
    public static final TextureRegion backgroundTexture = new TextureRegion(new Texture("Background.png"), 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    public static final Texture Car1Texture = new Texture(Gdx.files.internal("Car1.png"));
    public static final Texture Car2Texture = new Texture(Gdx.files.internal("Car2.png"));
    public static final Texture Log1Texture = new Texture(Gdx.files.internal("Log1.png"));
    public static final Texture Log2Texture = new Texture(Gdx.files.internal("Log2.png"));
    public static final Texture Log3Texture = new Texture(Gdx.files.internal("Log3.png"));

}
