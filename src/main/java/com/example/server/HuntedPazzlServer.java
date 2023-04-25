package com.example.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;

public class HuntedPazzlServer {
    private int port;

    public HuntedPazzlServer(int port) {
        this.port = port;
    }

    /**
     * @throws Exception
     */
    public void run() throws Exception {
        // create new event loop group for handling incoming connections
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // create new event loop group for handling actual I/O operations
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // create server bootstrap instance and configure it
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // add HTTP server codec and continue handler to the pipeline
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new HttpServerExpectContinueHandler());
                            // add custom server handler to the pipeline
                            ch.pipeline().addLast("handler",new HuntedPazzlServerHandler());
                        }
                    });

            // bind server to the specified port and start listening for incoming connections
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // wait until server channel is closed
            channelFuture.channel().closeFuture().sync();
        } finally {
            // gracefully shutdown both event loop groups
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // check if command line arguments are provided
        if (args.length > 0) { // condition for args if mistakes occurs
            // print usage message and exit
            System.err.println("Usage: java " + HuntedPazzlServer.class.getSimpleName() + " <port>");
            return;
        }

        // set default port number
        int port = Integer.parseInt("8080");
        // create new instance of the server and start it
        new HuntedPazzlServer(port).run();
    }
}
