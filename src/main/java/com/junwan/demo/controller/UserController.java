package com.junwan.demo.controller;

import com.junwan.demo.entity.Result;
import com.junwan.demo.entity.User;
import com.junwan.demo.service.UserService;
import com.junwan.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     * @param user Valid注解可使在entity中定义的校验生效
     * @param bindingResult  校验返回的结果
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Result createUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            logger.error(msg);
            return ResultUtil.fail(-1,msg);
        }
        User res =userService.createUser(user);
        return ResultUtil.success(res);
    }

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @RequestMapping(value = "find", method = RequestMethod.POST)
    public Result find(Integer id) {
        if (id == null) {
            return ResultUtil.fail(null,"id不能为空");
        }
        User user = userService.findById(id);
        return ResultUtil.success(user);
    }

    /**
     * 查询全部用户
     * @return
     */
    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public Result findAll() {
        List<User> userList = userService.findAll();
        return ResultUtil.success(userList);
    }

    /**
     * 删除用户
     * @param user
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public void del(User user) {
        userService.deleteById(user.getId());
    }

    /**
     * 根据姓名查询用户
     * @param user
     * @return
     */
    @RequestMapping(value = "findByName", method = RequestMethod.POST)
    public List<User> findByName(User user) {
        return userService.findByName(user.getName());
    }
}
