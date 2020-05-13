package club.byteyun.server.system.service;

import club.byteyun.common.entity.system.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserRoleService extends IService<UserRole>
{

    void deleteUserRolesByRoleId(String[] roleIds);

    void deleteUserRolesByUserId(String[] userIds);
}