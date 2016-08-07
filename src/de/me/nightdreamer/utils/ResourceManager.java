package de.me.nightdreamer.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 13.12.13
 * Time: 22:35
 */
public class ResourceManager {

    public Game gameInstance;
    public SpriteBatch spriteBatch;
    public ShapeRenderer shapeRenderer;
    public OrthographicCamera camera;

    public ResourceManager(Game game)
    {
        gameInstance = game;
        spriteBatch = new SpriteBatch();
        spriteBatch.setShader(SpriteBatch.createDefaultShader());
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.position.add(camera.viewportWidth/2, 0f, 0f);
        camera.update();
    }

    public void clearScreen()
    {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void dispose()
    {
        spriteBatch.dispose();
    }

}
