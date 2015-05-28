package com.ants.crafty.desktop;

//import java.awt.Toolkit;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ants.crafty.GameMain;
import com.ants.crafty.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = Toolkit.getDefaultToolkit().getScreenSize().width;
		//config.height = Toolkit.getDefaultToolkit().getScreenSize().height;
		config.width = 1280;
		config.height = 720;
		config.vSyncEnabled = true;
		config.addIcon("logos/logo_32.png", FileType.Internal);
		config.fullscreen = false;
		config.title = Settings.Title + " • " + Settings.Version;
		new LwjglApplication(new GameMain(), config);
	}
}
