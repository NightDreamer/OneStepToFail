package de.me.nightdreamer.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 15.12.13
 * Time: 01:33
 */
public class MapLoader {
    public static int[][] loadMap(int lvl)
    {
        int[][] tmp = new int[32][16];

        Pixmap pixmap = new Pixmap(Gdx.files.internal("res/level_"+lvl+".png"));
        for(int x = 0; x < pixmap.getWidth(); x++)
        {
            for(int y = 0; y < pixmap.getHeight(); y++)
            {
                if(pixmap.getPixel(x,y) == Color.rgba8888(Color.WHITE))
                {
                    tmp[x][y] = 0; // Nothing
                }
                else if(pixmap.getPixel(x,y) == Color.rgba8888(Color.BLACK))
                {
                    tmp[x][y] = 1; // Wall
                }
                else if(pixmap.getPixel(x,y) == Color.rgba8888(Color.RED))
                {
                    tmp[x][y] = 2; // Player
                }
                else if(pixmap.getPixel(x,y) == Color.rgba8888(Color.BLUE))
                {
                    tmp[x][y] = 3; // Laser
                }
                else if(pixmap.getPixel(x,y) == Color.rgba8888(Color.YELLOW))
                {
                    tmp[x][y] = 4; // Coin
                }
                else if(pixmap.getPixel(x,y) == Color.rgba8888(Color.GREEN))
                {
                    tmp[x][y] = 5; // Door
                }
            }
        }
        pixmap.dispose();

        int map[][] = new int[32][16];

        for(int x = 0; x < 32; x++)
        {
            for(int y = 0; y < 16; y++)
            {
                map[x][y] = tmp[x][15-y];
            }
        }

        return map;
    }
}
