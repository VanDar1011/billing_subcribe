package com.example.biling_system.schedule;

import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.service.BillTransactionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Component
@Log4j2
@RestController
@RequestMapping(path = "/billTransaction")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScanToCreateBillTransaction {
    private final BillTransactionsService billTransactionsService;

    //    @Scheduled(cron = "1 * * * * ?")
    public void scanEveryFiveMinutes() {
        billTransactionsService.createRecord();
    }

    @GetMapping
    public ApiResponse<String> scanBillTransaction() {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {

            billTransactionsService.createRecord();
            apiResponse.setMessage("Success create bill transaction");
            log.info(apiResponse.getMessage());
            return apiResponse;
        } catch (Exception e) {
            log.error(e);
            apiResponse.setMessage(e.getMessage());
            return apiResponse;
        }

    }
}
