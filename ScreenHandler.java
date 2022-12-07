package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ScreenHandler extends Game {

    static public Skin mySkin;
    private Stage stage;

    public void create () {
        Gdx.input.setInputProcessor(stage);
    }

    public void render () {
        super.render();
    }


    public void dispose () {
    }
}
