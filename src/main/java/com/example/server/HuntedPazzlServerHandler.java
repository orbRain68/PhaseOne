package com.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
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
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Called when a new channel is created and registered to its EventLoop.
        // You can do initialization work here.
        super.channelActive(ctx);


    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // Called when the last message read from the current context has been read.
        // Here we just flush the context to write any pending messages to the network.
        ctx.flush();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Called when a message is received on the channel.
        // In this case, we assume the message is an HTTP request.

        // Convert the message to a String and then to a ByteBuf for processing.
        String obj = msg.toString();
        byte[] bye = obj.getBytes();
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bye);

        try {
            // Read and process the data
            String data = byteBuf.toString(io.netty.util.CharsetUtil.US_ASCII);
            // Handle the data
            // TODO: Implement your game logic here

            if (msg instanceof HttpRequest) {
                // Cast the message to an HttpRequest
                HttpRequest req = (HttpRequest) msg;

                // Check if the request is a keep-alive request
                boolean keepAlive = HttpUtil.isKeepAlive(req);

                // Create a response with the welcome message
                FullHttpResponse response = new DefaultFullHttpResponse(
                        req.protocolVersion(), OK,
                        Unpooled.wrappedBuffer((byte[]) welcomeToGame.getBytes()));
                response.headers()
                        .set(CONTENT_TYPE, TEXT_PLAIN)
                        .setInt(CONTENT_LENGTH, response.content().readableBytes());
                if (keepAlive) {
                    if (!req.protocolVersion().isKeepAliveDefault()) {
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
                if (req.headers().get(HttpHeaderNames.USER_AGENT).equals("Java/"+System.getProperty("java.version"))) {
                    com.example.server.game.Haunted_puzzle.stratGame(req.headers().get(HttpHeaderNames.CONTENT_BASE));
                }
        
            }
        } finally {
            // Release the ByteBuf
            byteBuf.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Called when an exception is raised during the channel processing.
        // Here we just print the stack trace and close the context.
        cause.printStackTrace();
        ctx.close();
    }
}