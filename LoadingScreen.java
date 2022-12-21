package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;


public class LoadingScreen implements Screen {

    private final TankStars game;
    private AssetManager manager;
    private OrthographicCamera camera;
    private Sprite LoadingScreenSprite, LoadingScreenSprite1, LoadingScreenSprite2, LoadingScreenSprite3,
            LoadingScreenSprite4, LoadingScreenSprite5, LoadingScreenSpriteGeneric;
    private float LifeTime = 0;

    private GlyphLayout layout;
    private int count = 0, countChange = 1;
    private float percentLoaded;
    private boolean ScreensCreated = false;

    private void countGO(){
        count += countChange;
        if(count == 30 || count == 0) countChange = -countChange;
    }

    public LoadingScreen(final TankStars game, AssetManager Manager) {
        this.game = game;
        this.manager = Manager;

        LoadingScreenSprite = new Sprite(new Texture(Gdx.files.internal("Loading Screen\\0.png")));
        LoadingScreenSprite1 = new Sprite(new Texture(Gdx.files.internal("Loading Screen\\1.png")));
        LoadingScreenSprite2 = new Sprite(new Texture(Gdx.files.internal("Loading Screen\\2.png")));
        LoadingScreenSprite3 = new Sprite(new Texture(Gdx.files.internal("Loading Screen\\3.png")));
        LoadingScreenSprite4 = new Sprite(new Texture(Gdx.files.internal("Loading Screen\\4.png")));
        LoadingScreenSprite5 = new Sprite(new Texture(Gdx.files.internal("Loading Screen\\5.png")));
        LoadingScreenSpriteGeneric = LoadingScreenSprite;
		LoadingScreenSprite.setPosition( Gdx.graphics.getWidth()/2 - LoadingScreenSprite.getWidth()/2,
				Gdx.graphics.getHeight()/2 - LoadingScreenSprite.getHeight()/2 + 30);
		LoadingScreenSprite.setScale(Gdx.graphics.getWidth()/LoadingScreenSprite.getWidth(),
				Gdx.graphics.getHeight()/LoadingScreenSprite.getHeight());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        layout = new GlyphLayout();
        layout.setText(game.font, "Press any key to Continue");

        manager.load("Main Menu\\Main Menu Screen\\0.png", Texture.class);
        manager.load("Main Menu\\Main Menu Screen\\1.png", Texture.class);
        manager.load("Main Menu\\Main Menu Screen\\2.png", Texture.class);
        manager.load("Main Menu\\Main Menu Screen\\3.png", Texture.class);
        manager.load("Main Menu\\Main Menu Screen\\4.png", Texture.class);

        manager.load("Main Menu\\Settings Screen\\0.png", Texture.class);
        manager.load("Main Menu\\Settings Screen\\1.png", Texture.class);
        manager.load("Main Menu\\Settings Screen\\2.png", Texture.class);
        manager.load("Main Menu\\Settings Screen\\3.png", Texture.class);

        manager.load("Main Menu\\Save Game Screen\\0.png", Texture.class);
        manager.load("Main Menu\\Save Game Screen\\1.png", Texture.class);
        manager.load("Main Menu\\Save Game Screen\\2.png", Texture.class);
        manager.load("Main Menu\\Save Game Screen\\3.png", Texture.class);
        manager.load("Main Menu\\Save Game Screen\\4.png", Texture.class);

        manager.load("Main Menu\\Tank Select Screen\\1.png", Texture.class);
        manager.load("Main Menu\\Tank Select Screen\\2.png", Texture.class);
        manager.load("Main Menu\\Tank Select Screen\\3.png", Texture.class);
        manager.load("Main Menu\\Tank Select Screen\\4.png", Texture.class);
        manager.load("Main Menu\\Tank Select Screen\\5.png", Texture.class);
        manager.load("Main Menu\\Tank Select Screen\\6.png", Texture.class);
        manager.load("Main Menu\\Tank Select Screen\\7.png", Texture.class);
        manager.load("Main Menu\\Tank Select Screen\\8.png", Texture.class);
        manager.load("Main Menu\\Tank Select Screen\\9.png", Texture.class);

        manager.load("Game Screen\\Pause Screen\\0.png", Texture.class);
        manager.load("Game Screen\\Pause Screen\\1.png", Texture.class);
        manager.load("Game Screen\\Pause Screen\\2.png", Texture.class);
        manager.load("Game Screen\\Pause Screen\\3.png", Texture.class);
        manager.load("Game Screen\\Pause Screen\\4.png", Texture.class);

        manager.load("Game Screen\\Game Over Screen\\0.png", Texture.class);
        manager.load("Game Screen\\Game Over Screen\\1.png", Texture.class);
        manager.load("Game Screen\\Game Over Screen\\2.png", Texture.class);

        manager.load("Audio\\Main Menu BGM.mp3", Music.class);
        manager.load("Audio\\Game BGM.mp3", Music.class);

        manager.load("Audio\\Click.mp3", Sound.class);
        manager.load("Audio\\Back.mp3", Sound.class);
        manager.load("Audio\\Drumroll.mp3", Sound.class);
        manager.load("Audio\\TankMove.wav", Sound.class);
        manager.load("Audio\\FuelEmpty.mp3", Sound.class);


        manager.load("Tanks\\Abrams.png", Texture.class);
        manager.load("Tanks\\Buratino.png", Texture.class);
        manager.load("Tanks\\Frost.png", Texture.class);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        percentLoaded = manager.getProgress();
        if (percentLoaded == 1 && LifeTime <= 5) {LifeTime += Gdx.graphics.getDeltaTime();}

        if(manager.update() && LifeTime >= 0.5) {
            LoadingScreenSpriteGeneric = LoadingScreenSprite;
        }
        else {
            if (percentLoaded == 1) LoadingScreenSpriteGeneric = LoadingScreenSprite5;
            else if (percentLoaded >= 0.75f) LoadingScreenSpriteGeneric = LoadingScreenSprite4;
            else if (percentLoaded >= 0.50f) LoadingScreenSpriteGeneric = LoadingScreenSprite3;
            else if (percentLoaded >= 0.25f) LoadingScreenSpriteGeneric = LoadingScreenSprite2;
            else LoadingScreenSpriteGeneric = LoadingScreenSprite1;
        }
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(LoadingScreenSpriteGeneric, LoadingScreenSprite.getX(), LoadingScreenSprite.getY(),
				LoadingScreenSprite.getWidth() / 2, LoadingScreenSprite.getHeight() / 2,
				LoadingScreenSprite.getWidth(), LoadingScreenSprite.getHeight(),
				LoadingScreenSprite.getScaleX(), LoadingScreenSprite.getScaleY(),
				LoadingScreenSprite.getRotation());

        if(LifeTime >= 1)  {
            countGO();
            if (countChange == 1) game.font.draw(game.batch, layout, Gdx.graphics.getWidth() / 2 - layout.width / 2,
				Gdx.graphics.getHeight() / 2 - layout.height / 2 - 350);
        }
        game.batch.end();

        if(LifeTime >= 0.1 && !ScreensCreated){
            TankStars.mainMenuScreen = new MainMenuScreen(game, manager);
            TankStars.saveGameScreen = new SaveGameScreen(game, manager);
            TankStars.tankSelectScreen = new TankSelectScreen(game, manager);
            ScreensCreated = true;
        }

        if ((Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) && (LifeTime >= 1)) {
            manager.get("Audio\\Click.mp3", Sound.class).play();
            game.setScreen(TankStars.mainMenuScreen);
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
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

