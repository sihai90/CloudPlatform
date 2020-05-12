package club.byteyun.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * @version 1.0
 * @ClassName ByteYunAuthUser
 * @Description //TODO 授权领域对象
 * @Date 12:29 2020/4/29
 * @Author lql
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ByteYunAuthUser extends User
{
    private static final long serialVersionUID = -1748289340320186418L;

    private Long userId;

    private String avatar;

    private String email;

    private String mobile;

    private String sex;

    private Long deptId;

    private String deptName;

    private String roleId;

    private String roleName;

    private Date lastLoginTime;

    private String description;

    private String status;

    public ByteYunAuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, authorities);
    }

    public ByteYunAuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
