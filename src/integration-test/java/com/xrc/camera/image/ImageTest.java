package com.xrc.camera.image;

import com.xrc.awt.Dimensions;
import com.xrc.camera.Camera;
import com.xrc.camera.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ImageTest {

    private static final Dimension MIN_IMAGE_SIZE =
            new Dimension(1280, 720);

    @Autowired
    private Camera camera;

    private BufferedImage image;


    @Before
    public void init() throws Exception {
        image = camera.getImage();

        ImageIO.write(image, "JPG", new File(
                Constants.IMAGE_FILE_DATE_FORMAT.format(new Date()) + "_testImage.jpg")
        );
    }

    @Test
    public void checkImageSize() {
        Dimension imageSize = new Dimension(
                image.getWidth(),
                image.getHeight()
        );

        Assert.assertTrue(
                Dimensions.compare(imageSize, MIN_IMAGE_SIZE) >= 0
        );
    }
}