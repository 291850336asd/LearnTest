package com.meng.example.monitor.scheduler;

import java.util.Map;

public interface ScheduledService {
    public void startJob(Map<String, Object> context, int interalInSeconds);
    public void shutdown();
}
