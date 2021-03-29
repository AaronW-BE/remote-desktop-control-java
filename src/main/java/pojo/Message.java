package pojo;

import java.io.ByteArrayOutputStream;

public abstract class Message {
    int messageSize;
    int id;
    int type;
    long time = System.currentTimeMillis();

    public int getType() {
        return type;
    }

    public void setHead(int messageSize, int id, int type, long time) {
        this.messageSize = messageSize;
        this.id = id;
        this.type = type;
        this.time = time;
    }

    public byte[] getHeadBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(messageSize);
        byteArrayOutputStream.write(id);
        byteArrayOutputStream.write(type);
        byteArrayOutputStream.write((int) time);
        return byteArrayOutputStream.toByteArray();
    }

    public abstract byte[] toBytes();
}