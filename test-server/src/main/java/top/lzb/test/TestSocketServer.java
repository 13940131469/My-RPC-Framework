package top.lzb.test;

import top.lzb.rpc.api.HelloService;
import top.lzb.rpc.registry.DefaultServiceProvider;
import top.lzb.rpc.server.RpcServer;
import top.lzb.rpc.server.socket.SocketServer;

/**
 * 测试用服务提供方（服务端）
 * @author ziyang
 */
public class TestSocketServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        DefaultServiceProvider registry = new DefaultServiceProvider();
        registry.addServiceProvider(helloService);
        RpcServer rpcServer = new SocketServer("127.0.0.1",9000);
        rpcServer.publishService(helloService,HelloService.class);
    }

}
