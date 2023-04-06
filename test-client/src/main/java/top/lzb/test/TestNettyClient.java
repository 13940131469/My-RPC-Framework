package top.lzb.test;

import top.lzb.rpc.api.HelloObject;
import top.lzb.rpc.api.HelloService;
import top.lzb.rpc.client.netty.NettyClient;
import top.lzb.rpc.client.RpcClientProxy;

public class TestNettyClient {
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        RpcClientProxy rpcClientProxy = new RpcClientProxy(nettyClient);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject ob = new HelloObject(13, "this is a netty");
        String hello = helloService.hello(ob);
        System.out.println(hello);
    }
}
