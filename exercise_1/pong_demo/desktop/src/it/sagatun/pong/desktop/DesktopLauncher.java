package it.sagatun.pong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import it.sagatun.pong.Pong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Pong.HEIGHT;
		config.width = Pong.WIDTH;
		config.title = Pong.TITLE;
		new LwjglApplication(new Pong(), config);
	}
}
