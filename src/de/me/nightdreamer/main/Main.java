package de.me.nightdreamer.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import de.me.nightdreamer.screens.Ingame;
import de.me.nightdreamer.screens.MainMenu;
import de.me.nightdreamer.utils.ResourceManager;

/**
 * Created with IntelliJ IDEA.
 * User: NightDreamer
 * Date: 15.12.13
 * Time: 01:29
 */
public class Main extends Game {


    @Override
    public void create() {
        Gdx.gl20.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl20.glEnable(GL20.GL_BLEND);

        setScreen(new MainMenu(new ResourceManager(this), false));
    }

    public static void main(String[] args)
    {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

        cfg.useGL20 = true;
        cfg.vSyncEnabled = true;
        cfg.resizable = false;
        cfg.fullscreen = false;
        cfg.width = 800;
        cfg.height = 600;
        cfg.samples = 8;
        cfg.title = "One Step To Fail | Dev: NightDreamer | Ludum Dare 28 | Theme: 'You Only Get One'";

        new LwjglApplication(new Main(), cfg);
    }

}
