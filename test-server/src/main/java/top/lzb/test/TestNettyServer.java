package top.lzb.test;

import top.lzb.rpc.api.HelloService;
import top.lzb.rpc.registry.DefaultServiceRegistry;
import top.lzb.rpc.server.netty.NettyServer;
import top.lzb.rpc.server.RpcServer;

public class TestNettyServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        DefaultServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        RpcServer rpcServer = new NettyServer();
        rpcServer.start(90);
    }

}
