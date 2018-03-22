package com.tysonmak.graphics;

import com.tysonmak.application.Application;
import com.tysonmak.entity.manager.Entity;
import com.tysonmak.tilemap.Tile;

public class Camera {

    private float x;
    private float y;

    private Application app;

    public Camera(Application app, float x, float y) {
        this.app = app;
        this.x = x;
        this.y = y;
    }

    public void move(float xAmt, float yAmt) {
        x += xAmt;
        y += yAmt;
    }

    public void centerOnEntity(Entity e) {
        x = e.getX() - app.getWidth() / 2 + e.getWidth() / 2;
        y = e.getY() - app.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }

    public void checkBlankSpace() {
        if(x < 0) {
            x = 0;
        } else if(x > 40 * Tile.WIDTH - app.getWidth()) {
            x = 40 * Tile.WIDTH - app.getWidth();
        }

        if(y < 0) {
            y = 0;
        } else if(y > 40 * Tile.HEIGHT - app.getHeight()) {
            y = 40 * Tile.HEIGHT - app.getHeight();
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
