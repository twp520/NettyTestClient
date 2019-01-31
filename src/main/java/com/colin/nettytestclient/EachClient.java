package com.colin.nettytestclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class EachClient {

    private String host;
    private int port;

    public EachClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        Bootstrap bs = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bs.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast("stringEncoder", new StringEncoder())
                                .addLast("stringDecoder", new StringDecoder())
                                .addLast("myHandler", new EachClientHandler());
                    }
                });
        ChannelFuture future = bs.connect().sync();
        future.channel().writeAndFlush("hello server. i am colin");
        future.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
