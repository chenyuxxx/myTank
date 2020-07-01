package com.bjmashibing.demo;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame f = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.key("initTankCount")) ;

        for (int i = 0; i < initTankCount; i++) {
            f.tanks.add(new Tank(30 + i * 50, 100, Dir.Down,f,Group.BAD));
        }

        while(true) {
            Thread.sleep(50);
            f.repaint();
        }

    }
}
