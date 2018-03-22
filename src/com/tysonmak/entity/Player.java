package com.tysonmak.entity;

import com.tysonmak.application.Application;
import com.tysonmak.audio.AudioPlayer;
import com.tysonmak.entity.manager.Entity;
import com.tysonmak.graphics.Animation;
import com.tysonmak.graphics.Assets;
import com.tysonmak.states.manager.GameStateManager;
import com.tysonmak.tilemap.Tile;
import com.tysonmak.tilemap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {

    private Animation animation;
    private boolean attacking;

    private Tooltip tip1, tip2, tip3, tip4;

    private boolean hasAxe;
    private boolean hasBoat;
    private boolean onWater;

    private int treasureAmt;

    private AudioPlayer collect;
    private AudioPlayer splash;
    private AudioPlayer destroy;

    private boolean finished;

    public Player(Application app, GameStateManager gsm, TileMap map) {
        super(app, gsm, map, 64 * 18, 64 * 21, 64, 64);
        this.map = map;
        tileSize = Tile.WIDTH;

        up = down = left = right = moving = false;

        vel = 3;

        animation = new Animation();
        animation.setFrames(Assets.playerDown);
        animation.setDelay(10);

        attacking = false;

        tip1 = new Tooltip(app, gsm, map, "Press SPACE to collect");
        tip2 = new Tooltip(app, gsm, map, "Press SPACE to destroy");
        tip3 = new Tooltip(app, gsm, map, "Hint: you need an axe!");
        tip4 = new Tooltip(app, gsm, map, "Hint: you need a boat!");

        hasAxe = false;
        hasBoat = false;
        onWater = false;

        treasureAmt = 0;

        collect = new AudioPlayer("/sound/collect.wav");
        splash = new AudioPlayer("/sound/splash.wav");
        destroy = new AudioPlayer("/sound/tilechange.wav");

        finished = false;
    }

    public void getAction() {
        tileX = (int) x / tileSize;
        tileY = (int) y / tileSize;

        if(hasAxe) {
            if(map.getTile(tileX, tileY - 1).getId() == 8) {
                tip2.show();
                if(app.getKeys().keyJustPressed(KeyEvent.VK_SPACE)) {
                    map.setTile(tileX, tileY - 1, 0);
                    destroy.play();
                }
            } else if(map.getTile(tileX, tileY + 1).getId() == 8) {
                tip2.show();
                if(app.getKeys().keyJustPressed(KeyEvent.VK_SPACE)) {
                    map.setTile(tileX, tileY + 1, 0);
                    destroy.play();
                }
            } else if(map.getTile(tileX - 1, tileY).getId() == 8) {
                tip2.show();
                if(app.getKeys().keyJustPressed(KeyEvent.VK_SPACE)) {
                    map.setTile(tileX - 1, tileY, 0);
                    destroy.play();
                }
            } else if(map.getTile(tileX + 1, tileY).getId() == 8) {
                tip2.show();
                if(app.getKeys().keyJustPressed(KeyEvent.VK_SPACE)) {
                    map.setTile(tileX + 1, tileY, 0);
                    destroy.play();
                }
            } else {
                tip2.hide();
            }
        } else {
            if(map.getTile(tileX, tileY - 1).getId() == 8) {
                tip3.show();
            } else if(map.getTile(tileX, tileY + 1).getId() == 8) {
                tip3.show();
            } else if(map.getTile(tileX - 1, tileY).getId() == 8) {
                tip3.show();
            } else if(map.getTile(tileX + 1, tileY).getId() == 8) {
                tip3.show();
            } else {
                tip3.hide();
            }
        }

        if(map.getTile(tileX, tileY - 1).getId() == 14) {
            map.setTile(tileX, tileY - 1, 22);
        } else if(map.getTile(tileX, tileY + 1).getId() == 14) {
            map.setTile(tileX, tileY + 1, 22);
        } else if(map.getTile(tileX - 1, tileY).getId() == 14) {
            map.setTile(tileX - 1, tileY, 22);
        } else if(map.getTile(tileX + 1, tileY).getId() == 14) {
            map.setTile(tileX + 1, tileY, 22);
        }

        if (map.getTile(tileX, tileY - 1).getId() == 22) {
            tip1.show();

            if(app.getKeys().keyJustPressed(KeyEvent.VK_SPACE)) {
                map.setTile(tileX, tileY - 1, 15);
                treasureAmt++;
                collect.play();
            }
        } else if (map.getTile(tileX, tileY + 1).getId() == 22) {
            tip1.show();

            if(app.getKeys().keyJustPressed(KeyEvent.VK_SPACE)) {
                map.setTile(tileX, tileY + 1, 15);
                treasureAmt++;
                collect.play();
            }
        } else if (map.getTile(tileX - 1, tileY).getId() == 22) {
            tip1.show();

            if(app.getKeys().keyJustPressed(KeyEvent.VK_SPACE)) {
                map.setTile(tileX - 1, tileY, 15);
                treasureAmt++;
                collect.play();
            }
        } else if (map.getTile(tileX + 1, tileY).getId() == 22) {
            tip1.show();

            if(app.getKeys().keyJustPressed(KeyEvent.VK_SPACE)) {
                map.setTile(tileX + 1, tileY, 15);
                treasureAmt++;
                collect.play();
            }
        } else {
            tip1.hide();
        }

        if(map.getTile(tileX, tileY).getId() == 31) {
            map.setTile(tileX, tileY, 0);
            hasAxe = true;
            collect.play();
        } else if(map.getTile(tileX, tileY).getId() == 30) {
            map.setTile(tileX, tileY, 0);
            hasBoat = true;
            collect.play();
        }

        if(!hasBoat) {
            if(map.getTile(tileX, tileY - 1).isWater() || map.getTile(tileX, tileY + 1).isWater() || map.getTile(   tileX - 1, tileY).isWater() || map.getTile(tileX + 1, tileY).isWater())
                tip4.show();
            else
                tip4.hide();
        } else
            tip4.hide();
    }

    @Override
    public void update() {
        app.getCam().centerOnEntity(this);

        if(!finished) {
            boolean current = onWater;

            if(app.getKeys().keyPressed(KeyEvent.VK_UP)) moveUp();
            if(app.getKeys().keyPressed(KeyEvent.VK_DOWN)) moveDown();
            if(app.getKeys().keyPressed(KeyEvent.VK_LEFT)) moveLeft();
            if(app.getKeys().keyPressed(KeyEvent.VK_RIGHT)) moveRight();

            if(map.getTile((int) destinationX / tileSize, (int) destinationY / tileSize).isWater())
                onWater = true;
            else
                onWater = false;

            if(!current && onWater) {
                splash.play();
            }

            getAction();

            tip1.update();
            tip2.update();
            tip3.update();
            tip4.update();

            if(up) {
                if(onWater && animation.getFrames() != Assets.boatUp) {
                    animation.setFrames(Assets.boatUp);
                    animation.setDelay(-1);
                } else if(!onWater && animation.getFrames() != Assets.playerUp) {
                    animation.setFrames(Assets.playerUp);
                    animation.setDelay(10);
                }
            }

            if(down) {
                if(onWater && animation.getFrames() != Assets.boatDown) {
                    animation.setFrames(Assets.boatDown);
                    animation.setDelay(-1);
                } else if(!onWater && animation.getFrames() != Assets.playerDown) {
                    animation.setFrames(Assets.playerDown);
                    animation.setDelay(10);
                }
            }

            if(left) {
                if(onWater && animation.getFrames() != Assets.boatLeft) {
                    animation.setFrames(Assets.boatLeft);
                    animation.setDelay(-1);
                } else if(!onWater && animation.getFrames() != Assets.playerLeft) {
                    animation.setFrames(Assets.playerLeft);
                    animation.setDelay(10);
                }
            }

            if(right) {
                if(onWater && animation.getFrames() != Assets.boatRight) {
                    animation.setFrames(Assets.boatRight);
                    animation.setDelay(-1);
                } else if(!onWater && animation.getFrames() != Assets.playerRight) {
                    animation.setFrames(Assets.playerRight);
                    animation.setDelay(10);
                }
            }

            if(!moving && animation.getFrames() == Assets.playerUp) {
                animation.setFrames(Assets.upStill);
                animation.setDelay(-1);
            } else if(!moving && animation.getFrames() == Assets.playerDown) {
                animation.setFrames(Assets.downStill);
                animation.setDelay(-1);
            } else if(!moving && animation.getFrames() == Assets.playerLeft) {
                animation.setFrames(Assets.leftStill);
                animation.setDelay(-1);
            } else if(!moving && animation.getFrames() == Assets.playerRight) {
                animation.setFrames(Assets.rightStill);
                animation.setDelay(-1);
            } else
                animation.update();

            if(moving) getNextPosition();

            if(x == destinationX && y == destinationY) {
                up = down = left = right = moving = false;
                tileX = (int) x / tileSize;
                tileY = (int) y / tileSize;
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        if(!attacking && !onWater)
            g.drawImage(animation.getImage(), (int) (x - app.getCam().getX()), (int) (y - 36 - app.getCam().getY()), width, height * 2, null);
        else
            g.drawImage(animation.getImage(), (int) (x - (width / 2) - app.getCam().getX()), (int) (y - (height / 2) - app.getCam().getY()), width * 2, height * 2, null);

        tip1.render(g);
        tip2.render(g);
        tip3.render(g);
        tip4.render(g);
    }

    @Override
    public boolean validateNextPosition() {
        if(moving) return true;

        tileX = (int) x / tileSize;
        tileY = (int) y / tileSize;

        if(up) {
            if(tileY == 0 || (map.getTile(tileX, tileY - 1).isSolid()) || (map.getTile(tileX, tileY - 1).isWater() && !hasBoat)) return false;
            else {
                destinationX = x;
                destinationY = y - tileSize;
            }
        }

        if(down) {
            if(tileY == map.getHeight() - 1 || (map.getTile(tileX, tileY + 1).isSolid()) || (map.getTile(tileX, tileY + 1).isWater() && !hasBoat)) return false;
            else {
                destinationX = x;
                destinationY = y + tileSize;
            }
        }

        if(left) {
            if(tileX == 0 || (map.getTile(tileX - 1, tileY).isSolid()) || (map.getTile(tileX - 1, tileY).isWater() && !hasBoat)) return false;
            else {
                destinationX = x - tileSize;
                destinationY = y;
            }
        }

        if(right) {
            if(tileX == map.getWidth() + 1 || (map.getTile(tileX + 1, tileY).isSolid()) || (map.getTile(tileX + 1, tileY).isWater() && !hasBoat)) return false;
            else {
                destinationX = x + tileSize;
                destinationY = y;
            }
        }

        return true;
    }

    public boolean isHasAxe() {
        return hasAxe;
    }

    public void setHasAxe(boolean hasAxe) {
        this.hasAxe = hasAxe;
    }

    public boolean isHasBoat() {
        return hasBoat;
    }

    public void setHasBoat(boolean hasBoat) {
        this.hasBoat = hasBoat;
    }

    public boolean isOnWater() {
        return onWater;
    }

    public void setOnWater(boolean onWater) {
        this.onWater = onWater;
    }

    public int getTreasureAmt() {
        return treasureAmt;
    }

    public void setTreasureAmt(int treasureAmt) {
        this.treasureAmt = treasureAmt;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
