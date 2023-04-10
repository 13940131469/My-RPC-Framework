package top.lzb.test;

import top.lzb.rpc.annotation.ServiceScan;
import top.lzb.rpc.server.RpcServer;
import top.lzb.rpc.server.socket.SocketServer;

/**
 * 测试用服务提供方（Socket服务端）
 * @author lzb
 */
@ServiceScan
public class TestSocketServer {

    public static void main(String[] args) {
        RpcServer rpcServer = new SocketServer("127.0.0.1",9000);
        rpcServer.start();
    }

}
