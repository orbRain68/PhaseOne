package com.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.util.CharsetUtil;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HuntedPazzlServerTest {

    @Test
    public void testRoundTrip() {
        // given
        String request = "heartbeat";

        // when
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new OkResponder());
        embeddedChannel.writeInbound(request);

        // then
        ByteBuf outboundMessage = embeddedChannel.readOutbound();
        assertEquals("ok", outboundMessage.toString(CharsetUtil.UTF_8));
    }

    public static class OkResponder extends ChannelInboundHandlerAdapter {
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("ok", CharsetUtil.UTF_8))
                    .addListener(ChannelFutureListener.CLOSE);
        }
    }
}
