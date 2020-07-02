package com.bjmashibing.demo.design;

import com.bjmashibing.demo.Bullet;
import com.bjmashibing.demo.Dir;
import com.bjmashibing.demo.Tank;

public class FourDirFireStrategy implements FireStrategy {

    private FourDirFireStrategy() {
    }

    private static class FourDirStrategyHolder {
        private final static FourDirFireStrategy INSTANCE = new FourDirFireStrategy();
    }

    public static FourDirFireStrategy getInstance() {
        return FourDirFireStrategy.FourDirStrategyHolder.INSTANCE;
    }

    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        Dir[] dirs = Dir.values();
        for (Dir dir:dirs) {
            new Bullet(bx, by, dir, tank.tf, tank.group);
        }
    }
}
