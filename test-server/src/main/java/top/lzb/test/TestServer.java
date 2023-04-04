package top.lzb.test;

import top.lzb.rpc.api.HelloService;
import top.lzb.rpc.registry.DefaultServiceRegistry;
import top.lzb.rpc.server.RpcServer;

/**
 * 测试用服务提供方（服务端）
 * @author ziyang
 */
public class TestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        DefaultServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        RpcServer rpcServer = new RpcServer(registry);
        rpcServer.start(9000);
    }

}
