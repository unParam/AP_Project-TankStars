package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class SaveGameScreen implements Screen {
    private final TankStars game;
    private AssetManager manager;
    private OrthographicCamera camera;

    private Sprite SaveGameScreenSprite, SaveGameScreenSprite1, SaveGameScreenSprite2,
            SaveGameScreenSprite3, SaveGameScreenSprite4, SaveGameScreenGenericSprite;

    int X,Y;
    private boolean isSoundON;
    private Sound clickSound;

    public SaveGameScreen(final TankStars game, AssetManager Manager) {
        this.game = game;
        this.manager = Manager;

        SaveGameScreenSprite = new Sprite(manager.get("Main Menu\\Save Game Screen\\0.png", Texture.class));
        SaveGameScreenSprite1 = new Sprite(manager.get("Main Menu\\Save Game Screen\\1.png", Texture.class));
        SaveGameScreenSprite2 = new Sprite(manager.get("Main Menu\\Save Game Screen\\2.png", Texture.class));
        SaveGameScreenSprite3 = new Sprite(manager.get("Main Menu\\Save Game Screen\\3.png", Texture.class));
        SaveGameScreenSprite4 = new Sprite(manager.get("Main Menu\\Save Game Screen\\4.png", Texture.class));
        SaveGameScreenGenericSprite = SaveGameScreenSprite;

        SaveGameScreenSprite.setPosition( Gdx.graphics.getWidth()/2 - SaveGameScreenSprite.getWidth()/2,
                Gdx.graphics.getHeight()/2 - SaveGameScreenSprite.getHeight()/2 + 30);
        SaveGameScreenSprite.setScale(Gdx.graphics.getWidth()/SaveGameScreenSprite.getWidth(),
                Gdx.graphics.getHeight()/SaveGameScreenSprite.getHeight());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        clickSound = manager.get("Audio\\Click.mp3", Sound.class);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        X = Gdx.input.getX();
        Y = Gdx.input.getY();
        isSoundON = TankStars.getSoundON();

        if (X >= 1410 && X <= 1790) {
            if (Y >= 195 && Y <= 315) SaveGameScreenGenericSprite = SaveGameScreenSprite1;
            else if (Y >= 445 && Y <= 565) SaveGameScreenGenericSprite = SaveGameScreenSprite2;
            else if (Y >= 705 && Y <= 825) SaveGameScreenGenericSprite = SaveGameScreenSprite3;
            else SaveGameScreenGenericSprite = SaveGameScreenSprite;
        }
        else if (X >= 80 && X <= 165 && Y >= 90 && Y <= 170) SaveGameScreenGenericSprite = SaveGameScreenSprite4;
        else SaveGameScreenGenericSprite = SaveGameScreenSprite;

        game.batch.begin();
        game.batch.draw(SaveGameScreenGenericSprite, SaveGameScreenSprite.getX(), SaveGameScreenSprite.getY(),
                SaveGameScreenSprite.getWidth() / 2, SaveGameScreenSprite.getHeight() / 2,
                SaveGameScreenSprite.getWidth(), SaveGameScreenSprite.getHeight(),
                SaveGameScreenSprite.getScaleX(), SaveGameScreenSprite.getScaleY(),
                SaveGameScreenSprite.getRotation());
        game.batch.end();

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if (SaveGameScreenGenericSprite == SaveGameScreenSprite1) {
                if(isSoundON) clickSound.play();
            }
            else if (SaveGameScreenGenericSprite == SaveGameScreenSprite2) {
                if(isSoundON) clickSound.play();
            }
            else if (SaveGameScreenGenericSprite == SaveGameScreenSprite3) {
                if(isSoundON) clickSound.play();
            }
            else if(SaveGameScreenGenericSprite == SaveGameScreenSprite4){
                if(isSoundON) clickSound.play();
                game.setScreen(TankStars.mainMenuScreen);
            }
        }
    }
    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
