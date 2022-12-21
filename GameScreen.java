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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Tank;
import com.mygdx.game.Utilities.Constants;
import com.mygdx.game.Utilities.TiledObjectUtil;


public class GameScreen implements Screen {

    private final TankStars game;
    private AssetManager manager;
    private OrthographicCamera camera;

    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player, player1, player2, platform;
    private static final float PPM = Constants.PPM;
    private final float SCALE = 0.335f;
    private Sprite Abrams, Buratino, Frost, PauseScreen, PauseScreen1, PauseScreen2, PauseScreen3, PauseScreen4, PauseScreenGeneric,
            GameOverScreen, GameOverScreen1, GameOverScreen2, Sprite1, Sprite2;
    private boolean isTankMoving, isTankMoveSoundPlaying, isEmptyFuelSoundPlaying, isFuelEmpty, isPaused, isTurnOver,
            isGameOver, isSoundON, isMusicON;
    private Sound tankMoveSound, emptyFuelSound, clickSound;
    private Music GameBGM;
    private int fuel;
    private float health;
    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;

    public GameScreen(final TankStars game, AssetManager manager){
        this.game = game;
        this.manager = manager;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/SCALE, h/SCALE);

        world = new World(new Vector2(0, -50f), false);
        b2dr = new Box2DDebugRenderer();

        player1 = createBox(800, 1640, 150, 80, false);
        player2 = createBox(5000, 1350, 150, 80, false);
        player = player1;
        platform = createBox(2900, 1550, 0, 0, true);

        Abrams = new Sprite(manager.get("Tanks\\Abrams.png", Texture.class));
        Abrams.setScale(0.35f);
        Buratino = new Sprite(manager.get("Tanks\\Buratino.png", Texture.class));
        Buratino.setScale(0.35f);
        Frost = new Sprite(manager.get("Tanks\\Frost.png", Texture.class));
        Frost.setScale(0.35f);
        if(TankSelectScreen.getTankID1() == 1) Sprite1 = Abrams;
        else if(TankSelectScreen.getTankID1() == 2) Sprite1 = Frost;
        else Sprite1 = Buratino;
        if(TankSelectScreen.getTankID2() == 1) Sprite2 = Abrams;
        else if(TankSelectScreen.getTankID2() == 2) Sprite2 = Frost;
        else Sprite2 = Buratino;
        PauseScreen = new Sprite(manager.get("Game Screen\\Pause Screen\\0.png", Texture.class));
        PauseScreen1 = new Sprite(manager.get("Game Screen\\Pause Screen\\1.png", Texture.class));
        PauseScreen2 = new Sprite(manager.get("Game Screen\\Pause Screen\\2.png", Texture.class));
        PauseScreen3 = new Sprite(manager.get("Game Screen\\Pause Screen\\3.png", Texture.class));
        PauseScreen4 = new Sprite(manager.get("Game Screen\\Pause Screen\\4.png", Texture.class));
        PauseScreenGeneric = PauseScreen;
        PauseScreen.setScale(0.8f);

        isTankMoving = isTankMoveSoundPlaying = isEmptyFuelSoundPlaying = isFuelEmpty = isPaused = isTurnOver =
                isGameOver = false;

        GameBGM = manager.get("Audio\\Game BGM.mp3", Music.class);
        GameBGM.setVolume(0.05f);
        GameBGM.setLooping(true);

        tankMoveSound = manager.get("Audio\\TankMove.wav", Sound.class);
        emptyFuelSound = manager.get("Audio\\FuelEmpty.mp3", Sound.class);
        clickSound = manager.get("Audio\\Click.mp3", Sound.class);

        map = new TmxMapLoader().load("Game Screen\\Ground Terrain\\Map1.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);

        fuel = 100; health = 800;

        TiledObjectUtil.parseTiledObjectLayer(world, map.getLayers().get("collision layer").getObjects());
    }
    @Override
    public void render(float delta) {
        float X = Gdx.input.getX();
        float Y = Gdx.input.getY();
        isSoundON = TankStars.getSoundON();
        isMusicON = TankStars.getMusicON();
        if(!isGameOver){
            if(isTurnOver){if(player == player1) player = player2; else player = player1; isTurnOver = false; fuel = 100;}
            if(!isPaused) {
                update(Gdx.graphics.getDeltaTime());
                ScreenUtils.clear(0, 0, 0, 1);

                tmr.render();

                game.batch.begin();
                game.batch.draw(Sprite1, player1.getPosition().x * PPM - Sprite1.getWidth() / 2, player1.getPosition().y * PPM - Sprite1.getHeight() / 2,
                        Sprite1.getWidth() / 2, Sprite1.getHeight() / 2,
                        Sprite1.getWidth(), Sprite1.getHeight(), Sprite1.getScaleX(), Sprite1.getScaleY(),
                        (float) Math.toDegrees(player1.getAngle()));
                game.batch.draw(Sprite2, player2.getPosition().x * PPM - Sprite2.getWidth() / 2, player2.getPosition().y * PPM - Sprite2.getHeight() / 2,
                        Sprite2.getWidth()/2, Sprite2.getHeight() / 2,
                        Sprite2.getWidth(), Sprite2.getHeight(), - Sprite2.getScaleX(), Sprite2.getScaleY(),
                        (float) Math.toDegrees(player2.getAngle()));
                game.batch.end();
//                b2dr.render(world, camera.combined.scl(PPM));

                int Force = 0;
                if(!isFuelEmpty) {
                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                        Force -= 5;
                        isTankMoving = true;
                    }
                    else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                        Force += 5;
                        isTankMoving = true;
                    }
                    else isTankMoving = false;
                }
                else isTankMoving = false;
                if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                    isPaused = true;
                }
                else if(Gdx.input.isKeyJustPressed(Input.Keys.C)){isTurnOver = true;}
                player.setLinearVelocity((float) (Force*Math.cos(player.getAngle())),
                        (float) (Force*(Math.sin(player.getAngle()))));
                if(!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)||fuel==0) player.setAwake(false);
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && X >= 70 && X <= 165 && Y >= 50 && Y <= 150){isPaused = true;}
            }
            else{
                if(X >= 780 && X <= 1100) {
                    if(Y >= 240 && Y <= 350) PauseScreenGeneric = PauseScreen1;
                    else if(Y >= 410 && Y <= 520) PauseScreenGeneric = PauseScreen2;
                    else if(Y >= 585 && Y <= 695) PauseScreenGeneric = PauseScreen3;
                    else if(Y >= 760 && Y <= 870) PauseScreenGeneric = PauseScreen4;
                    else PauseScreenGeneric = PauseScreen;
                }
                else PauseScreenGeneric = PauseScreen;

                game.batch.begin();
                game.batch.draw(PauseScreenGeneric, 2850 - PauseScreen.getWidth()/2, 1600 - PauseScreen.getHeight()/2,
                        PauseScreen.getWidth()/2, PauseScreen.getHeight()/2,
                        PauseScreen.getWidth(), PauseScreen.getHeight(), PauseScreen.getScaleX(), PauseScreen.getScaleY(), PauseScreen.getRotation());
                game.batch.end();

                if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                    isPaused = false;
                }
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                    if(PauseScreenGeneric == PauseScreen1) {isPaused = false; if(isSoundON) clickSound.play();}
                    else if(PauseScreenGeneric == PauseScreen2 || PauseScreenGeneric == PauseScreen3) if(isSoundON) clickSound.play();
                    else if(PauseScreenGeneric == PauseScreen4) {GameBGM.stop(); if(isSoundON) clickSound.play();
                        game.setScreen(TankStars.mainMenuScreen);}
                }

            }
        }
        else{
        }
    }

    public void update(float delta){
        world.step(1/60f, 6, 2);
        cameraUpdate(delta);
        soundUpdate();
        fuelUpdate();
        tmr.setView(camera);
        game.batch.setProjectionMatrix(camera.combined);
    }
    public void cameraUpdate(float delta){
        Vector3 position = camera.position;
        position.x = platform.getPosition().x * PPM;
        position.y = platform.getPosition().y * PPM;
        camera.position.set(position);

        camera.update();
    }
    public Body createBox(int x, int y, int width, int height, boolean isStatic){
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic) def.type = BodyDef.BodyType.StaticBody;
        else def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x/PPM, y/PPM);
        def.fixedRotation = false;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/PPM, height/2/PPM);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 10f;
        fixture.restitution = 0f;
        fixture.friction = 0.5f;

        pBody.createFixture(fixture);
        shape.dispose();
        return pBody;
    }

    public void soundUpdate(){
        if(isTankMoving && !isTankMoveSoundPlaying && isSoundON) {tankMoveSound.loop(); isTankMoveSoundPlaying = true;}
        else if(!isTankMoving) {tankMoveSound.stop(); isTankMoveSoundPlaying = false;}
        if(isFuelEmpty && !isEmptyFuelSoundPlaying && isSoundON){
            emptyFuelSound.play();
            isEmptyFuelSoundPlaying = true;
        }
        if(!isFuelEmpty){
            isEmptyFuelSoundPlaying = false;
        }
        if(isMusicON) GameBGM.play();
        else GameBGM.stop();
    }
    public void fuelUpdate(){
        if(fuel<=0){isFuelEmpty = true;}
        else isFuelEmpty = false;
        if(isTankMoving && !isFuelEmpty) fuel -= 1;
    }

    @Override
    public void show() {
        GameBGM.play();
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
        world.dispose();
        b2dr.dispose();
        tmr.dispose();
        map.dispose();
    }
}
