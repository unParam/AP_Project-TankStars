package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tank {
    private int Health;
    private float Fuel;
    private Sprite sprite;

    public int getHealth() {
        return Health;
    }

    public float getFuel() {
        return Fuel;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public void setFuel(float fuel) {
        Fuel = fuel;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
