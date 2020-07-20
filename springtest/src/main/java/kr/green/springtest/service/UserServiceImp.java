package kr.green.springtest.service;

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
	public UserVo getUser(String id) {
		// TODO Auto-generated method stub
		return userdao.getUser(id);
	}

	@Override
	public UserVo isUesr(UserVo inputUser) {
		//	�Ϲ������� �α��� ������ db���� ���̵�� ��ġ�ϴ� ������ �����ͼ� �Է¹��� ���̵�� ������ ���� �� ��й�ȣ�� ���Ͽ� �α����� �����Ѵ�.
		//	������ ��й�ȣ�� ������ �ʴ´�.
		//	�Է��� ��й�ȣ�� ���� ��й�ȣ�̰� db�� ����� ��� ��ȣ�� ��ȣȭ�� ��й�ȣ�̱� ������ ������ ���� ���� �� ����.
		//	PW�� �̻��� �۾��� �ϸ� �α����� �� �� �ֱ� ������(����ε� SQL������)
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
}
