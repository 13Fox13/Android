package com.klopov.andrei.owlpost.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.klopov.andrei.owlpost.OwlPost;

public class MenuState extends State{
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStatManager gsm) {
        super(gsm);
        camera.setToOrtho(false, OwlPost.WIDTH / 2, OwlPost.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png"); //пока это и не кнопка
                        // вообще, конечно, меню еще пилить и пилить
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0); // вроде поправил растяжение экрана
        sb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        sb.end();

    }

    @Override
    public void dispose() { // освобождаем память
        background.dispose();
        playBtn.dispose();
        System.out.println("Dispose");

    }
}
