package com.tysonmak.states;

import com.tysonmak.application.Application;
import com.tysonmak.audio.AudioPlayer;
import com.tysonmak.graphics.Assets;
import com.tysonmak.graphics.FontLoader;
import com.tysonmak.states.manager.GameState;
import com.tysonmak.states.manager.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HelpState extends GameState {

    private AudioPlayer collect;

    public HelpState(Application app, GameStateManager gsm) {
        super(app, gsm);

        collect = new AudioPlayer("/sound/collect.wav");
    }

    @Override
    public void update() {
        if(app.getKeys().keyJustPressed(KeyEvent.VK_ENTER)) {
            gsm.setState(GameStateManager.MENU);
            collect.play();
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(125, 112, 113));
        g.fillRect(0, 0, app.getWidth(), app.getHeight());

        FontLoader.drawString(g, "How to Play", app.getWidth() / 2, 35, true, Color.BLACK, Assets.fontLarge);
        FontLoader.drawString(g, "How to Play", app.getWidth() / 2, 32, true, Color.WHITE, Assets.fontLarge);

        g.setColor(Color.BLACK);
        g.fillRect(32, 64, 448, 400);

        FontLoader.drawString(g, "Menu Controls", app.getWidth() / 2, 108, true, new Color(255, 198, 0), Assets.font);
        FontLoader.drawString(g, "UP = Menu option up", 60, 148, false, Color.WHITE, Assets.font);
        FontLoader.drawString(g, "DOWN = Menu option down", 60, 168, false, Color.WHITE, Assets.font);
        FontLoader.drawString(g, "ENTER = Select option", 60, 188, false, Color.WHITE, Assets.font);

        FontLoader.drawString(g, "Game Controls", app.getWidth() / 2, 228, true, new Color(255, 198, 0), Assets.font);
        FontLoader.drawString(g, "UP = Move up", 60, 268, false, Color.WHITE, Assets.font);
        FontLoader.drawString(g, "DOWN = Move down", 60, 288, false, Color.WHITE, Assets.font);
        FontLoader.drawString(g, "LEFT = Move left", 60, 308, false, Color.WHITE, Assets.font);
        FontLoader.drawString(g, "RIGHT = Move right", 60, 328, false, Color.WHITE, Assets.font);
        FontLoader.drawString(g, "SPACE = Action", 60, 348, false, Color.WHITE, Assets.font);
        FontLoader.drawString(g, "M = Toggle music", 60, 368, false, Color.WHITE, Assets.font);
        FontLoader.drawString(g, "Have fun!", 60, 420, false, new Color(255, 198, 0), Assets.font);

        FontLoader.drawString(g, "Press ENTER to return to menu", app.getWidth() / 2, 512, true, Color.BLACK, Assets.font);
    }
}
