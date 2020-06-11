package com.rw.netty.service.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

public class MyNettyBIOServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new OioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.channel(OioServerSocketChannel.class);
        bootstrap.group(group);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
                        ctx.writeAndFlush(byteBuf).addListener(ChannelFutureListener.CLOSE);
                    }
                });
            }
        });
        ChannelFuture future = bootstrap.bind(9093).sync();
        future.channel().closeFuture().sync();
        group.shutdownGracefully().sync();
    }
}
