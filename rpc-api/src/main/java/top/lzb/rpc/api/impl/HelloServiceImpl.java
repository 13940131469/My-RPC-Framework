package top.lzb.rpc.api.impl;

import top.lzb.rpc.annotation.Service;
import top.lzb.rpc.api.HelloObject;
import top.lzb.rpc.api.HelloService;
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(HelloObject object) {
        return "收到了信息："+ object.getMessage();
    }
}
