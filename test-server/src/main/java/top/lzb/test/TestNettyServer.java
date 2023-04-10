package top.lzb.test;

import top.lzb.rpc.annotation.ServiceScan;
import top.lzb.rpc.server.netty.NettyServer;
import top.lzb.rpc.server.RpcServer;
/**
 * 测试用服务提供方（Netty服务端）
 * @author lzb
 */
@ServiceScan
public class TestNettyServer {
    public static void main(String[] args) {
        RpcServer rpcServer = new NettyServer("127.0.0.1",90);
        rpcServer.start();
    }

}
