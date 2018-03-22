package com.tysonmak.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static FontLoader loader;

    private static final int width = 16;
    private static final int height = 16;

    private static final int widthC = 16;
    private static final int heightC = 32;

    private static final int widthA = 32;
    private static final int heightA = 32;

    public static BufferedImage grass1, grass2, grass3, grass4, grass5, grass6;
    public static BufferedImage log, bush, tree;
    public static BufferedImage water1, water2, water3, water4, water5, water6, water7, water8, water9, water10, water11, water12, water13;

    public static BufferedImage chestClosed, chestOpened, chestEmpty;

    public static BufferedImage infoIcon;

    public static BufferedImage[] playerLeft, playerRight, playerUp, playerDown;
    public static BufferedImage[] leftStill, rightStill, upStill, downStill;
    public static BufferedImage[] boatLeft, boatRight, boatUp, boatDown;

    public static BufferedImage blankSlot, axeSlot, boatSlot, invBar, axe, boat, clock, coins;
    public static BufferedImage barEmpty, bar1, bar2, bar3, bar4, bar5, bar6, bar7, bar8, bar9, bar10, rank;

    public static Font font, font2, fontLarge, fontExtraLarge;

    public static void create() {
        loader = new FontLoader();
        SpriteSheet tileSet = new SpriteSheet(ImageLoader.load("/tileset.png"));

        grass1 = tileSet.crop(0, 0, width, height);
        grass2 = tileSet.crop(width, 0, width, height);
        grass3 = tileSet.crop(width * 2, 0, width, height);
        grass4 = tileSet.crop(width * 3, 0, width, height);
        grass5 = tileSet.crop(width * 4, 0, width, height);
        grass6 = tileSet.crop(width * 5, 0, width, height);

        log = tileSet.crop(0, height, width, height);
        bush = tileSet.crop(width * 6, 0, width, height);
        tree = tileSet.crop(width * 7, 0, width, height);

        chestClosed = tileSet.crop(width * 6, height, width, height);
        chestOpened = tileSet.crop(width * 7, height, width, height);
        chestEmpty = tileSet.crop(width * 6, height * 2, width, height);

        infoIcon = tileSet.crop(width * 7, height * 2, width, height);

        water1 = tileSet.crop(width, height, width, height);
        water2 = tileSet.crop(width * 2, height, width, height);
        water3 = tileSet.crop(width * 3, height, width, height);
        water4 = tileSet.crop(width, height * 2, width, height);
        water5 = tileSet.crop(width * 2, height * 2, width, height);
        water6 = tileSet.crop(width * 3, height * 2, width, height);
        water7 = tileSet.crop(width, height * 3, width, height);
        water8 = tileSet.crop(width * 2, height * 3, width, height);
        water9 = tileSet.crop(width * 3, height * 3, width, height);

        water10 = tileSet.crop(width * 4, height, width, height);
        water11 = tileSet.crop(width * 5, height, width, height);
        water12 = tileSet.crop(width * 4, height * 2, width, height);
        water13 = tileSet.crop(width * 5, height * 2, width, height);

        SpriteSheet characterSet = new SpriteSheet(ImageLoader.load("/character.png"));

        playerLeft = new BufferedImage[4];
        playerRight = new BufferedImage[4];
        playerUp = new BufferedImage[4];
        playerDown = new BufferedImage[4];

        leftStill = new BufferedImage[1];
        rightStill = new BufferedImage[1];
        upStill = new BufferedImage[1];
        downStill = new BufferedImage[1];

        boatLeft = new BufferedImage[1];
        boatRight = new BufferedImage[2];
        boatUp = new BufferedImage[3];
        boatDown = new BufferedImage[4];

        playerLeft[0] = characterSet.crop(0, heightC * 3, widthC, heightC);
        playerLeft[1] = characterSet.crop(widthC, heightC * 3, widthC, heightC);
        playerLeft[2] = characterSet.crop(widthC * 2, heightC * 3, widthC, heightC);
        playerLeft[3] = characterSet.crop(widthC * 3, heightC * 3, widthC, heightC);

        playerRight[0] = characterSet.crop(0, heightC, widthC, heightC);
        playerRight[1] = characterSet.crop(widthC, heightC, widthC, heightC);
        playerRight[2] = characterSet.crop(widthC * 2, heightC, widthC, heightC);
        playerRight[3] = characterSet.crop(widthC * 3, heightC, widthC, heightC);

        playerUp[0] = characterSet.crop(0, heightC * 2, widthC, heightC);
        playerUp[1] = characterSet.crop(widthC, heightC * 2, widthC, heightC);
        playerUp[2] = characterSet.crop(widthC * 2, heightC * 2, widthC, heightC);
        playerUp[3] = characterSet.crop(widthC * 3, heightC * 2, widthC, heightC);

        playerDown[0] = characterSet.crop(0, 0, widthC, heightC);
        playerDown[1] = characterSet.crop(widthC, 0, widthC, heightC);
        playerDown[2] = characterSet.crop(widthC * 2, 0, widthC, heightC);
        playerDown[3] = characterSet.crop(widthC * 3, 0, widthC, heightC);

        leftStill[0] = characterSet.crop(0, heightC * 3, widthC, heightC);
        rightStill[0] = characterSet.crop(0, heightC, widthC, heightC);
        upStill[0] = characterSet.crop(0, heightC * 2, widthC, heightC);
        downStill[0] = characterSet.crop(0, 0, widthC, heightC);

        SpriteSheet boatAnim = new SpriteSheet(ImageLoader.load("/boatanim.png"));

        boatLeft[0] = boatAnim.crop(0, heightA * 3, widthA, heightA);
        boatRight[0] = boatAnim.crop(0, heightA, widthA, heightA);
        boatUp[0] = boatAnim.crop(0, heightA * 2, widthA, heightA);
        boatDown[0] = boatAnim.crop(0, 0, widthA, heightA);

        blankSlot = tileSet.crop(0, height * 2, width, height);
        invBar = tileSet.crop(0, height * 3, width, height);
        axeSlot = tileSet.crop(width * 5, height * 3, width, height);
        boatSlot = tileSet.crop(width * 4, height * 3, width, height);
        axe = tileSet.crop(width * 7, height * 3, width, height);
        boat = tileSet.crop(width * 6, height * 3, width, height);
        clock = tileSet.crop(0, height * 4, width, height);
        coins = tileSet.crop(width, height * 4, width, height);

        barEmpty = tileSet.crop(width * 6, height * 6, widthA, height);
        bar1 = tileSet.crop(width * 2, height * 4, widthA, height);
        bar2 = tileSet.crop(width * 4, height * 4, widthA, height);
        bar3 = tileSet.crop(width * 6, height * 4, widthA, height);
        bar4 = tileSet.crop(0, height * 5, widthA, height);
        bar5 = tileSet.crop(width * 2, height * 5, widthA, height);
        bar6 = tileSet.crop(width * 4, height * 5, widthA, height);
        bar7 = tileSet.crop(width * 6, height * 5, widthA, height);
        bar8 = tileSet.crop(0, height * 6, widthA, height);
        bar9 = tileSet.crop(width * 2, height * 6, widthA, height);
        bar10 = tileSet.crop(width * 4, height * 6, widthA, height);

        rank = tileSet.crop(0, height * 7, width, height);

        font = loader.load("font.ttf", 24);
        font2 = loader.load("font.ttf", 30);
        fontLarge = loader.load("font.ttf", 36);
        fontExtraLarge = loader.load("font.ttf", 70);
    }

}
