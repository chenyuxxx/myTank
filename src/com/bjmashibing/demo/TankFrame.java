package com.bjmashibing.demo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.Down,this,Group.GOOD);

    List<Tank> tanks = new ArrayList<>();

    List<Bullet> bullet = new ArrayList<>();

    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    List<Explode> explode = new ArrayList<>();

//    Explode explode = new Explode(100,100,this);

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullet.size(), 10, 60);
        g.drawString("敌人的数量：" + tanks.size(), 10, 80);
        myTank.print(g);
        for (int i = 0; i < bullet.size(); i++) {
            bullet.get(i).print(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).print(g);
        }

        for (int i = 0; i < bullet.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullet.get(i).collidWild(tanks.get(j));
            }
        }

        for (int i = 0; i < explode.size(); i++) {
            explode.get(i).paint(g);
        }

    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g){
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        graphics.setColor(c);
        print(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }

            setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bD && !bR) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.Left);
                if (bU) myTank.setDir(Dir.Up);
                if (bD) myTank.setDir(Dir.Down);
                if (bR) myTank.setDir(Dir.Right);
            }

        }

    }


}
