package org.wintrisstech;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
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
public class SpaceGame extends JComponent implements ActionListener, MouseListener,
        KeyListener, MouseMotionListener {
    
    public static final int windowWidth = 1280;
    public static final int windowHeight = 720;
    
    private Background background = new Background();
    private Ship ship = new Ship();

    public static void main(String[] args) {
        JFrame window = new JFrame("Space");
        SpaceGame game = new SpaceGame();
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(windowWidth, windowHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        background.paint(g2);
        ship.paint(g2);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ship.update();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        ship.accelerate();
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
