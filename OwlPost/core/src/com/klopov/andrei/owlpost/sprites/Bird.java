package com.klopov.andrei.owlpost.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int MOVEMENT = 80;
    public static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velosity;
    private Rectangle bounds;
    private Sound flap;

    private Texture bird;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0 ,0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }

    public void update(float dt){
        velosity.add(0, GRAVITY, 0); // сила притяжения
        velosity.scl(dt);
        position.add(MOVEMENT * dt, velosity.y, 0);
        if (position.y <0);


        velosity.scl(1/dt);
        bounds.setPosition(position.x, position.y);

    }

    public void jump() {

        velosity.y = 250; //анимация полета
        flap.play();    // звук крыльев
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() { // освобождаем память
        bird.dispose();
        flap.dispose();
    }
}
