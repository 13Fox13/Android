package com.klopov.andrei.owlpost.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.klopov.andrei.owlpost.OwlPost;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = OwlPost.WIDTH;
		config.height = OwlPost.HEIGHT;
		config.title = OwlPost.TITLE;
		new LwjglApplication(new OwlPost(), config);
	}
}
