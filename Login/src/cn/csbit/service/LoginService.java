package cn.csbit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.csbit.core.hibernate.BaseDAO;
import cn.csbit.core.jdbc.JdbcTemplate;
import cn.csbit.model.User;

@Service
public class LoginService {
	@Autowired
	private BaseDAO rootDAO;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	// 登录验证
	@Transactional()
	public User checkExist(String name, String password) {
		User user = this.rootDAO.getById(User.class, name);
		if (user == null) {
			throw new RuntimeException("用户不存在");
		} else {
//			if (cn.csbit.core.util.Helpers.md5Encode(password).equals(
			if (password.equals(
					user.getPassword())) {
				user.setErrorCount((short) 0);
				// user.getUserRole().getRights();
				return user;
			}else{
				throw new RuntimeException("密码错误");
			}
		}

	}
}
