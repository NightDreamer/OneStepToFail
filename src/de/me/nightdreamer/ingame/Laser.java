package de.me.nightdreamer.ingame;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import de.me.nightdreamer.utils.ResourceManager;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 15.12.13
 * Time: 13:40
 */
public class Laser {

    private ResourceManager res;
    private Vector2 position, hitpoint;
    private int map[][];
    private float angle;
    private boolean up, down, left, right, on;
    private float velocity = 25f;
    private Player player;

    public Laser(ResourceManager res, Vector2 position, int[][] map, Player player)
    {
        this.res = res;
        this.position = position;
        this.map = map;
        this.player = player;

        hitpoint = new Vector2(1f,1f);

        // right
        if(map[(int)(position.x - 1)][(int)position.y] == 1)
        {
            right = true;
            angle = 90f;
            on = true;
        }
        // left
        else if(map[(int)(position.x + 1)][(int)position.y] == 1)
        {
            left = true;
            angle = -90f;
            on = false;
        }
        // down
        else if(map[(int)(position.x)][(int)(position.y + 1)] == 1)
        {
            down = true;
            hitpoint.setAngle(180f);
            on = false;

        }
        // up
        else if(map[(int)(position.x)][(int)(position.y - 1)] == 1)
        {
            up = true;
            hitpoint.setAngle(0f);
            on = false;
        }
    }

    private void raycasting()
    {
        // outgoing standard
        Vector2 pos = new Vector2(position.x, position.y);

        do
        {
            pos.add(MathUtils.cosDeg(angle), MathUtils.sinDeg(angle));

            if((int)pos.x < 0f)
            {
                pos.x = 0f;
            }
            else if((int)pos.x > 31f)
            {
                pos.x = 31f;
            }


            if((int)pos.y < 0f)
            {
                pos.y = 0f;
            }
            else if((int)pos.y > 15f)
            {
                pos.y = 15f;
            }

            if((int)pos.x == (int)(player.position.x + 0.5f) && (int)pos.y == (int)(player.position.y + 0.5f))
            {
                player.loose = true;
            }
        }while(map[(int)pos.x][(int)pos.y] != 1);

        hitpoint.set(pos.x, pos.y);
    }

    public void update(float delta)
    {

        if(right)
        {
            if(on)
            {
                angle -= delta*velocity;
                if(angle <= -90f)
                {
                    angle = -90f;
                    on = false;
                }
            }
            else
            {
                angle += delta*velocity;
                if(angle >= 90f)
                {
                    angle = 90f;
                    on = true;
                }
            }
        }
        else if(left)
        {
            if(on)
            {
                angle += delta*velocity;
                if(angle >= 270f)
                {
                    angle = 270f;
                    on = false;
                }
            }
            else
            {
                angle -= delta*velocity;
                if(angle <= 90f)
                {
                    angle = 90f;
                    on = true;
                }
            }
        }
        else if(down)
        {
            if(on)
            {
                angle += delta*velocity;
                if(angle >= 360f)
                {
                    angle = 360f;
                    on = false;
                }
            }
            else
            {
                angle -= delta*velocity;
                if(angle <= 180f)
                {
                    angle = 180f;
                    on = true;
                }
            }
        }
        else if(up)
        {
            if(on)
            {
                angle -= delta*velocity;
                if(angle <= 0f)
                {
                    angle = 0f;
                    on = false;
                }
            }
            else
            {
                angle += delta*velocity;
                if(angle >= 180f)
                {
                    angle = 180f;
                    on = true;
                }
            }
        }
        else
        {
            angle += delta*velocity;
            if(angle >= 360f)
            {
                angle = 0f;
            }
        }

        raycasting();
    }

    public void render(float delta)
    {
        res.shapeRenderer.line(position.x*37.5f, position.y*37.5f, hitpoint.x*37.5f, hitpoint.y*37.5f);
    }

}
