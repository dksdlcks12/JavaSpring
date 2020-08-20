package kr.ajs.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ajs.portfolio.dao.AdminDao;

@Service
public class AdminServiceImp implements AdminService {
	@Autowired
	AdminDao adminDao;
}
