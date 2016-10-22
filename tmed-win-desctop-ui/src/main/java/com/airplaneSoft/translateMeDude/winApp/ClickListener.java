package com.airplaneSoft.translateMeDude.winApp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by Mezentsev.Y on 10/21/2016.
 */
class ClickListener extends MouseAdapter implements ActionListener {
    private final static int clickInterval = (Integer) Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval");
    private MouseEvent lastEvent;
    private Timer timer;

    public ClickListener() {
        this(clickInterval);
    }

    public ClickListener(int delay) {
        timer = new Timer(delay, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*if (e.getClickCount() > 2) {
            return;
        }
        lastEvent = e;
        if (timer.isRunning()) {
            timer.stop();
            doubleClick(lastEvent);
        } else {
            timer.restart();
        }*/

        if (timer.isRunning() && !e.isConsumed() && e.getClickCount() > 1) {
            //System.out.println("double");
            doubleClick(e);
            timer.stop();
        } else {
            timer.restart();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        singleClick(lastEvent);
    }

    public void singleClick(MouseEvent e) {
    }

    public void doubleClick(MouseEvent e) {
    }
}