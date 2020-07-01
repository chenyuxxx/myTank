package com.bjmashibing.demo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class ResourcesMgr {
    public static BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;
    public static BufferedImage badTankL, badTankR, badTankU, badTankD;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            goodTankU = ImageIO.read(Objects.requireNonNull(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png")));
            goodTankR = ImageUtil.rotateImage(goodTankU,90);
            goodTankL = ImageUtil.rotateImage(goodTankR,180);
            goodTankD = ImageUtil.rotateImage(goodTankU,180);

            badTankU = ImageIO.read(Objects.requireNonNull(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png")));
            badTankR = ImageUtil.rotateImage(badTankU,90);
            badTankL = ImageUtil.rotateImage(badTankU,180);
            badTankD = ImageUtil.rotateImage(badTankU,180);

            bulletU = ImageIO.read(Objects.requireNonNull(ResourcesMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png")));
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletL = ImageUtil.rotateImage(bulletR,180);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(Objects.requireNonNull(ResourcesMgr.class.getClassLoader().
                        getResourceAsStream("images/e" + (i+1) +".gif")));
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
