package pojo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PubImageMessage extends Message{
    BufferedImage image;

    public PubImageMessage() {

    }

    public PubImageMessage(BufferedImage image) throws IOException {
        this.image = image;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        this.messageSize = getHeadBytes().length + outputStream.size();
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int headSize = getHeadBytes().length;
        outputStream.write(getHeadBytes(), 0, headSize);

        ByteArrayOutputStream imgOut = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", outputStream);
            outputStream.write(imgOut.toByteArray(), headSize, outputStream.size());
            return new byte[0];
        } catch (IOException e) {
            e.printStackTrace();
            return getHeadBytes();
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
