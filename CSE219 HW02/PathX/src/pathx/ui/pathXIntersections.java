/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathx.ui;

import mini_game.SpriteType;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import static pathx.pathXConstants.*;

/**
 *
 * @author WTang
 */
public class pathXIntersections {

    public int x;
    public int y;
    public boolean open;

    public pathXIntersections(int initX, int initY) {
        x = initX;
        y = initY;
        open = true;
    }

    // ACCESSOR METHODS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOpen() {
        return open;
    }

    // MUTATOR METHODS
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * This toggles the intersection open/closed.
     */
    public void toggleOpen() {
        open = !open;
    }

    public boolean equals(pathXIntersections intersection) {
        boolean bo = false;
        if (this.getX() == intersection.getX() && this.getY() == intersection.getY()) {
            bo = true;
        }
        return bo;
    }

    /**
     * Returns a textual representation of this intersection.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
