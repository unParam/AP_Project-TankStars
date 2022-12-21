package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    private final TankStars game;
    private AssetManager manager;
    private OrthographicCamera camera;
    private Sprite MainMenuScreenSprite, MainMenuScreenSprite1, MainMenuScreenSprite2, MainMenuScreenSprite3, MainMenuScreenSprite4,
            MainMenuScreenGenericSprite, MainMenuSettingSprite, MainMenuSettingSprite1, MainMenuSettingSprite2, MainMenuSettingSprite3;

    private int X,Y;
    private boolean SettingsFlag, isSoundON, isMusicON;

    private Sound clickSound, BackSound;
    public MainMenuScreen(final TankStars game, AssetManager Manager) {
        this.game = game;
        this.manager = Manager;

        MainMenuScreenSprite = new Sprite(manager.get("Main Menu\\Main Menu Screen\\0.png", Texture.class));
        MainMenuScreenSprite1 = new Sprite(manager.get("Main Menu\\Main Menu Screen\\1.png", Texture.class));
        MainMenuScreenSprite2 = new Sprite(manager.get("Main Menu\\Main Menu Screen\\2.png", Texture.class));
        MainMenuScreenSprite3 = new Sprite(manager.get("Main Menu\\Main Menu Screen\\3.png", Texture.class));
        MainMenuScreenSprite4 = new Sprite(manager.get("Main Menu\\Main Menu Screen\\4.png", Texture.class));
        MainMenuSettingSprite = new Sprite(manager.get("Main Menu\\Settings Screen\\0.png", Texture.class));
        MainMenuSettingSprite1 = new Sprite(manager.get("Main Menu\\Settings Screen\\1.png", Texture.class));
        MainMenuSettingSprite2 = new Sprite(manager.get("Main Menu\\Settings Screen\\2.png", Texture.class));
        MainMenuSettingSprite3 = new Sprite(manager.get("Main Menu\\Settings Screen\\3.png", Texture.class));
        MainMenuScreenGenericSprite = MainMenuScreenSprite;

        SettingsFlag = false;

        MainMenuScreenSprite.setPosition( Gdx.graphics.getWidth()/2 - MainMenuScreenSprite.getWidth()/2,
                Gdx.graphics.getHeight()/2 - MainMenuScreenSprite.getHeight()/2 + 30);
        MainMenuScreenSprite.setScale(Gdx.graphics.getWidth()/MainMenuScreenSprite.getWidth(),
                Gdx.graphics.getHeight()/MainMenuScreenSprite.getHeight());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        clickSound = manager.get("Audio\\Click.mp3", Sound.class);
        BackSound = manager.get("Audio\\Back.mp3", Sound.class);

        TankStars.setMainMenuBGM(manager.get("Audio\\Main Menu BGM.mp3", Music.class));
        TankStars.getMainMenuBGM().setVolume(0.05f);
        TankStars.getMainMenuBGM().setLooping(true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        X = Gdx.input.getX();
        Y = Gdx.input.getY();
        isSoundON = TankStars.getSoundON();
        isMusicON = TankStars.getMusicON();

        //Handling Hover
        if(!SettingsFlag) {
            if (X >= 1410 && X <= 1790) {
                if (Y >= 195 && Y <= 315) MainMenuScreenGenericSprite = MainMenuScreenSprite1;
                else if (Y >= 445 && Y <= 565) MainMenuScreenGenericSprite = MainMenuScreenSprite2;
                else if (Y >= 705 && Y <= 825) MainMenuScreenGenericSprite = MainMenuScreenSprite3;
                else MainMenuScreenGenericSprite = MainMenuScreenSprite;
            }
            else if (X >= 80 && X <= 165 && Y >= 90 && Y <= 170) MainMenuScreenGenericSprite = MainMenuScreenSprite4;
            else MainMenuScreenGenericSprite = MainMenuScreenSprite;
        }

        game.batch.begin();
        game.batch.draw(MainMenuScreenGenericSprite, MainMenuScreenSprite.getX(), MainMenuScreenSprite.getY(),
                MainMenuScreenSprite.getWidth() / 2, MainMenuScreenSprite.getHeight() / 2,
                MainMenuScreenSprite.getWidth(), MainMenuScreenSprite.getHeight(),
                MainMenuScreenSprite.getScaleX(), MainMenuScreenSprite.getScaleY(),
                MainMenuScreenSprite.getRotation());
        game.batch.end();

        //Handling Clicks
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if(!SettingsFlag) {
                if (MainMenuScreenGenericSprite == MainMenuScreenSprite1) {
                    if(isSoundON) clickSound.play();
                    game.setScreen(TankStars.tankSelectScreen);
                }
                else if (MainMenuScreenGenericSprite == MainMenuScreenSprite2) {
                    if(isSoundON) clickSound.play();
                    game.setScreen(TankStars.saveGameScreen);
                }
                else if (MainMenuScreenGenericSprite == MainMenuScreenSprite3) {
                    if(isSoundON) clickSound.play();
                    TankStars.stopMusic();
                    Gdx.app.exit();
                }
                else if (MainMenuScreenGenericSprite == MainMenuScreenSprite4) {
                    if(isSoundON)clickSound.play();
                    if (!isSoundON && !isMusicON) MainMenuScreenGenericSprite = MainMenuSettingSprite;
                    else if (isSoundON && !isMusicON) MainMenuScreenGenericSprite = MainMenuSettingSprite1;
                    else if (!isSoundON && isMusicON) MainMenuScreenGenericSprite = MainMenuSettingSprite2;
                    else MainMenuScreenGenericSprite = MainMenuSettingSprite3;
                    SettingsFlag = true;
                }
            }
//
            else{
                if (X <= 629 || X >= 1289 || Y <= 213 || Y >= 833) {
                    MainMenuScreenGenericSprite = MainMenuScreenSprite;
                    if(isSoundON)BackSound.play();
                    SettingsFlag = false;
                }
                else if (X >= 770 && X <= 1150) {
                    if (Y >= 385 && Y <= 505) {
                        if (MainMenuScreenGenericSprite == MainMenuSettingSprite)
                        {MainMenuScreenGenericSprite = MainMenuSettingSprite1;TankStars.setSoundON(true);clickSound.play();}
                        else if (MainMenuScreenGenericSprite == MainMenuSettingSprite1)
                        {MainMenuScreenGenericSprite = MainMenuSettingSprite;TankStars.setSoundON(false);}
                        else if (MainMenuScreenGenericSprite == MainMenuSettingSprite2)
                        {MainMenuScreenGenericSprite = MainMenuSettingSprite3;TankStars.setSoundON(true);clickSound.play();}
                        else if (MainMenuScreenGenericSprite == MainMenuSettingSprite3)
                        {MainMenuScreenGenericSprite = MainMenuSettingSprite2;TankStars.setSoundON(false);}
                    }
                    else if (Y >= 585 && Y <= 705) {
                        if (MainMenuScreenGenericSprite == MainMenuSettingSprite)
                        {MainMenuScreenGenericSprite = MainMenuSettingSprite2;TankStars.setMusicON(true);}
                        else if (MainMenuScreenGenericSprite == MainMenuSettingSprite2)
                        {MainMenuScreenGenericSprite = MainMenuSettingSprite;TankStars.setMusicON(false);}
                        else if (MainMenuScreenGenericSprite == MainMenuSettingSprite1)
                        {MainMenuScreenGenericSprite = MainMenuSettingSprite3;TankStars.setMusicON(true);}
                        else if (MainMenuScreenGenericSprite == MainMenuSettingSprite3)
                        {MainMenuScreenGenericSprite = MainMenuSettingSprite1;TankStars.setMusicON(false);}
                        if (isSoundON) clickSound.play();
                    }
                }
            }
        }
        if(!isMusicON) TankStars.pauseMusic();
        else{TankStars.playMusic();}
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        TankStars.playMusic();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
