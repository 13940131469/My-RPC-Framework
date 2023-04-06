package top.lzb.rpc.registry;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lzb.rpc.enumeration.RpcError;
import top.lzb.rpc.exception.RpcException;
import top.lzb.rpc.loadbalancer.LoadBanlancer;
import top.lzb.rpc.loadbalancer.RandomLoadBanlancer;
import top.lzb.rpc.util.NacosUtil;

import java.net.InetSocketAddress;
import java.util.List;

public class NacosServiceDiscovery implements ServiceDiscovery{

    private static final Logger logger = LoggerFactory.getLogger(NacosServiceRegistry.class);
    private final  LoadBanlancer loadBanlancer;
    public NacosServiceDiscovery(){
        loadBanlancer = new RandomLoadBanlancer();
    }
    public NacosServiceDiscovery(LoadBanlancer loadBanlancer){
        this.loadBanlancer = loadBanlancer;
    }
    @Override
    public InetSocketAddress lookupService(String serviceName) {
        try {
            List<Instance> instances = NacosUtil.getAllInstance(serviceName);
            Instance instance = loadBanlancer.select(instances);
            return new InetSocketAddress(instance.getIp(), instance.getPort());
        } catch (NacosException e) {
            logger.error("获取服务时有错误发生:", e);
        }
        return null;
    }
}
