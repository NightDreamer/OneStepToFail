package de.me.nightdreamer.ingame;

import com.badlogic.gdx.math.Vector2;
import de.me.nightdreamer.utils.ResourceManager;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 15.12.13
 * Time: 03:03
 */
public class Coin {

    private ResourceManager res;
    public Vector2 position;

    public Coin(ResourceManager res, Vector2 position)
    {
        this.res = res;
        this.position = position;
    }

    public void render()
    {
        res.shapeRenderer.circle(position.x*37.5f, position.y*37.5f, 15f);
    }

}
