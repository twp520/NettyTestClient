package com.colin.nettytestclient;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class EachClientHandler extends SimpleChannelInboundHandler<String> {


    //链接到服务器
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接到服务器");
    }

    //收到服务器消息
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String byteBuf) throws Exception {
        System.out.println("收到服务器发送回来的消息 channelRead0：" + byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("收到服务器发送回来的消息 channelRead: " + msg);
    }

    //发生异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }

}
