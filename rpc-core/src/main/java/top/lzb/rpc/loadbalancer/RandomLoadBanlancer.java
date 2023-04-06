package top.lzb.rpc.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.Random;

public class RandomLoadBanlancer implements LoadBanlancer{
    @Override
    public Instance select(List<Instance> instanceList) {
        return instanceList.get(new Random().nextInt(instanceList.size()));
    }
}
