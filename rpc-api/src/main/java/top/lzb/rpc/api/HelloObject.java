package top.lzb.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ziyang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor//生成一个全参数的构造方法
public class HelloObject implements Serializable {

    private Integer id;
    private String message;

}
