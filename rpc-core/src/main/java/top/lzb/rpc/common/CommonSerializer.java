package top.lzb.rpc.common;

public interface CommonSerializer {

    byte[] serialize(Object obj);

    Object deserialize(byte[] bytes, Class<?> clazz);

    int getCode();

    static CommonSerializer getByCode(int code) {
        switch (code) {
            case 0:
                return new KYROSerializer();
            case 1:
                return new JSONSerializer();
            default:
                return null;
        }
    }
}

