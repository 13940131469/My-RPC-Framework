package top.lzb.rpc.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

public interface LoadBanlancer {
    Instance select(List<Instance> instanceList);
}
