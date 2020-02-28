package com.envisage.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.envisage.game.Envisage;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Envisage";
		config.resizable = false;
		config.width = 1160; //1160
		config.height = 980; //980
		new LwjglApplication(new Envisage(), config);
	}
}
