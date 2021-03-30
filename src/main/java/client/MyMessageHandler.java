package client;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import pojo.Message;
import pojo.MsgType;
import pojo.PubImageMessage;

public class MyMessageHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("my message handler active");
        PubImageMessage pubImageMessage = new PubImageMessage();
        ctx.write(pubImageMessage);
        ctx.flush();
        System.out.println("send message to server in active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read message");
        MessagePack messagePack = new MessagePack();
        Message message = messagePack.convert((Value) msg, Message.class);
        System.out.println(message.getId());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
