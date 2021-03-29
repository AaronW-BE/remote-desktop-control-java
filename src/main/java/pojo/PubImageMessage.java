package pojo;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PubImageMessage extends Message{
    BufferedImage image;

    PubImageMessage() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        this.messageSize = outputStream.size();
    }

    @Override
    byte[] toBytes() {
        return new byte[0];
    }
}
