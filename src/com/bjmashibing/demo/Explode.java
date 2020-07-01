package com.bjmashibing.demo;

import java.awt.*;

public class Explode {
    private int x,y;

    public static int WIDTH = ResourcesMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourcesMgr.explodes[0].getHeight();

    private int step = 0;

    private TankFrame tf = null;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g){
        g.drawImage(ResourcesMgr.explodes[step++],x,y,null);
        if (step >= ResourcesMgr.explodes.length) tf.explode.remove(this);
    }
}
