package top.lzb.rpc.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lzb.rpc.client.RpcClient;
import top.lzb.rpc.common.CommonDecoder;
import top.lzb.rpc.common.CommonEncoder;
import top.lzb.rpc.common.JSONSerializer;
import top.lzb.rpc.common.KYROSerializer;
import top.lzb.rpc.entity.RpcRequest;
import top.lzb.rpc.entity.RpcResponse;
import top.lzb.rpc.loadbalancer.PollingLoadBanlancer;
import top.lzb.rpc.registry.NacosServiceDiscovery;
import top.lzb.rpc.registry.NacosServiceRegistry;
import top.lzb.rpc.registry.ServiceDiscovery;
import top.lzb.rpc.registry.ServiceRegistry;

import java.net.InetSocketAddress;

public class NettyClient implements RpcClient {

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private static final Bootstrap bootstrap;

    private final ServiceRegistry serviceRegistry;

    private final ServiceDiscovery serviceDiscovery;

    public NettyClient() {
        this.serviceRegistry = new NacosServiceRegistry();
        this.serviceDiscovery = new NacosServiceDiscovery(new PollingLoadBanlancer());
    }

    static {
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new CommonDecoder())
                                .addLast(new CommonEncoder(new KYROSerializer()))
                                .addLast(new NettyClientHandler());
                    }
                });
    }

    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        try {
            InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcRequest.getInterfaceName());
            String host = inetSocketAddress.getHostString();
            int port = inetSocketAddress.getPort();
            ChannelFuture future = bootstrap.connect(inetSocketAddress.getHostString(), port).sync();
            logger.info("客户端连接到服务器 {}:{}", host, port);
            Channel channel = future.channel();
            if(channel != null) {
                channel.writeAndFlush(rpcRequest).addListener(future1 -> {
                    if(future1.isSuccess()) {
                        logger.info(String.format("客户端发送消息: %s", rpcRequest.toString()));
                    } else {
                        logger.error("发送消息时有错误发生: ", future1.cause());
                    }
                });
                channel.closeFuture().sync();
                AttributeKey<RpcResponse> key = AttributeKey.valueOf("rpcResponse");
                RpcResponse rpcResponse = channel.attr(key).get();
                return rpcResponse.getData();
            }
        } catch (InterruptedException e) {
            logger.error("发送消息时有错误发生: ", e);
        }
        return null;
    }
}
