package com.bjmashibing.demo;

import com.bjmashibing.demo.design.DefaultFireStrategy;
import com.bjmashibing.demo.design.FireStrategy;
import com.bjmashibing.demo.design.FourDirFireStrategy;

import java.awt.*;
import java.util.Random;

public class Tank {

    private int x,y;
    private Dir dir = Dir.Down;
    private static final int SPEED = 5;

    public static int WIDTH = ResourcesMgr.goodTankL.getWidth();
    public static int HEIGHT = ResourcesMgr.goodTankL.getHeight();

    public Group group = Group.BAD;

    private Random random = new Random();

    public boolean living = true;

    Rectangle rect = new Rectangle();

    public TankFrame tf = null;

    private boolean moving = true;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir,TankFrame tf,Group group) {
        super();
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

    public void print(Graphics g) {
        switch (dir){
            case Right:
                g.drawImage(this.group == Group.GOOD ? ResourcesMgr.goodTankR:ResourcesMgr.badTankR,x,y,null);
                break;
            case Left:
                g.drawImage(this.group == Group.GOOD ? ResourcesMgr.goodTankL:ResourcesMgr.badTankL,x,y,null);
                break;
            case Down:
                g.drawImage(this.group == Group.GOOD ? ResourcesMgr.goodTankD:ResourcesMgr.badTankD,x,y,null);
                break;
            case Up:
                g.drawImage(this.group == Group.GOOD ? ResourcesMgr.goodTankU:ResourcesMgr.badTankU,x,y,null);
                break;
        }
        move();

    }

    private void move(){
        if (!living) tf.tanks.remove(this);
        if (!moving) return;
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
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            String badFire = (String) PropertyMgr.key("defaultFireStrategy");
//            Class.forName(badFire).newInstance();
            this.fire(DefaultFireStrategy.getInstance());
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) randomDir();

        boundsCheack();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheack() {
        if (this.x < 2 ) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > (TankFrame.GAME_WIDTH - this.WIDTH -2)) x = TankFrame.GAME_WIDTH - this.WIDTH - 2;
        if (this.y > (TankFrame.GAME_HEIGHT - this.HEIGHT -2)) y = TankFrame.GAME_HEIGHT - this.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire(FireStrategy fireStrategy){
        fireStrategy.fire(this);
    }

    public void die(){
        this.living = false;
    }

}
