package edu.gyc.mp1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gyc.mp1.dao.UserMapper;
import edu.gyc.mp1.model.User;
import edu.gyc.mp1.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService {
}
