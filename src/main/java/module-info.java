module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    requires io.netty.buffer;
    requires io.netty.handler;
    requires io.netty.codec;
    requires io.netty.common;
    requires io.netty.transport;
   

    opens com.example to javafx.fxml;
    exports com.example;

}
