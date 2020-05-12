package club.byteyun.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @ClassName ByteYunAuthUser
 * @Description //TODO 授权领域对象
 * @Date 12:29 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ByteYunAuthUser implements Serializable
{
    private static final long serialVersionUID = -1748289340320186418L;

    private String username; // 密码

    private String password; //用户名

    private boolean accountNonExpired = true; //方法返回boolean类型，用于判断账户是否未过期，未过期返回true反之返回false

    private boolean accountNonLocked= true; //方法用于判断账户是否未锁定

    private boolean credentialsNonExpired= true; //用于判断用户凭证是否没过期，即密码是否未过期；

    private boolean enabled= true; //方法用于判断用户是否可用
}
