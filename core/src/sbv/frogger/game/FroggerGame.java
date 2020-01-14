package sbv.frogger.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sbv.frogger.game.scenes.MainMenuScreen;

public class FroggerGame extends Game {
	SpriteBatch batch;
	BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		setScreen(new MainMenuScreen());
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
