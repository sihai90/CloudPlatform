package club.byteyun.auth.service;

import club.byteyun.auth.manager.UserManager;
import club.byteyun.common.entity.ByteYunAuthUser;
import club.byteyun.common.entity.system.SystemUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @ClassName ByteYunUserDetailService
 * @Description //TODO 校验用户名和密码服务
 * @Date 12:04 2020/4/29
 * @Author lql
 **/
@Service
public class ByteYunUserDetailService implements UserDetailsService
{
    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SystemUser systemUser = userManager.findByName(username);
        if (systemUser != null)
        {
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
            {
                notLocked = true;
            }
            ByteYunAuthUser authUser = new ByteYunAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true, notLocked, AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
            BeanUtils.copyProperties(systemUser, authUser);
            return authUser;
        }
        else
        {
            throw new UsernameNotFoundException("");
        }
    }
}
