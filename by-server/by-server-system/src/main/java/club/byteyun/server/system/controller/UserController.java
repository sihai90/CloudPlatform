package club.byteyun.server.system.controller;

import club.byteyun.common.entity.ByteYunResponse;
import club.byteyun.common.entity.QueryRequest;
import club.byteyun.common.entity.system.SystemUser;
import club.byteyun.common.exception.ByteYunException;
import club.byteyun.common.utils.ByteYunUtils;
import club.byteyun.server.system.service.IUserService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController
{

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')")
    public ByteYunResponse userList(QueryRequest queryRequest, SystemUser user)
    {
        Map<String, Object> dataTable = ByteYunUtils.getDataTable(userService.findUserDetail(user, queryRequest));
        return new ByteYunResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public void addUser(@RequestBody @Valid SystemUser user) throws ByteYunException
    {
        try
        {
            this.userService.createUser(user);
        }
        catch (Exception e)
        {
            String message = "新增用户失败";
            log.error(message, e);
            throw new ByteYunException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public void updateUser( @RequestBody @Valid SystemUser user) throws ByteYunException
    {
        try
        {
            this.userService.updateUser(user);
        }
        catch (Exception e)
        {
            String message = "修改用户失败";
            log.error(message, e);
            throw new ByteYunException(message);
        }
    }

    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws ByteYunException
    {
        try
        {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
        }
        catch (Exception e)
        {
            String message = "删除用户失败";
            log.error(message, e);
            throw new ByteYunException(message);
        }
    }
}