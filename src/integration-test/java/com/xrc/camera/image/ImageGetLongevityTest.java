package com.xrc.camera.image;

import com.xrc.camera.Camera;
import com.xrc.camera.ImageVerifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.image.BufferedImage;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImageGetLongevityTest {

    private final ImageVerifier imageVerifier = new ImageVerifier();

    @Autowired
    private Camera camera;

    @Test
    @Repeat(20)
    public void execute() {
        BufferedImage image = camera.getImage();
        imageVerifier.verify(image);
    }
}
