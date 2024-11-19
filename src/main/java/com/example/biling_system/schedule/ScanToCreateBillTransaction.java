package com.example.biling_system.schedule;

import org.springframework.scheduling.annotation.Scheduled;

public class ScanToCreateBillTransaction {
    @Scheduled(cron = "5 * * * * ?")
    public void scanEveryFiveMinutes() {

    }
}
