package club.byteyun.auth.mapper;

import club.byteyun.common.entity.system.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<SystemUser>
{
    SystemUser findByName(String username);
}