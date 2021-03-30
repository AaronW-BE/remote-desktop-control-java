package server;

import io.netty.channel.*;
import pojo.Message;
import java.awt.*;
import java.io.IOException;

public class ServerSocketChannel extends ChannelHandlerAdapter  {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws IOException, AWTException { // (1)
        Message message = new Message();
        System.out.println(message.getId());
        ctx.writeAndFlush(message);
        System.out.println("send msg to client");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read message from client");
    }
}
