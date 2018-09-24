package com.junwan.demo.serviceImpl;

import com.junwan.demo.entity.User;
import com.junwan.demo.enums.ResultEnums;
import com.junwan.demo.exception.UserException;
import com.junwan.demo.repository.UserRepository;
import com.junwan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        String name = user.getName();
        if(StringUtils.isEmpty(name)) {
            //校验用户名不能为空
            throw new UserException(ResultEnums.USER_NAME_NOT_NULL);
        }
        return userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        Optional op = userRepository.findById(id);
        if (op.isPresent()){
            return (User) op.get();
        } else {
            throw new UserException(ResultEnums.USER_NOT_FIND);
        }
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
