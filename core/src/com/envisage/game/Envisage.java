package com.envisage.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Envisage extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public TextureAtlas textureAtlas;
    public Skin skin;
    public TextButton.TextButtonStyle textButtonStyle;


    public void create() {
        batch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("text_button_skin/default.fnt"));
        font.getData().setScale(2f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        textureAtlas = new TextureAtlas(Gdx.files.internal("text_button_skin/uiskin.atlas"));

        skin = new Skin();
        skin.addRegions(textureAtlas);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("default-round");
        textButtonStyle.down = skin.getDrawable("default-round-down");

        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
        textureAtlas.dispose();
        skin.dispose();
        this.getScreen().dispose();
    }

    public void close() {
        Gdx.app.exit();
    }
}
