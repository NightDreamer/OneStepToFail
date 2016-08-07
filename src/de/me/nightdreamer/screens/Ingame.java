package de.me.nightdreamer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.me.nightdreamer.ingame.Coin;
import de.me.nightdreamer.ingame.Laser;
import de.me.nightdreamer.ingame.Player;
import de.me.nightdreamer.map.MapLoader;
import de.me.nightdreamer.utils.ResourceManager;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 15.12.13
 * Time: 01:40
 */
public class Ingame implements Screen{

    private ResourceManager res;
    private Player player;
    private ArrayList<Laser> lasers;
    private ArrayList<Coin> coins;
    private int[][] map;
    private int lvl;

    public Ingame(ResourceManager res, int lvl)
    {
        this.res = res;
        map = MapLoader.loadMap(lvl);
        this.lvl = lvl;

        Vector2 startPlayerPos = new Vector2();
        lasers = new ArrayList<Laser>();
        coins = new ArrayList<Coin>();

        for(int x = 0; x < 32; x++)
        {
            for(int y = 0; y < 16; y++)
            {
                if(map[x][y] == 2)
                {
                    startPlayerPos = new Vector2(x,y);
                }
                else if(map[x][y] == 4)
                {
                    coins.add(new Coin(res, new Vector2(x+0.5f,y+0.5f)));
                }
            }
        }

        player = new Player(res, startPlayerPos, map, coins);

        for(int x = 0; x < 32; x++)
        {
            for(int y = 0; y < 16; y++)
            {
                if(map[x][y] == 3)
                {
                    lasers.add(new Laser(res, new Vector2(x+0.5f,y+0.5f), map, player));
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            res.gameInstance.setScreen(new MainMenu(res, true));
        }

        //--------------------- UPDATE -----------------------------//
        player.update(delta);
        if(player.win)
        {
            if(lvl < 3)
            {
                res.gameInstance.setScreen(new Ingame(res,lvl+1));
            }
            else
            {
                res.gameInstance.setScreen(new GameFinished(res));
            }

        }
        else if(player.loose)
        {
            res.gameInstance.setScreen(new GameOver(res));
        }

        //--------------------- RENDER -----------------------------//
        res.clearScreen();

        // camera position
        res.camera.position.set(player.position.x*37.5f, res.camera.position.y, res.camera.position.z);
        Vector3 camPos = res.camera.position;
        if((camPos.x - res.camera.viewportWidth/2) < 0.0f)
        {
            res.camera.position.set(res.camera.viewportWidth/2, res.camera.position.y, res.camera.position.z);
        }
        else if((camPos.x + res.camera.viewportWidth/2) > 32f*37.5f)
        {
            res.camera.position.set(32f*37.5f - res.camera.viewportWidth/2, res.camera.position.y, res.camera.position.z);
        }

        res.camera.update();
        res.shapeRenderer.setProjectionMatrix(res.camera.combined);
        // Laser
        res.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        res.shapeRenderer.setColor(Color.RED);
        for(Laser tmp : lasers)
        {
            tmp.update(delta);
            tmp.render(delta);
        }
        res.shapeRenderer.end();
        // Map & Player
        res.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderMap(player.position.x);
        player.render(delta);
        res.shapeRenderer.setColor(Color.YELLOW);

        if(coins.isEmpty())
        {
            for(int x = 0; x < 32; x++)
            {
                for(int y = 0; y < 15; y++)
                {
                    if(map[x][y] == 5)
                    {
                        map[x][y] = 0;
                    }
                }
            }
        }
        else
        {
            for(Coin tmp : coins)
            {
                tmp.render();
            }
        }

        res.shapeRenderer.end();

    }

    private void renderMap(float xOffset)
    {
        for(int x = 0; x < 32; x++)
        {
            for(int y = 0; y < 16; y++)
            {
                if(map[x][y] == 1 || map[x][y] == 5)
                {
                    res.shapeRenderer.setColor(Color.BLACK);
                    res.shapeRenderer.rect(x*37.5f, y*37.5f, 37.5f, 37.5f);
                }
                else if(map[x][y] == 3)
                {
                    res.shapeRenderer.setColor(Color.BLUE);
                    res.shapeRenderer.rect(x*37.5f, y*37.5f, 37.5f, 37.5f);
                }
            }
        }
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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
