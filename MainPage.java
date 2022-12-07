package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyActor;

public class MainPage extends ApplicationAdapter implements Screen, InputProcessor {

    private SpriteBatch batch;

    private Game game;
    private Sprite MainPageSprite;

    public MainPage(Game AGame){
        game = AGame;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        MainPageSprite =  new Sprite(new Texture(Gdx.files.internal("Main Page.png")));
        MainPageSprite.setPosition( Gdx.graphics.getWidth()/2 - MainPageSprite.getWidth()/2,
                Gdx.graphics.getHeight()/2 - MainPageSprite.getHeight()/2);
        MainPageSprite.setScale(Gdx.graphics.getWidth()/MainPageSprite.getWidth(),
                Gdx.graphics.getHeight()/MainPageSprite.getHeight());
//        stage.addActor(new MyActor(MainPageSprite));
//        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(MainPageSprite, MainPageSprite.getX(), MainPageSprite.getY(),
                MainPageSprite.getWidth() / 2, MainPageSprite.getHeight() / 2,
                MainPageSprite.getWidth(), MainPageSprite.getHeight(),
                MainPageSprite.getScaleX(), MainPageSprite.getScaleY(),
                MainPageSprite.getRotation());
        batch.end();
    }
    @Override
    public void dispose () {
        batch.dispose();
    }
    @Override
    public boolean keyDown(int keycode) {
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

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }
}
