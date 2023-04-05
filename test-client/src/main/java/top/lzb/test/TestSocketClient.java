package top.lzb.test;

import top.lzb.rpc.api.HelloObject;
import top.lzb.rpc.api.HelloService;
import top.lzb.rpc.client.RpcClientProxy;
import top.lzb.rpc.client.socket.SocketClient;

/**
 * 测试用消费者（客户端）
 * @author lzb
 */
public class TestSocketClient {

    public static void main(String[] args) {
        SocketClient socketClient = new SocketClient("127.0.0.1", 9000);
        RpcClientProxy proxy = new RpcClientProxy(socketClient);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }

}
