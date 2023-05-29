package com.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.CLOSE;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class HuntedPazzlServerHandler extends ChannelInboundHandlerAdapter {
    private static final String welcomeToGame = "Welcome to Haunted Puzzle \nConnected ...";

    /**
     * Called when a message is received on the channel.
     *
     * @param ctx the channel handler context
     * @param msg the received message
     * @throws Exception if an error occurs during channel read
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;

            // Check if the request is a keep-alive request
            boolean keepAlive = HttpUtil.isKeepAlive(request);

            // Create a response with the welcome message
            FullHttpResponse response = new DefaultFullHttpResponse(
                    request.protocolVersion(), OK,
                    Unpooled.wrappedBuffer(welcomeToGame.getBytes()));
            response.headers()
                    .set(CONTENT_TYPE, TEXT_PLAIN)
                    .setInt(CONTENT_LENGTH, response.content().readableBytes());
            if (keepAlive) {
                if (!request.protocolVersion().isKeepAliveDefault()) {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                }
            } else {
                // Tell the client we're going to close the connection.
                response.headers().set(CONNECTION, CLOSE);
            }

            // Write the response to the context
            ChannelFuture f = ctx.write(response);
            if (!keepAlive) {
                f.addListener(ChannelFutureListener.CLOSE);
            }

            // Start the game via server ONLY through client request (Client java file).
            if (request.headers().get(HttpHeaderNames.USER_AGENT).equals("Java/" + System.getProperty("java.version"))) {
                com.example.server.game.Haunted_puzzle.stratGame(request.headers().get(HttpHeaderNames.CONTENT_BASE));
            }
        }

        // Release the message (ByteBuf)
        if (msg instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) msg;
            byteBuf.release();
        }
    }

    /**
     * Called when an exception is raised during the channel processing.
     *
     * @param ctx   the channel handler context
     * @param cause the exception that was raised
     * @throws Exception if an error occurs during exception handling
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}