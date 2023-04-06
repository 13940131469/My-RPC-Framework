package top.lzb.test;

import top.lzb.rpc.api.HelloService;
import top.lzb.rpc.registry.DefaultServiceProvider;
import top.lzb.rpc.registry.NacosServiceRegistry;
import top.lzb.rpc.server.netty.NettyServer;
import top.lzb.rpc.server.RpcServer;

public class TestNettyServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        DefaultServiceProvider registry = new DefaultServiceProvider();
        registry.addServiceProvider(helloService);
        RpcServer rpcServer = new NettyServer("127.0.0.1",90);
        rpcServer.publishService(helloService,HelloService.class);
    }

}
