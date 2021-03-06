package kr.green.springtest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.springtest.dao.UserDao;
import kr.green.springtest.vo.UserVo;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userdao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserVo getUser(HttpServletRequest request) {
		
		return (UserVo)request.getSession().getAttribute("user");
	}

	@Override
	public UserVo isUesr(UserVo inputUser) {
		//	일반적으로 로그인 과정은 db에서 아이디와 일치하는 정보를 가져와서 입력받은 아이디와 가져온 정보 중 비밀번호를 비교하여 로그인을 결정한다.
		//	쿼리로 비밀번호를 비교하지 않는다.
		//	입력한 비밀번호는 실제 비밀번호이고 db에 저장된 비밀 번호는 암호화된 비밀번호이기 때문에 쿼리로 직접 비교할 수 없다.
		//	PW에 이상한 작업을 하면 로그인이 될 수 있기 때문에(블라인드 SQL인젝션)
		UserVo user = userdao.getUser(inputUser.getId());
		if(user != null && passwordEncoder.matches(inputUser.getPw(), user.getPw())) {
			return user;
		}else {
			return null;	
		}
	}

	@Override
	public boolean signUp(UserVo user) {
		if(user == null) return false;
		if(user.getId() == null) return false;
		if(user.getPw() == null || user.getPw().length() == 0) return false;
		if(user.getEmail() == null || user.getEmail().length() == 0) return false;
		if(user.getGender() == null) user.setGender("male");
		if(userdao.getUser(user.getId())!=null) return false;
		user.setPw(passwordEncoder.encode(user.getPw()));
		userdao.insertuser(user);
		return true;
	}

	@Override
	public UserVo getUser(String id) {
		return userdao.getUser(id);
	}
}
