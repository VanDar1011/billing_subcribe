package com.example.biling_system.service;

import com.example.biling_system.Repository.BillTransactionsRepository;
import com.example.biling_system.Repository.CustomerRepository;
import com.example.biling_system.Repository.TempScheduleRepository;
import com.example.biling_system.Repository.TransactionRepository;
import com.example.biling_system.dto.response.BillTransactionsResponse;
import com.example.biling_system.dto.response.PackageTypeResponse;
import com.example.biling_system.dto.response.SubcriberResponse;
import com.example.biling_system.dto.response.UsagePackageResponse;
import com.example.biling_system.exception.AppException;
import com.example.biling_system.exception.ErrorCode;
import com.example.biling_system.exception.TransactionNotCorrect;
import com.example.biling_system.mapper.BillTransactionsMapper;
import com.example.biling_system.model.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillTransactionsService {
    private static final Logger log = LogManager.getLogger(BillTransactionsService.class);
    BillTransactionsRepository billTransactionsRepository;
    BillTransactionsMapper billTransactionsMapper;
    private final TempScheduleService tempScheduleService;
    private final TransactionRepository transactionRepository;
    private final UsagePackageService usagePackageService;
    private final SubcriberService subcriberService;
    private final CustomerRepository customerRepository;
    private final TempScheduleRepository tempScheduleRepository;

    @Transactional
    public void createRecord() {
        List<TempSchedule> scheduleList = tempScheduleService.getAllTempScheduleWhenStatusNotDone();
        if(scheduleList.isEmpty()) {
           return;
        }
        for (TempSchedule tempSchedule : scheduleList) {
            try {
                Transaction transaction =
                        transactionRepository.findByTransactionCode(tempSchedule.getTransactionCode());
                if (transaction == null) {
                    throw new TransactionNotCorrect(tempSchedule.getTransactionCode());
                }
                Bill bill = transaction.getIdBill();
                if(bill == null) {
                    throw new TransactionNotCorrect(tempSchedule.getTransactionCode());
                }
                UsagePackageResponse usagePackageResponse =
                        usagePackageService.findUsagePackageById(bill.getIdUsagePackage());
                if (usagePackageResponse == null) {
                    throw new TransactionNotCorrect(tempSchedule.getTransactionCode());
                }
                PackageTypeResponse packageTypeResponse = usagePackageResponse.getIdPackageType();
                if (packageTypeResponse == null) {
                    throw new TransactionNotCorrect(tempSchedule.getTransactionCode());
                }

                SubcriberResponse subcriberResponse =
                        subcriberService.findSubcriberById(usagePackageResponse.getIdSubcriber());
                if (subcriberResponse == null) {
                    throw new TransactionNotCorrect(tempSchedule.getTransactionCode());
                }
                Customer customer = customerRepository.findById(subcriberResponse.getIdCustomer())
                        .orElseThrow(() -> new TransactionNotCorrect(tempSchedule.getTransactionCode()));

                BillTransactions billTransactions = new BillTransactions();
                billTransactions.setTransactionCode(tempSchedule.getTransactionCode());
                billTransactions.setCodeCus(customer.getCodeCus());
                billTransactions.setNameCustomer(customer.getName());
                billTransactions.setIdentifyCode(customer.getIdentifyCode());
                billTransactions.setGenderCustomer(customer.getGender());
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
                billTransactionsRepository.save(billTransactions);
                tempSchedule.setStatus((byte) 1);
                tempScheduleRepository.save(tempSchedule);
            } catch (TransactionNotCorrect e) {
                log.error("Transaction not correct", e);
                tempSchedule.setStatus((byte) 2);
            }
            // ket thuc log
        }
        // start

    }

    public List<BillTransactionsResponse> billTransactionResponseList(String packageName, String packageCode, Date transactionDate) {
        List<BillTransactions> billtrans = billTransactionsRepository.findBillTransactionsByCondition(packageName, packageCode, transactionDate);
        return billTransactionsMapper.toBillTransactions(billtrans);
    }

    public void createfileBillTransactions(List<BillTransactionsResponse> billTransactionsResponseList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("BillTransactions");
        Row header = sheet.createRow(0);
        String[] headers = {"ID", "MA_GD", "MA_KH", "CCCD", "TEN_KH", "GIOI_TINH", "DIA_CHI", "MA_TB", "SDT", "LOAI_TB",
                "MA_GOI_CUOC", "TEN_GOI_CUOC", "DUNG_LUONG", "NGAY_KICH_HOAT", "NGAY_HET_HAN", "SO_TIEN", "NGAY_TAO_GIAO_DICH"};

        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowIdx = 1;
        for (BillTransactionsResponse response : billTransactionsResponseList) {
            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(response.getId());
            row.createCell(1).setCellValue(response.getTransactionCode());
            row.createCell(2).setCellValue(response.getCodeCus());
            row.createCell(3).setCellValue(response.getIdentifyCode());
            row.createCell(4).setCellValue(response.getNameCustomer());
            row.createCell(5).setCellValue(response.getGenderCustomer());
            row.createCell(6).setCellValue(response.getAddress());
            row.createCell(7).setCellValue(response.getCodeNumber());
            row.createCell(8).setCellValue(response.getPhoneNumber());
            row.createCell(9).setCellValue(response.getPhoneNumberType());
            row.createCell(10).setCellValue(response.getPackageCode());
            row.createCell(11).setCellValue(response.getPackageName());
            row.createCell(12).setCellValue(response.getPackageCapacity());
            row.createCell(13).setCellValue(response.getStartDay().toString());
            row.createCell(14).setCellValue(response.getEndDay().toString());
            row.createCell(15).setCellValue(response.getTotalAmount());
            row.createCell(16).setCellValue(response.getTransactionDate().toString());
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        String filePath = "BillTransactions.xlsx";
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            System.out.println("File created successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
