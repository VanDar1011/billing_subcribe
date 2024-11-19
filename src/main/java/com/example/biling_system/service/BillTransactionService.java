package com.example.biling_system.service;

import com.example.biling_system.Repository.BillTransactionRepository;
import com.example.biling_system.Repository.CustomerRepository;
import com.example.biling_system.Repository.TempScheduleRepository;
import com.example.biling_system.Repository.TransactionRepository;
import com.example.biling_system.dto.response.PackageTypeResponse;
import com.example.biling_system.dto.response.SubcriberResponse;
import com.example.biling_system.dto.response.UsagePackageResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BillTransactionService {
    private final BillTransactionRepository billTransactionRepository;
    private final TempScheduleService tempScheduleService;
    private final TransactionRepository transactionRepository;
    private final UsagePackageService usagePackageService;
    private final SubcriberService subcriberService;
    private final CustomerRepository customerRepository;
    private final TempScheduleRepository tempScheduleRepository;

    @Transactional
    public void createRecord() {
        // start
        List<TempSchedule> scheduleList = tempScheduleService.getAllTempScheduleWhenStatusNotDone();
        for (TempSchedule tempSchedule : scheduleList) {
            Transaction transaction =
                    transactionRepository.findByTransactionCode(tempSchedule.getTransactionCode());
            Bill bill = transaction.getIdBill();
            UsagePackageResponse usagePackageResponse =
                    usagePackageService.findUsagePackageById(bill.getIdUsagePackage());
            PackageTypeResponse packageTypeResponse = usagePackageResponse.getIdPackageType();

            SubcriberResponse subcriberResponse =
                    subcriberService.findSubcriberById(usagePackageResponse.getIdSubcriber());

            Customer customer = customerRepository.findById(subcriberResponse.getIdCustomer())
                    .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));

            BillTransactions billTransactions = new BillTransactions();
            billTransactions.setTransactionCode(tempSchedule.getTransactionCode());
            billTransactions.setCodeCus(customer.getCodeCus());
            billTransactions.setNameCustomer(customer.getName());
            billTransactions.setIdentifyCode(customer.getIdentifyCode());
            billTransactions.setGender_customer(customer.getGender());
            billTransactions.setAddress(customer.getAddress());
            billTransactions.setCodeNumber(subcriberResponse.getCodeNumber());
            billTransactions.setPhoneNumber(subcriberResponse.getPhoneNumber());
            billTransactions.setPhoneNumberType(subcriberResponse.getPhoneNumberType());
            billTransactions.setPackageCode(packageTypeResponse.getPackageCode());
            billTransactions.setPackageName(packageTypeResponse.getPackageName());
            billTransactions.setPackageCapacity(packageTypeResponse.getPackageCapacity());
            billTransactions.setStartDay(usagePackageResponse.getStartDay());
            billTransactions.setEndDay(usagePackageResponse.getEndDay());
            billTransactions.setTotalAmount(bill.getTotalAmount());
            billTransactions.setTransactionDate(transaction.getTransactionDate());
            billTransactionRepository.save(billTransactions);
            tempSchedule.setStatus((byte)1);
            tempScheduleRepository.save(tempSchedule);
            // ket thuc log
        }
    }
}
