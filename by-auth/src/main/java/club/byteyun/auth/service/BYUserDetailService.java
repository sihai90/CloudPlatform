package club.byteyun.auth.service;

import club.byteyun.common.entity.BYAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName BYUserDetailService
 * @Description //TODO 校验用户名和密码服务
 * @Date 12:04 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@Service
public class BYUserDetailService implements UserDetailsService
{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        BYAuthUser authUser = new BYAuthUser();
        authUser.setUsername(username);
        authUser.setPassword(this.passwordEncoder.encode("123456"));

        return new User(username,authUser.getPassword(),authUser.isEnabled()
                                                        ,authUser.isAccountNonExpired()
                                                        ,authUser.isCredentialsNonExpired()
                                                        ,authUser.isAccountNonLocked()
                                                        , AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }
}
