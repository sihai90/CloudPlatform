package club.byteyun.server.system.service;

import club.byteyun.common.entity.QueryRequest;
import club.byteyun.common.entity.system.SystemUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserService extends IService<SystemUser>
{
    /**
     * 查找用户详细信息
     *
     * @param request request
     * @param user    用户对象，用于传递查询条件
     * @return IPage
     */
    IPage<SystemUser> findUserDetail(SystemUser user, QueryRequest request);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    void createUser(SystemUser user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    void updateUser(SystemUser user);

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    void deleteUsers(String[] ids);
}
