package com.example.biling_system.controller;


import com.example.biling_system.dto.response.ApiResponse;
import com.example.biling_system.dto.response.BillTransactionsResponse;
import com.example.biling_system.service.BillTransactionsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.ByteArrayOutputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/billtransactions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillTransactionsController {
    BillTransactionsService billTransactionsService;

    @GetMapping("/find")
    public ApiResponse<List<BillTransactionsResponse>> findBillTransactions(
            @RequestParam(required = false) String packageName,
            @RequestParam(required = false) String packageCode,
            @RequestParam(required = false) Date transactionDate) {

        ApiResponse<List<BillTransactionsResponse>> response = new ApiResponse<>();

        List<BillTransactionsResponse> transactions = billTransactionsService.billTransactionResponseList(
                packageName, packageCode, transactionDate);

        response.setData(transactions);

        return response;
    }


//    @GetMapping
//    public ApiResponse<String> createBillTransaction(@RequestBody
//                                                     List<BillTransactionsResponse> billTransactionsResponse) {
//        ApiResponse<String> response = new ApiResponse<>();
//        billTransactionsService.createfileBillTransactions(billTransactionsResponse);
//        return response;
//    }

    @GetMapping("/export")
    public ResponseEntity<?> exportToExcel() {
        List<BillTransactionsResponse> billTransactionsResponseList =
                billTransactionsService.getAllBillTransactions();

        if (billTransactionsResponseList == null || billTransactionsResponseList.isEmpty()) {
            return new ResponseEntity<>("Không có dữ liệu để xuất!", HttpStatus.NO_CONTENT);
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("BillTransactions");
            Row header = sheet.createRow(0);
            String[] headers = {"STT", "ID", "MÃ GIAO DỊCH", "MÃ KHÁCH HÀNG", "CCCD",
                    "TÊN KHÁCH HÀNG", "GIỚI TÍNH", "ĐỊA CHỈ", "MÃ THUÊ BAO",
                    "SDT", "LOẠI THUÊ BAO", "MÃ GÓI CƯỚC", "TÊN GÓI CƯỚC",
                    "DUNG LƯỢNG", "NGÀY KÍCH HOẠT", "NGÀY HẾT HẠN",
                    "SỐ TIỀN", "NGÀY TẠO GIAO DỊCH"};

            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

            int rowIdx = 1;
            for (int i = 0; i < billTransactionsResponseList.size(); i++) {
                BillTransactionsResponse response = billTransactionsResponseList.get(i);
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(response.getId());
                row.createCell(2).setCellValue(response.getTransactionCode());
                row.createCell(3).setCellValue(response.getCodeCus());
                row.createCell(4).setCellValue(response.getIdentifyCode());
                row.createCell(5).setCellValue(response.getNameCustomer());
                row.createCell(6).setCellValue(response.getGenderCustomer());
                row.createCell(7).setCellValue(response.getAddress());
                row.createCell(8).setCellValue(response.getCodeNumber());
                row.createCell(9).setCellValue(response.getPhoneNumber());
                row.createCell(10).setCellValue(response.getPhoneNumberType());
                row.createCell(11).setCellValue(response.getPackageCode());
                row.createCell(12).setCellValue(response.getPackageName());
                row.createCell(13).setCellValue(response.getPackageCapacity());

                Cell startDayCell = row.createCell(14);
                startDayCell.setCellValue(response.getStartDay());
                startDayCell.setCellStyle(dateCellStyle);

                Cell endDayCell = row.createCell(15);
                endDayCell.setCellValue(response.getEndDay());
                endDayCell.setCellStyle(dateCellStyle);

                row.createCell(16).setCellValue(response.getTotalAmount());

                Cell transactionDateCell = row.createCell(17);
                transactionDateCell.setCellValue(response.getTransactionDate());
                transactionDateCell.setCellStyle(dateCellStyle);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                byte[] excelBytes = outputStream.toByteArray();
                HttpHeaders headersResponse = new HttpHeaders();
                headersResponse.add("Content-Disposition", "attachment; filename=BillTransactions.xlsx");
                headersResponse.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                return new ResponseEntity<>(excelBytes, headersResponse, HttpStatus.OK);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Lỗi khi tạo file Excel!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
