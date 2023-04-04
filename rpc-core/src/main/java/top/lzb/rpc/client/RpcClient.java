package top.lzb.rpc.client;

import top.lzb.rpc.entity.RpcRequest;

public interface RpcClient {
    Object sendRequest(RpcRequest request);
}
