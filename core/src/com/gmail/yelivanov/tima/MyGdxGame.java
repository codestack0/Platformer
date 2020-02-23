package com.gmail.yelivanov.tima;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
    AssetManager assetManager;
    SpriteBatch batch;

    @Override
    public void create () {
        assetManager = new AssetManager();
        batch = new SpriteBatch();
        Screen levelScreen = new LevelScreen(this);
        setScreen(levelScreen);
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        Gdx.app.log("MyGdxGame.java","dispose()");
        batch.dispose();
        assetManager.dispose();
    }
}
