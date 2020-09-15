package kr.ajs.portfolio.scheduler;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kr.ajs.portfolio.dao.SchedulerDao;
import kr.ajs.portfolio.vo.UserVo;
@Service
public class PointScheduler {
	@Autowired
	SchedulerDao schedulerDao;
	@Scheduled(cron="0 0 0 * * * ")
    public void TestScheduler(){
        ArrayList<UserVo> userList;
        userList = schedulerDao.getUpdateUser();
        for(UserVo user:userList) {
        	schedulerDao.userPointUpDate(user);
        }
    }
}
