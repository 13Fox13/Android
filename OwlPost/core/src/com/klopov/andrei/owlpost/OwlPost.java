package com.klopov.andrei.owlpost;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.klopov.andrei.owlpost.states.GameStatManager;
import com.klopov.andrei.owlpost.states.MenuState;

public class OwlPost extends ApplicationAdapter {
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 1920;
	public static final String TITLE = "Owl Post";
	private GameStatManager gsm;
	private SpriteBatch batch;
	private Music music;





	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStatManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	@Override
	public void dispose() { // освобождаем память
		super.dispose();
		music.dispose();
	}

}