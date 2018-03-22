package com.tysonmak.entity.manager;

import com.tysonmak.application.Application;
import com.tysonmak.states.manager.GameStateManager;
import com.tysonmak.tilemap.Tile;
import com.tysonmak.tilemap.TileMap;

import java.awt.*;

public abstract class Entity {

    protected Application app;
    protected GameStateManager gsm;

    protected float x;
    protected float y;

    protected int width;
    protected int height;

    protected TileMap map;
    protected int tileSize;

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;
    protected boolean moving;

    protected int tileX;
    protected int tileY;
    protected float destinationX;
    protected float destinationY;

    protected float vel;

    public Entity(Application app, GameStateManager gsm, TileMap map, float x, float y, int width, int height) {
        this.app = app;
        this.gsm = gsm;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.map = map;
        tileSize = Tile.WIDTH;

        up = down = left = right = moving = false;

        vel = 3;
    }

    public boolean validateNextPosition() {
        if(moving) return true;

        tileX = (int) x / tileSize;
        tileY = (int) y / tileSize;

        if(up) {
            if((map.getTile(tileX, tileY - 1).isSolid())) return false;
            else {
                destinationX = x;
                destinationY = y - tileSize;
            }
        }

        if(down) {
            if((map.getTile(tileX, tileY + 1).isSolid())) return false;
            else {
                destinationX = x;
                destinationY = y + tileSize;
            }
        }

        if(left) {
            if((map.getTile(tileX - 1, tileY).isSolid())) return false;
            else {
                destinationX = x - tileSize;
                destinationY = y;
            }
        }

        if(right) {
            if((map.getTile(tileX + 1, tileY).isSolid())) return false;
            else {
                destinationX = x + tileSize;
                destinationY = y;
            }
        }

        return true;
    }

    public void getNextPosition() {
        if(up && (y - destinationY) > 0) y -= vel;
        else up = false;
        if(up && (y - destinationY) < 0) y = destinationY;

        if(down && (destinationY - y) > 0) y += vel;
        else down = false;
        if(down && (destinationY - y) < 0) y = destinationY;

        if(left && (x - destinationX) > 0) x -= vel;
        else left = false;
        if(left && (x - destinationX) < 0 ) x = destinationX;

        if(right && (destinationX - x) > 0) x += vel;
        else right = false;
        if(right && (destinationX - x) < 0) x = destinationX;
    }

    public void moveUp() {
        if(moving) return;
        up = true;
        moving = validateNextPosition();
    }

    public void moveDown() {
        if(moving) return;
        down = true;
        moving = validateNextPosition();
    }

    public void moveLeft() {
        if(moving) return;
        left = true;
        moving = validateNextPosition();
    }

    public void moveRight() {
        if(moving) return;
        right = true;
        moving = validateNextPosition();
    }

    public abstract void update();

    public abstract void render(Graphics2D g);

    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public boolean intersects(Entity entity) {
        return getRectangle().intersects(entity.getRectangle());
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public GameStateManager getGsm() {
        return gsm;
    }

    public void setGsm(GameStateManager gsm) {
        this.gsm = gsm;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
