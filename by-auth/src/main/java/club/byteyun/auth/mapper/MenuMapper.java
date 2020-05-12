package club.byteyun.auth.mapper;

import club.byteyun.common.entity.system.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu>
{

    List<Menu> findUserPermissions(String username);
}
