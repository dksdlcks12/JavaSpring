package kr.green.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.springtest.dao.UserDao;
import kr.green.springtest.vo.UserVo;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userdao;

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
		if(user == null)
			return null;
		if(user.getPw().equals(inputUser.getPw())) {
			return user;
		}
		return null;
	}
}
