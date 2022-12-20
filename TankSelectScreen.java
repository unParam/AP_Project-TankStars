package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class TankSelectScreen implements Screen {
    private final TankStars game;
    private AssetManager manager;
    private OrthographicCamera camera;

    Sprite  TankSelectScreenSprite1, TankSelectScreenSprite2, TankSelectScreenSprite3, TankSelectScreenSprite4,
            TankSelectScreenSprite5, TankSelectScreenSprite6, TankSelectScreenSprite7, TankSelectScreenSprite8,
            TankSelectScreenSprite9, TankSelectScreenGenericSprite, AbramsSprite, BuratinoSprite, FrostSprite, TankSprite;

    private int X,Y;
    private boolean isSoundON;
    private Sound clickSound, drumSound;
    private int TankID = 1;
    private int TankID1 = 1;
    private int TankID2 = 1;
    private boolean ready1 = false;
    private boolean ready2 = false;
    private GlyphLayout layout1, layout2;

    public TankSelectScreen(final TankStars game, AssetManager Manager) {
        this.game = game;
        this.manager = Manager;

        TankSelectScreenSprite1 = new Sprite(manager.get("Main Menu\\Tank Select Screen\\1.png", Texture.class));
        TankSelectScreenSprite2 = new Sprite(manager.get("Main Menu\\Tank Select Screen\\2.png", Texture.class));
        TankSelectScreenSprite3 = new Sprite(manager.get("Main Menu\\Tank Select Screen\\3.png", Texture.class));
        TankSelectScreenSprite4 = new Sprite(manager.get("Main Menu\\Tank Select Screen\\4.png", Texture.class));
        TankSelectScreenSprite5 = new Sprite(manager.get("Main Menu\\Tank Select Screen\\5.png", Texture.class));
        TankSelectScreenSprite6 = new Sprite(manager.get("Main Menu\\Tank Select Screen\\6.png", Texture.class));
        TankSelectScreenSprite7 = new Sprite(manager.get("Main Menu\\Tank Select Screen\\7.png", Texture.class));
        TankSelectScreenSprite8 = new Sprite(manager.get("Main Menu\\Tank Select Screen\\8.png", Texture.class));
        TankSelectScreenSprite9 = new Sprite(manager.get("Main Menu\\Tank Select Screen\\9.png", Texture.class));
        TankSelectScreenGenericSprite = TankSelectScreenSprite1;

        AbramsSprite = new Sprite(manager.get("Tanks\\Abrams.png", Texture.class));
        BuratinoSprite = new Sprite(manager.get("Tanks\\Buratino.png", Texture.class));
        FrostSprite = new Sprite(manager.get("Tanks\\Frost.png", Texture.class));

        TankSelectScreenSprite1.setPosition( Gdx.graphics.getWidth()/2 - TankSelectScreenSprite1.getWidth()/2,
                Gdx.graphics.getHeight()/2 - TankSelectScreenSprite1.getHeight()/2 + 30);
        TankSelectScreenSprite1.setScale(Gdx.graphics.getWidth()/TankSelectScreenSprite1.getWidth(),
                Gdx.graphics.getHeight()/TankSelectScreenSprite1.getHeight());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        clickSound = manager.get("Audio\\Click.mp3", Sound.class);
        drumSound = manager.get("Audio\\Drumroll.mp3", Sound.class);

        layout1 = new GlyphLayout();
        layout1.setText(game.font, "PLAYER 1");
        layout2 = new GlyphLayout();
        layout2.setText(game.font, "PLAYER 2");
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        X = Gdx.input.getX();
        Y = Gdx.input.getY();
        isSoundON = TankStars.getSoundON();

        if(TankID == 1){
            if (X >= 1410 && X <= 1790 && Y >= 705 && Y <= 825){
                TankSelectScreenGenericSprite = TankSelectScreenSprite3;
            }
            else if(X >= 80 && X <= 165 && Y >= 90 && Y <= 170){
                TankSelectScreenGenericSprite = TankSelectScreenSprite2;
            }
            else TankSelectScreenGenericSprite = TankSelectScreenSprite1;
            TankSprite = AbramsSprite;
        }
        else if(TankID == 2){
            if (X >= 1410 && X <= 1790 && Y >= 705 && Y <= 825) TankSelectScreenGenericSprite = TankSelectScreenSprite6;
            else if(X >= 80 && X <= 165 && Y >= 90 && Y <= 170) TankSelectScreenGenericSprite = TankSelectScreenSprite5;
            else TankSelectScreenGenericSprite = TankSelectScreenSprite4;
            TankSprite = FrostSprite;
        }
        else if(TankID == 3){
            if (X >= 1410 && X <= 1790 && Y >= 705 && Y <= 825) TankSelectScreenGenericSprite = TankSelectScreenSprite9;
            else if(X >= 80 && X <= 165 && Y >= 90 && Y <= 170) TankSelectScreenGenericSprite = TankSelectScreenSprite8;
            else TankSelectScreenGenericSprite = TankSelectScreenSprite7;
            TankSprite = BuratinoSprite;
        }

        game.batch.begin();
        game.batch.draw(TankSelectScreenGenericSprite, TankSelectScreenSprite1.getX(), TankSelectScreenSprite1.getY(),
                TankSelectScreenSprite1.getWidth() / 2, TankSelectScreenSprite1.getHeight() / 2,
                TankSelectScreenSprite1.getWidth(), TankSelectScreenSprite1.getHeight(),
                TankSelectScreenSprite1.getScaleX(), TankSelectScreenSprite1.getScaleY(),
                TankSelectScreenSprite1.getRotation());
        if(TankID <= 2) {
            game.batch.draw(TankSprite, Gdx.graphics.getWidth() / 2 - TankSprite.getWidth() / 2 - 300,
                    Gdx.graphics.getHeight() / 2 - TankSprite.getHeight() / 2 - 100, TankSprite.getWidth() / 2, TankSprite.getHeight() / 2,
                    TankSprite.getWidth(), TankSprite.getHeight(), 1.5f, 1.5f, 0f);
        }
        else{game.batch.draw(TankSprite, Gdx.graphics.getWidth() / 2 - TankSprite.getWidth() / 2 - 325,
                Gdx.graphics.getHeight() / 2 - TankSprite.getHeight() / 2 - 63, TankSprite.getWidth() / 2, TankSprite.getHeight() / 2,
                TankSprite.getWidth(), TankSprite.getHeight(), 1.5f, 1.5f, 0f);}

        if(!ready1){game.font.draw(game.batch, layout1, Gdx.graphics.getWidth() / 2 - layout1.width / 2 + 640,
                Gdx.graphics.getHeight() / 2 - layout1.height / 2 + 410);}
        else{game.font.draw(game.batch, layout2, Gdx.graphics.getWidth() / 2 - layout2.width / 2 + 640,
                Gdx.graphics.getHeight() / 2 - layout2.height / 2 + 410);}
        game.batch.end();

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            if (TankSelectScreenGenericSprite == TankSelectScreenSprite2 || TankSelectScreenGenericSprite == TankSelectScreenSprite5
            || TankSelectScreenGenericSprite == TankSelectScreenSprite8){
                if(!ready1) game.setScreen(TankStars.mainMenuScreen);
                else{
                    if(TankID1 == 1) TankID = 1;
                    else if (TankID1 == 2) TankID = 2;
                    else if (TankID1 == 3) TankID = 3;
                    ready1 = false;
                }
                if(isSoundON) clickSound.play();
            }
            else if(Y >= 430 && Y <= 530) {
                if (X >= 1735 && X <= 1905 && TankID <= 2) {TankID++; if(isSoundON) clickSound.play();}
                else if (X >= 1300 && X <= 1470 && TankID >= 2) {TankID--; if(isSoundON) clickSound.play();}
            }
            else if(TankSelectScreenGenericSprite == TankSelectScreenSprite3){
                if(!ready1){TankID1 = 1; ready1 = true; TankID = 1;}
                else{TankID2 = 1; ready2 = true;}
                if(isSoundON) clickSound.play();
            }
            else if(TankSelectScreenGenericSprite == TankSelectScreenSprite6){
                if(!ready1){TankID1 = 2; ready1 = true; TankID = 1;}
                else{TankID2 = 2; ready2 = true;}
                if(isSoundON) clickSound.play();
            }
            else if (TankSelectScreenGenericSprite == TankSelectScreenSprite9){
                if(!ready1){TankID1 = 3; ready1 = true; TankID = 1;}
                else{TankID2 = 3; ready2 = true;}
                if(isSoundON) clickSound.play();
            }
            if(ready1 && ready2){
                if(isSoundON) drumSound.play();
                TankStars.stopMusic();
                game.setScreen(TankStars.gameScreen);
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
