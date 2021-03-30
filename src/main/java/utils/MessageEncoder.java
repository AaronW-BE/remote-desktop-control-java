package utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.msgpack.MessagePack;
import pojo.Message;

import java.util.List;

public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        System.out.println("encode msg");
        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(message);
        System.out.println("write bytes");
        byteBuf.writeBytes(raw);
    }
}
