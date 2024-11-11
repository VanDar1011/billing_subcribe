package com.example.biling_system.schedule;

import com.example.biling_system.Repository.BillRepository;
import com.example.biling_system.Repository.UsagePackageRepository;
import com.example.biling_system.dto.response.UsagePackageResponse;
import com.example.biling_system.model.UsagePackage;
import com.example.biling_system.service.UsagePackageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/crontab")
//@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateInVoiceByFirstOfMonth {
    private final BillRepository billRepository;
    private final UsagePackageRepository usagePackageRepository;
    private final UsagePackageService usagePackageService;
    private static final Logger log = LoggerFactory.getLogger(CreateInVoiceByFirstOfMonth.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");


    //    @Scheduled(fixedRate = 1000)
//    @Scheduled(cron = "0 0 0 1 * ?")
//    @Scheduled(cron = "1 * * * * ?")
    @GetMapping
    public void createInVoiceByFirstOfMonth() {
        Instant instant = Instant.now();
        log.info("The time is now {}", dateFormat.format(instant.toEpochMilli()));
        // lấy tất cả bản ghi của bản sử dụng cước
        Date now = new Date(instant.toEpochMilli());
        log.info("Time current " + now);
        List<UsagePackageResponse> list = usagePackageService.getUsagePackagesWithinTimeRange(now);
        log.info("Number of record " + list.size());
//        List<UsagePackage> usagePackages = usagePackageRepository.findTimeBetweenStartAndEnd(now);
//        System.out.println(usagePackages.size());
//        usagePackages.stream().forEach(usagePackage -> {
//            System.out.println(usagePackage.getId());
//        });

        // check xem thằng nào còn đang nợ cước thì tạo hóa đơn
    }
}
