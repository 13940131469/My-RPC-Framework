package top.lzb.rpc.registry;

public interface ServiceProvider {

    <T> void addServiceProvider(T service);

    Object getServiceProvider(String serviceName);
}
