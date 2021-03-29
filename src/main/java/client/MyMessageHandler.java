package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import pojo.Message;
import pojo.MsgType;

public class MyMessageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read message");
        Message message = (Message) msg;
        if (message.getType() == MsgType.PUB_IMAGE) {
            System.out.println("pub image message");
            System.out.println("ok");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
