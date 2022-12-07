package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;

public class Main extends Game implements InputProcessor, Screen {

	public SpriteBatch batch;
	Texture LoadingScreenImage;
	Sprite LoadingScreenSprite;
	BitmapFont font;
	GlyphLayout layout = new GlyphLayout();
	String prompt;
	public static final float GAME_WORLD_WIDTH = 1920;
	public static final float GAME_WORLD_HEIGHT = 1080;

//	Viewport viewport;

	@Override
	public void create () {
		setScreen(new MainPage(this));
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("font.fnt"));
		font.setColor(Color.WHITE);
		prompt = "Press any key to Continue";
		layout.setText(font, prompt);

		LoadingScreenImage = new Texture("Loading Screen.png");
		LoadingScreenSprite = new Sprite(LoadingScreenImage);
		LoadingScreenSprite.setPosition( Gdx.graphics.getWidth()/2 - LoadingScreenSprite.getWidth()/2,
				Gdx.graphics.getHeight()/2 - LoadingScreenSprite.getHeight()/2);
		LoadingScreenSprite.setScale(Gdx.graphics.getWidth()/LoadingScreenSprite.getWidth(),
				Gdx.graphics.getHeight()/LoadingScreenSprite.getHeight());
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(LoadingScreenSprite, LoadingScreenSprite.getX(), LoadingScreenSprite.getY(),
				LoadingScreenSprite.getWidth() / 2, LoadingScreenSprite.getHeight() / 2,
				LoadingScreenSprite.getWidth(), LoadingScreenSprite.getHeight(),
				LoadingScreenSprite.getScaleX(), LoadingScreenSprite.getScaleY(),
				LoadingScreenSprite.getRotation());
		font.draw(batch, prompt, Gdx.graphics.getWidth() / 2 - layout.width / 2,
				Gdx.graphics.getHeight() / 2 - layout.height / 2 - 380);
		batch.end();

		if (Gdx.input.isTouched()) {
			this.setScreen(new MainPage(this));
			dispose();
		}
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void hide() {

	}


	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
//		if (keycode == Input.Keys.LEFT) {
//			this.setScreen(MainMenu(this));
//		}
			return false;

	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
