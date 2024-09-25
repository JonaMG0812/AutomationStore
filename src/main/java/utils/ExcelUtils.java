package main.java.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelUtils {
    private static final String excelFilePath = "src/main/resources/accounts.xlsx";

    public static void createExcelFile() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Accounts");
        
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("First Name");
        headerRow.createCell(1).setCellValue("Last Name");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Password");
        headerRow.createCell(4).setCellValue("Order Number");

        FileOutputStream fos = new FileOutputStream(excelFilePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    public static void writeAccountDataToExcel(List<Map<String, String>> accounts) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Accounts");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("First Name");
        headerRow.createCell(1).setCellValue("Last Name");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("Password");
        headerRow.createCell(4).setCellValue("Order Number");

        // Escribir datos
        int rowIndex = 1;
        for (Map<String, String> account : accounts) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(account.get("firstName"));
            row.createCell(1).setCellValue(account.get("lastName"));
            row.createCell(2).setCellValue(account.get("email"));
            row.createCell(3).setCellValue(account.get("password"));
            row.createCell(4).setCellValue(account.getOrDefault("orderNumber", ""));
        }

        FileOutputStream fos = new FileOutputStream(excelFilePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    public static List<Map<String, String>> readAccountDataFromExcel() throws Exception {
        List<Map<String, String>> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> account = new HashMap<>();
            account.put("firstName", row.getCell(0).getStringCellValue());
            account.put("lastName", row.getCell(1).getStringCellValue());
            account.put("email", row.getCell(2).getStringCellValue());
            account.put("password", row.getCell(3).getStringCellValue());
            account.put("orderNumber", row.getCell(4).getStringCellValue());
            data.add(account);
        }

        workbook.close();
        fis.close();
        return data;
    }
}
