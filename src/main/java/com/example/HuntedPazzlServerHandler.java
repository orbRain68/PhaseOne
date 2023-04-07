package com.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandlerAdapter;

public class HuntedPazzlServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String obj =msg.toString();
        byte[] bye = obj.getBytes();
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bye);

        try {
            // Read and process the data
String data = byteBuf.toString(io.netty.util.CharsetUtil.UTF_8);
            // Handle the data
            // TODO: Implement your game logic here
            System.out.println(data);
        } finally {
            byteBuf.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}