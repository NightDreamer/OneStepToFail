package de.me.nightdreamer.ingame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import de.me.nightdreamer.utils.ResourceManager;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 15.12.13
 * Time: 01:58
 */
public class Player {

    private ResourceManager res;
    public Vector2 position;
    private int map[][];
    private float step = 10f, gravity = 10.0f, jump;
    private ArrayList<Coin> coins;
    public boolean win = false, loose = false, jumping = false, spacePressed = false;

    public Player(ResourceManager res, Vector2 pos, int[][] map, ArrayList<Coin> coins)
    {
        this.res = res;
        this.map = map;
        this.coins = coins;
        position = pos;
    }

    public void update(float delta)
    {
        // coins collect
        int nr = 0;
        boolean collect = false;
        for(Coin tmp : coins)
        {
            if((int)tmp.position.x == (int)position.x && (int)tmp.position.y == (int)position.y)
            {
                nr = coins.indexOf(tmp);
                collect = true;
            }
        }
        if(collect)
        {
            coins.remove(nr);
        }

        // stuff
        if((int)(position.x+1f) > 31)
        {
            win = true;
            return;
        }

        if(!Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            spacePressed = false;
        }

        // gravity
        position.y -= gravity*delta;

        // jumping
        if(!jumping)
        {
            if(!spacePressed && Gdx.input.isKeyPressed(Input.Keys.SPACE))
            {
                jump = 25.0f;
                jumping = true;
                position.y += jump*delta;
                spacePressed = true;
            }
        }
        else
        {
            if(jump > 0.0f)
            {
                jump -= 4.5f*delta*gravity;
                position.y += delta*jump;
                if((map[(int)position.x][(int)(position.y+0.95f)] == 1 || map[(int)(position.x+0.95f)][(int)(position.y+0.95f)] == 1) || (map[(int)position.x][(int)(position.y+0.95f)] == 5 || map[(int)(position.x+0.95f)][(int)(position.y+0.95f)] == 5))
                {
                    jump = 0.0f;
                    position.y = (int)position.y;
                }
            }

        }

        if((map[(int)position.x][(int)position.y] == 1 || map[(int)(position.x+0.95f)][(int)position.y] == 1) || (map[(int)position.x][(int)position.y] == 5 || map[(int)(position.x+0.95f)][(int)position.y] == 5))
        {
            position.y += gravity*delta;
            position.y = (int)position.y;
            if(jumping)
            {
                jumping = false;
            }
        }

        // left & right movement
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            position.x -= delta*step;
            if((map[(int)position.x][(int)position.y] == 1 || map[(int)position.x][(int)(position.y+0.95f)] == 1) || (map[(int)position.x][(int)position.y] == 5 || map[(int)position.x][(int)(position.y+0.95f)] == 5))
            {
                position.x += delta*step;
                position.x = (int)position.x;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            position.x += delta*step;

            if((int)(position.x+1f) > 31)
            {
                win = true;
            }
            else if((map[(int)(position.x+0.95f)][(int)position.y] == 1 || map[(int)(position.x+0.95f)][(int)(position.y+0.95f)] == 1) || (map[(int)(position.x+0.95f)][(int)position.y] == 5 || map[(int)(position.x+0.95f)][(int)(position.y+0.95f)] == 5))
            {
                position.x = (int)position.x;
            }
        }
    }

    public void render(float delta)
    {
        res.shapeRenderer.setColor(Color.RED);
        res.shapeRenderer.rect(position.x*37.5f, position.y*37.5f, 37.5f, 37.5f);
    }
}
