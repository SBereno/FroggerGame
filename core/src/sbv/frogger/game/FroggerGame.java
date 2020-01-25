package sbv.frogger.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

import sbv.frogger.game.enums.GameState;
import sbv.frogger.game.scenes.MainMenuScreen;
import sbv.frogger.game.utils.Constants;
import sbv.frogger.game.utils.Screens;

public class FroggerGame extends Game {

	public OrthographicCamera camera;
	public GameState state = GameState.TO_START;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		Screens.mainMenuScreen = new MainMenuScreen(this);
		setScreen(Screens.mainMenuScreen);
	}

	@Override
	public void dispose() {

	}
}
