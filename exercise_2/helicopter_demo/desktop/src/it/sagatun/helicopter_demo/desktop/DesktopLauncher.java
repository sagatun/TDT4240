package it.sagatun.helicopter_demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import it.sagatun.helicopter_demo.HelicopterDemo;
import it.sagatun.helicopter_demo.sprites.Helicopter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = HelicopterDemo.WIDTH;
		config.height = HelicopterDemo.HEIGHT;
		config.title = HelicopterDemo.TITLE;
		new LwjglApplication(new HelicopterDemo(), config);
	}
}
