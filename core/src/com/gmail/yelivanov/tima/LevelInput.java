package com.gmail.yelivanov.tima;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

public final class LevelInput extends InputAdapter {

    private Screen screen;
    int direction;

    LevelInput(Screen screen) {
        this.screen = screen;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int w = Gdx.graphics.getWidth();
        if (screenX < w / 2) direction = -1;
        else direction = 1;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        direction = 0;
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
