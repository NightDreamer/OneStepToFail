package de.me.nightdreamer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.me.nightdreamer.utils.ResourceManager;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 15.12.13
 * Time: 17:03
 */
public class GameFinished implements Screen {

    private ResourceManager res;
    private Sprite bg;

    public GameFinished(ResourceManager res)
    {
        this.res = res;
        bg = new Sprite(new Texture(Gdx.files.internal("res/GameFinished.png")));
    }

    @Override
    public void render(float delta) {
        //----------------------------- UPDATE ----------------------//
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            res.gameInstance.setScreen(new Ingame(res, 1));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }

        //----------------------------- RENDER ----------------------//
        res.clearScreen();
        res.spriteBatch.begin();
        bg.draw(res.spriteBatch);
        res.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void show() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        bg.getTexture().dispose();
    }
}
