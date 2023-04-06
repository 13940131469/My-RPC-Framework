package top.lzb.rpc.registry;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lzb.rpc.enumeration.RpcError;
import top.lzb.rpc.exception.RpcException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultServiceProvider implements ServiceProvider {

    private static final Logger logger = LoggerFactory.getLogger(DefaultServiceProvider.class);

    private static final Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    private static final Set<String> registeredService = ConcurrentHashMap.newKeySet();

    @Override
    public synchronized <T> void addServiceProvider(T service) {
        String serviceName = service.getClass().getCanonicalName();
        if(registeredService.contains(serviceName)) return;
        registeredService.add(serviceName);
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if(interfaces.length == 0) {
            throw new RpcException(RpcError.SERVICE_NOT_IMPLEMENT_ANY_INTERFACE);
        }
        for(Class<?> i : interfaces) {
            serviceMap.put(i.getCanonicalName(), service);
        }
        logger.info("向接口: {} 注册服务: {}", interfaces, serviceName);
    }

    @Override
    public synchronized Object getServiceProvider(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if(service == null) {
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return service;
    }
}
