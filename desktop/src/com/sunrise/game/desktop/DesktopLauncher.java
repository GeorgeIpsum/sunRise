package com.sunrise.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sunrise.game.SUNRISE;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class DesktopLauncher {
	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = true;

	public static void main (String[] arg) {
		if(rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.duplicatePadding = false;
			settings.debug = drawDebugOutline;
			TexturePacker.process(settings, "assets-raw", "pack", "sunrise.pack");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SUNRISE(), config);
	}
}
