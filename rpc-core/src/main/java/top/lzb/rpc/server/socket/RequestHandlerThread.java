package top.lzb.rpc.server.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lzb.rpc.entity.RpcRequest;
import top.lzb.rpc.entity.RpcResponse;
import top.lzb.rpc.registry.ServiceProvider;
import top.lzb.rpc.registry.ServiceRegistry;
import top.lzb.rpc.server.RequestHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandlerThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);

    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceProvider serviceRegistry;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceProvider serviceRegistry) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getServiceProvider(interfaceName);
            Object result = requestHandler.handle(rpcRequest, service);
            objectOutputStream.writeObject(RpcResponse.success(result,rpcRequest.getRequestId()));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用或发送时有错误发生：", e);
        }
    }
}
