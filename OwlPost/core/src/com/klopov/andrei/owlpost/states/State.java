package com.klopov.andrei.owlpost.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStatManager gsm;

    public State(GameStatManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3(); //третье измерение не понадобится
    }

    protected abstract void handleInput();
    public abstract void update (float dt);
    public abstract void render (SpriteBatch sb);
    public abstract void dispose ();
}
