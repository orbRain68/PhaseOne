module com.example.server.game {
    requires javafx.controls;
    requires javafx.fxml;

    requires io.netty.buffer;
    requires io.netty.handler;
    requires io.netty.codec;
    requires io.netty.common;
    requires io.netty.transport;
    requires io.netty.codec.http;
    requires io.netty.codec.http2;
   
    requires java.desktop; 
    

    exports com.example.client;

}
