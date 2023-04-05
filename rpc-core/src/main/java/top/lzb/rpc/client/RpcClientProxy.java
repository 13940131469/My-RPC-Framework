package top.lzb.rpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lzb.rpc.client.socket.SocketClient;
import top.lzb.rpc.entity.RpcRequest;
import top.lzb.rpc.entity.RpcResponse;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * RPC客户端动态代理
 * @author lzb
 */
public class RpcClientProxy implements InvocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);

    private RpcClient rpcClient;

    public RpcClientProxy(RpcClient client) {
       this.rpcClient = client;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("调用方法: {}#{}", method.getDeclaringClass().getName(), method.getName());
        RpcRequest rpcRequest = new RpcRequest(UUID.randomUUID().toString(),method.getDeclaringClass().getName(),
                method.getName(), args, method.getParameterTypes());
        return rpcClient.sendRequest(rpcRequest);
    }
}
