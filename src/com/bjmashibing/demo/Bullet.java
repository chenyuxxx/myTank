package com.bjmashibing.demo;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    private int x, y;
    private Dir dir;

    public static int WIDTH = ResourcesMgr.bulletL.getWidth();
    public static int HEIGHT = ResourcesMgr.bulletL.getHeight();

    private boolean living = true;

    private Group group = Group.BAD;

    private TankFrame tf = null;

    Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir ,TankFrame tf,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void print(Graphics g){
        if (!living){
            tf.bullet.remove(this);
        }
        switch (dir){
            case Right:
                g.drawImage(ResourcesMgr.bulletR,x,y,null);
                break;
            case Left:
                g.drawImage(ResourcesMgr.bulletL,x,y,null);
                break;
            case Down:
                g.drawImage(ResourcesMgr.bulletD,x,y,null);
                break;
            case Up:
                g.drawImage(ResourcesMgr.bulletU,x,y,null);
                break;
        }
        
        move();
        
    }

    private void move() {
        switch (dir){
            case Up:
                y -= SPEED;
                break;
            case Down:
                y += SPEED;
                break;
            case Left:
                x -= SPEED;
                break;
            case Right:
                x += SPEED;
                break;
        }

        //更新 rect的 x y
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
    }

    public void collidWild(Tank tank) {
        if (this.group == tank.getGroup()) return;

        int bx = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;

        //TOOD: 用一个rect来记录子弹的位置
        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if (rect1.intersects(rect2)){
            this.die();
            tank.die();
            tf.explode.add(new Explode(bx,by,tf));
        }
    }

    public void die(){
        this.living = false;
    }

}
