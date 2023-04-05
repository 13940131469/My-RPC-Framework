package top.lzb.rpc.client;

import top.lzb.rpc.entity.RpcRequest;
import top.lzb.rpc.entity.RpcResponse;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * RPC客户端动态代理
 * @author lzb
 */
public class RpcClientProxy implements InvocationHandler {

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
        RpcRequest rpcRequest = new RpcRequest(method.getDeclaringClass().getName(),
                method.getName(), args, method.getParameterTypes());
        return rpcClient.sendRequest(rpcRequest);
    }
}
