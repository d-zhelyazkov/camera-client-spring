package com.xrc.camera;

import com.xrc.awt.Dimension;
import com.xrc.awt.Dimensions;
import org.junit.Assert;

import java.awt.image.RenderedImage;

public class ImageVerifier {

    private static final Dimension NULL_DIMENSION = new Dimension(0, 0);

    public void verify(RenderedImage image) {
        Assert.assertNotNull(image);

        Dimension imageDimension = new Dimension(image.getWidth(), image.getHeight());
        Assert.assertTrue("Invalid image dimension: " + imageDimension,
                Dimensions.compare(imageDimension, NULL_DIMENSION) > 0
        );
    }
}
