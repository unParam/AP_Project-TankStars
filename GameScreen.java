package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameScreen implements Screen {
    private Vector2 gravity;
    private World world;
    Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private float accumulator = 0;
    private Body Tank;
    private final static float PPM = 100;

//    private void doPhysicsStep(float deltaTime) {
//        // fixed time step
//        // max frame time to avoid spiral of death (on slow devices)
//        float frameTime = Math.min(deltaTime, 0.25f);
//        accumulator += frameTime;
//        while (accumulator >= Constants.TIME_STEP) {
//            WorldManager.world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
//            accumulator -= Constants.TIME_STEP;
//        }
//    }

    public GameScreen(){
        gravity = new Vector2(0, 0f);
        world = new World(gravity, true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        def.fixedRotation = true;
        Tank = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32/PPM, 32/PPM);
        Tank.createFixture(shape, 1f);
        shape.dispose();
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        debugRenderer.render(world, camera.combined.scl(PPM));
        world.step(1/60, 6, 2); // can use 8,3
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
        world.dispose();
        debugRenderer.dispose();
    }
}
