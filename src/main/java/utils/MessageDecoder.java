package utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import pojo.MsgType;
import pojo.PubImageMessage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("decode message");
        if (byteBuf.readableBytes() >= 4) {
            // 可读取长度
            int size = byteBuf.readInt();
            byteBuf.markReaderIndex();
            if (byteBuf.readableBytes() > size) {
                int id = byteBuf.readInt();
                byteBuf.markReaderIndex();
                int type = byteBuf.readInt();
                byteBuf.markReaderIndex();
                long time = byteBuf.readLong();

                if (type == MsgType.PUB_IMAGE) {
                    // 图片消息
                    System.out.println("pub image message");
                    PubImageMessage pubImageMessage = new PubImageMessage();
                    pubImageMessage.setHead(size, id, type, time);
                    int imageSize = size - pubImageMessage.getHeadBytes().length;
                    ByteBuf imageBuf = byteBuf.readBytes(imageSize);

                    ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBuf.array());
                    BufferedImage image = ImageIO.read(inputStream);
                    pubImageMessage.setImage(image);
                    list.add(pubImageMessage);
                }
            }

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
