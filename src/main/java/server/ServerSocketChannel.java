package server;

import client.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.MessageToByteEncoder;
import pojo.PubImageMessage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ServerSocketChannel extends ChannelInboundHandlerAdapter  {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws IOException, AWTException { // (1)
        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        BufferedImage image = robot.createScreenCapture(screenRectangle);

        PubImageMessage pubImageMessage = new PubImageMessage(image);
        ChannelFuture f = ctx.writeAndFlush(pubImageMessage);
        f.addListener(ChannelFutureListener.CLOSE);
    }
}

class TimeEncoder extends MessageToByteEncoder<UnixTime> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, UnixTime unixTime, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt((int) unixTime.value());
    }
}
