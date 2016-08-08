package com.pitlv.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pitlv.game.startingPoint;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Just Weird";
		config.width = 480;
		config.height = 270;
		new LwjglApplication(new startingPoint(), config);
	}
}
