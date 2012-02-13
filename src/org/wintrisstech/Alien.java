package org.wintrisstech;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

class Alien {

    protected static final Random r = new Random();
    protected Color color;
    public int x;
    public int y;
    public int w = 20;
    public int h = 20;
    public boolean visible = true;

    public void paint(Graphics2D g2) {
        if (visible) {
            g2.setColor(color);
            g2.fillRect(x - w / 2, y - h / 2, w, h);
        }
    }

    public void update(Ship ship) {
        // Do nothing
    }
}
