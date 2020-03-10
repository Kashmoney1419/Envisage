package com.envisage.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.envisage.game.Envisage;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Envisage";
		config.resizable = false;
		config.width = 696; //1856 / 2.66 // 58
		config.height = 588+120; //1568 / 2.66 + 320 / 2.66 // 49
		new LwjglApplication(new Envisage(), config);
	}
}
