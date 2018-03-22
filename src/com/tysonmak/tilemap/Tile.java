package com.tysonmak.tilemap;

import com.tysonmak.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];

    public static Tile grass1 = new Tile(Assets.grass1, 0, false);
    public static Tile grass2 = new Tile(Assets.grass2, 1, false);
    public static Tile grass3 = new Tile(Assets.grass3, 2, false);
    public static Tile grass4 = new Tile(Assets.grass4, 3, false);
    public static Tile grass5 = new Tile(Assets.grass5, 4, false);
    public static Tile grass6 = new Tile(Assets.grass6, 5, false);

    public static Tile log = new Tile(Assets.log, 8, true);
    public static Tile bush = new Tile(Assets.bush, 6, true);
    public static Tile tree = new Tile(Assets.tree, 7, true);

    public static Tile chestClosed = new Tile(Assets.chestClosed, 14, true);
    public static Tile chestOpened = new Tile(Assets.chestOpened, 15, true);
    public static Tile chestEmpty = new Tile(Assets.chestEmpty, 22, true);

    public static Tile water1 = new Tile(Assets.water1, 9, false);
    public static Tile water2 = new Tile(Assets.water2, 10, false);
    public static Tile water3 = new Tile(Assets.water3, 11, false);
    public static Tile water4 = new Tile(Assets.water4, 17, false);
    public static Tile water5 = new Tile(Assets.water5, 18, false);
    public static Tile water6 = new Tile(Assets.water6, 19, false);
    public static Tile water7 = new Tile(Assets.water7, 25, false);
    public static Tile water8 = new Tile(Assets.water8, 26, false);
    public static Tile water9 = new Tile(Assets.water9, 27, false);

    public static Tile water10 = new Tile(Assets.water10, 12, false);
    public static Tile water11 = new Tile(Assets.water11, 13, false);
    public static Tile water12 = new Tile(Assets.water12, 20, false);
    public static Tile water13 = new Tile(Assets.water13, 21, false);

    public static Tile axe = new Tile(Assets.axe, 31, false);
    public static Tile boat = new Tile(Assets.boat, 30, false);

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    private BufferedImage texture;
    private final int id;
    private boolean solid;

    public Tile(BufferedImage texture, int id, boolean solid) {
        this.texture = texture;
        this.id = id;
        this.solid = solid;

        tiles[id] = this;
    }

    public void render(Graphics2D g, int x, int y) {
        g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isWater() {
        if(id == 9 || id == 10 || id == 11 || id == 17 || id == 18 || id == 19 || id == 25 || id == 26 || id == 27 || id == 12 || id == 13 || id == 20 || id == 21)
            return true;
        else
            return false;
    }

    public int getId() {
        return id;
    }
}
