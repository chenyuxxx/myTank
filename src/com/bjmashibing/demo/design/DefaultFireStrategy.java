package com.bjmashibing.demo.design;

import com.bjmashibing.demo.Bullet;
import com.bjmashibing.demo.Tank;

public class DefaultFireStrategy implements FireStrategy {

    private DefaultFireStrategy(){
    }

    private static class DefaultFireStrategyHolder{
        private final static DefaultFireStrategy INSTANCE = new DefaultFireStrategy();
    }

    public static DefaultFireStrategy getInstance(){
        return DefaultFireStrategyHolder.INSTANCE;
    }

    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        new Bullet(bx,by,tank.getDir(),tank.tf,tank.group);

    }
}
