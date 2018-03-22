package com.tysonmak.entity;

import com.tysonmak.application.Application;
import com.tysonmak.entity.manager.Entity;
import com.tysonmak.graphics.Assets;
import com.tysonmak.graphics.FontLoader;
import com.tysonmak.states.manager.GameStateManager;
import com.tysonmak.tilemap.Tile;
import com.tysonmak.tilemap.TileMap;

import java.awt.*;

public class Tooltip extends Entity {

    private String text;
    private boolean show;

    public Tooltip(Application app, GameStateManager gsm, TileMap map, String text) {
        super(app, gsm, map, 16, (Tile.HEIGHT * 7) + 16, 480, 32);
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g) {
        if(show) {
            g.setColor(Color.WHITE);
            g.fillRect((int) x, (int) y, width, height);

            g.drawImage(Assets.infoIcon, (int) x - 16, Tile.HEIGHT * 7, 64, 64, null);

            FontLoader.drawString(g, text, (int) x + 40, (int) y + 23, false, Color.BLACK, Assets.font);
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isShowing() {
        return show;
    }

    public void show() {
        show = true;
    }

    public void hide() {
        show = false;
    }
}
