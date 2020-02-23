package com.gmail.yelivanov.tima;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.GdxRuntimeException;

public final class LevelScreen implements Screen {
    private MyGdxGame game;
    private SpriteBatch batch;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRenderer;
    private int tileWidth;
    private int tileHeight;
    private LevelInput levelInput;

    LevelScreen(MyGdxGame game) {
        this.game = game;
        this.batch = game.batch;
        levelInput = new LevelInput(this);
    }

    @Override
    public void show() {
        game.assetManager.setLoader(TiledMap.class, new TmxMapLoader());
        try {
            game.assetManager.load("test.tmx", TiledMap.class);
            game.assetManager.finishLoading();
            tiledMap = game.assetManager.get("test.tmx");
        }
        catch (GdxRuntimeException e) {
            Gdx.app.log("LevelScreen.java", e.getMessage());
        }
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tileWidth = tiledMap.getProperties().get("tilewidth", Integer.class);
        tileHeight = tiledMap.getProperties().get("tileheight", Integer.class);
        /*int mapWidthInTiles = tiledMap.getProperties().get("width", Integer.class);
        int mapHeightInTiles = tiledMap.getProperties().get("height", Integer.class);*/

        camera = new OrthographicCamera();
        Gdx.input.setInputProcessor(levelInput);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.x += levelInput.direction * 2;
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        float hWRatio = (float)height / (float)width;
        camera.setToOrtho(false, tileWidth * 10, tileHeight * 10 * hWRatio);
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
