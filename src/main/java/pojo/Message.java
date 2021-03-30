package pojo;

import lombok.Getter;
import lombok.Setter;


@org.msgpack.annotation.Message
@Getter
@Setter
public class Message {
    int messageSize;
    int id;
    byte type;
    long time;

    public Message() {
        this.messageSize = 100;
        this.id = 1;
        this.type = 0x0001;
        this.time = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageSize=" + messageSize +
                ", id=" + id +
                ", type=" + type +
                ", time=" + time +
                '}';
    }
}