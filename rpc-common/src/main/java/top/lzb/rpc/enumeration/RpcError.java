package top.lzb.rpc.enumeration;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RPC调用过程中的错误
 * @author lzb
 */
@AllArgsConstructor
@Getter
public enum RpcError {

    SERVICE_INVOCATION_FAILURE("服务调用出现失败"),
    SERVICE_NOT_FOUND("找不到对应的服务"),
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE("注册的服务未实现接口"),
    UNKNOWN_PROTOCOL("未发现的协议类型"),
    UNKNOWN_PACKAGE_TYPE("未识别的包类型"),
    UNKNOWN_SERIALIZER("不识别的序列化"),
    RESPONSE_NOT_MATCH("响应号与请求号不匹配"),
    FAILED_TO_CONNECT_TO_SERVICE_REGISTRY("连接注册中心出错"),
    REGISTER_SERVICE_FAILED("注册服务时出错"),
    SERVICE_SCAN_PACKAGE_NOT_FOUND("未发现开启服务扫描的注解"),
    UNKNOWN_ERROR("未知错误");
    private final String message;

}