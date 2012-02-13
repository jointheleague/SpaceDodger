package org.wintrisstech;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This project is designed as a starting point for students learning about
 * classes and method parameters.
 *
 * @author aaron@wintrisstech.org (Aaron VonderHaar)
 * @author http://wintrisstech.org
 */
public class SpaceDodger extends JComponent implements ActionListener, MouseListener,
        KeyListener, MouseMotionListener {

    public static final int windowWidth = 1280;
    public static final int windowHeight = 720;
    private Background background = new Background();
    private Ship ship = new Ship();
    private Alien[] aliens;
    private int wave;
    private int waveMessageCounter;
    private boolean gameOver;
    private boolean gameWon;

    public static void main(String[] args) {
        JFrame window = new JFrame();
        SpaceDodger game = new SpaceDodger();
        window.add(game);
        window.addKeyListener(game);
        window.addMouseListener(game);
        window.addMouseMotionListener(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        Timer t = new Timer(100, game);
        t.start();
    }

    public SpaceDodger() {
        startWave(1);
    }

    public void startWave(int nextWave) {
        gameOver = false;
        gameWon = false;
        wave = nextWave;
        waveMessageCounter = 30;

        if (wave == 1) {
            aliens = new Alien[10];
            for (int i = 0; i < aliens.length; i++) {
                aliens[i] = new PlainAlien();
            }
        }
        if (wave == 2) {
            gameWon = true;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(windowWidth, windowHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        background.paint(g2);
        ship.paint(g2);

        for (int i = 0; i < aliens.length; i++) {
            aliens[i].paint(g2);
        }
        
        if (gameOver) {
            drawMessage(g2, "You've been hit!");
        } else if (gameWon) {
            drawMessage(g2, "Congratulations, you've escaped!");
        } else if (waveMessageCounter > 0) {
            drawMessage(g2, "Wave " + wave);
        }
    }

    /**
     * Draws a message centered in the window.
     */
    private void drawMessage(Graphics2D g2, String message) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("serif", Font.PLAIN, 72));
        // Calculate the size of the text so we can center it in the window
        FontMetrics metrics = g2.getFontMetrics();
        Rectangle2D textBounds = metrics.getStringBounds(message, g2);
        g2.drawString(message, windowWidth / 2 - (int) textBounds.getCenterX(),
                windowHeight / 2 - (int) textBounds.getCenterY());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Update the ship
        ship.update();

        if (gameOver || gameWon) {
            repaint();
            return;
        }
        
        waveMessageCounter -= 1;

        // Update the aliens
        for (int i = 0; i < aliens.length; i++) {
            aliens[i].update(ship);
        }
        
        // Count the visible aliens
        int visibleAliens = 0;
        for (int i = 0; i < aliens.length; i++) {
            aliens[i].update(ship);
            if (aliens[i].visible) {
                visibleAliens++;
            }
        }

        // If there are no more aliens visible, start the next wave
        if (visibleAliens == 0) {
            startWave(wave + 1);
        }
        
        // Check if the ship has collided with any of the aliens
        for(int i = 0; i < aliens.length; i++) {
            // Check for a collision of the alien with the ship
            if (aliens[i].visible
                    && ship.x < aliens[i].x + aliens[i].w
                    && aliens[i].x < ship.x + ship.w
                    && ship.y < aliens[i].y + aliens[i].h
                    && aliens[i].y < ship.y + ship.h)
            {
                gameOver = true;
            }
        }

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (gameOver == true) {
            startWave(wave);
        } else {
            ship.accelerate();
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.rotateLeft();
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.rotateRight();
        } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
            ship.accelerate();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }
}
