package com.demo.drone.logging.aspect;

import com.demo.drone.dto.ActivityLogDTO;
import com.demo.drone.model.ActivityLog;
import com.demo.drone.repository.ActivityLogRepository;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Component
@Aspect
@Log4j2
public class ActivityLogAspect {
    @Autowired
    ActivityLogRepository activityLogRepository;

    @Pointcut("execution(* *..registerDrone(String))")
    public void logRegisterDrone() {
    }

    @AfterReturning("logRegisterDrone()")
    public void afterDroneRegistered(JoinPoint thisJoinPoint) {
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        activityLogDTO.setActivity(String.format("drone with SN %s registered", (String) thisJoinPoint.getArgs()[0]));
        ActivityLog activityLog = new ActivityLog();
        activityLog.setActivity(activityLogDTO.toString());
         activityLog.setActivityTime(Timestamp.valueOf(LocalDateTime.now()));
        log.info(activityLogDTO.toString());
        activityLogRepository.save(activityLog);
    }

}
