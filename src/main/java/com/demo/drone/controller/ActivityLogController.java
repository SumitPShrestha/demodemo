package com.demo.drone.controller;

import com.demo.drone.model.ActivityLog;
import com.demo.drone.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityLogController {
    @Autowired
    ActivityLogRepository activityLogRepository;

    @RequestMapping("/activity-logs")
    public List<ActivityLog> getActivityLog() {
        return activityLogRepository.findAll();
    }


}
