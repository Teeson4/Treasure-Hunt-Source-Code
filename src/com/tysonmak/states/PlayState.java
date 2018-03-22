package com.tysonmak.states;

import com.tysonmak.application.Application;
import com.tysonmak.audio.AudioPlayer;
import com.tysonmak.entity.Player;
import com.tysonmak.graphics.Assets;
import com.tysonmak.graphics.FontLoader;
import com.tysonmak.states.manager.GameState;
import com.tysonmak.states.manager.GameStateManager;
import com.tysonmak.tilemap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {

    private TileMap map;
    private Player player;

    private AudioPlayer music, finish;

    private int count;
    private int timeSeconds;
    private int timeMinutes;

    public PlayState(Application app, GameStateManager gsm) {
        super(app, gsm);

        map = new TileMap(app, "/map_final.map");
        player = new Player(app, gsm, map);

        music = new AudioPlayer("/sound/bgmusic.mp3");

        finish = new AudioPlayer("/sound/finish.mp3");

        music.setVolume(-10);
        music.loop();

        count = 0;
        timeSeconds = 0;
        timeMinutes = 0;
    }

    @Override
    public void update() {
        map.update();
        player.update();
        count++;

        if(!player.isFinished()) {
            if(timeSeconds == 60) {
                timeMinutes++;
                timeSeconds = 0;
            }

            if(count == 60) {
                timeSeconds++;
                count = 0;
            }
        }

        if(player.getTreasureAmt() == 10) {
            music.stop();

            finish.setVolume(-10);
            finish.play();

            app.setTimeMinutes(timeMinutes);
            app.setTimeSeconds(timeSeconds);

            gsm.setState(GameStateManager.GAMEOVER);
        }

        if(app.getKeys().keyJustPressed(KeyEvent.VK_M) && !music.getClip().isRunning())
            music.play();
        else if(app.getKeys().keyJustPressed(KeyEvent.VK_M) && music.getClip().isRunning())
            music.stop();
    }

    @Override
    public void render(Graphics2D g) {
        map.render(g);
        player.render(g);
        renderInventory(g);
    }

    public void renderInventory(Graphics2D g) {
        for(int i = 0; i < 8; i++) {
            g.drawImage(Assets.invBar, i * 64, 512, 64, 64, null);
        }

        if(player.isHasAxe())
            g.drawImage(Assets.axeSlot, 384, 512, 64, 64, null);
        else
            g.drawImage(Assets.blankSlot, 384, 512, 64, 64, null);

        if(player.isHasBoat())
            g.drawImage(Assets.boatSlot, 448, 512, 64, 64, null);
        else
            g.drawImage(Assets.blankSlot, 448, 512, 64, 64, null);

        g.drawImage(Assets.clock, 0, 512, 64, 64, null);
        g.drawImage(Assets.coins, 192, 512, 64, 64, null);

        switch (player.getTreasureAmt()) {
            case 0:
                g.drawImage(Assets.barEmpty, 256, 512, 128, 64, null);
                break;
            case 1:
                g.drawImage(Assets.bar1, 256, 512, 128, 64, null);
                break;
            case 2:
                g.drawImage(Assets.bar2, 256, 512, 128, 64, null);
                break;
            case 3:
                g.drawImage(Assets.bar3, 256, 512, 128, 64, null);
                break;
            case 4:
                g.drawImage(Assets.bar4, 256, 512, 128, 64, null);
                break;
            case 5:
                g.drawImage(Assets.bar5, 256, 512, 128, 64, null);
                break;
            case 6:
                g.drawImage(Assets.bar6, 256, 512, 128, 64, null);
                break;
            case 7:
                g.drawImage(Assets.bar7, 256, 512, 128, 64, null);
                break;
            case 8:
                g.drawImage(Assets.bar8, 256, 512, 128, 64, null);
                break;
            case 9:
                g.drawImage(Assets.bar9, 256, 512, 128, 64, null);
                break;
            case 10:
                g.drawImage(Assets.bar10, 256, 512, 128, 64, null);
                break;
        }

        if(timeSeconds >= 10)
            FontLoader.drawString(g, timeMinutes + ":" + timeSeconds, 74, 554, false, Color.BLACK, Assets.fontLarge);
        else
            FontLoader.drawString(g, timeMinutes + ":0" + timeSeconds, 74, 554, false, Color.BLACK, Assets.fontLarge);

        FontLoader.drawString(g, player.getTreasureAmt() + "", 246, 554, false, Color.BLACK, Assets.fontLarge);
    }
}
