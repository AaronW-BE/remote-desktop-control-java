package pojo;

import java.io.ByteArrayOutputStream;

public abstract class Message {
    int messageSize;
    int id;
    int type;
    long time = System.currentTimeMillis();

    byte[] getHeadBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(messageSize);
        byteArrayOutputStream.write(id);
        byteArrayOutputStream.write(type);
        byteArrayOutputStream.write((int) time);
        return byteArrayOutputStream.toByteArray();
    }

    abstract byte[] toBytes();
}