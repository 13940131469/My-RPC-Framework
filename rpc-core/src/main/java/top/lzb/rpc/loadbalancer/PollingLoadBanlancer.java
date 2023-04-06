package top.lzb.rpc.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

public class PollingLoadBanlancer implements LoadBanlancer{
    int index = 0;
    @Override
    public Instance select(List<Instance> instanceList) {
        index %= instanceList.size();
        return instanceList.get(index++);
    }
}
