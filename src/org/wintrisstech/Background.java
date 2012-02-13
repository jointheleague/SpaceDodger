package org.wintrisstech;

import java.awt.Color;
import java.awt.Graphics2D;

class Background {
    
    private Color spaceColor = new Color(60, 60, 60);

    void paint(Graphics2D g2) {
        g2.setColor(spaceColor);
        g2.fillRect(0, 0, SpaceDodger.windowWidth, SpaceDodger.windowHeight);
    }
    
}
