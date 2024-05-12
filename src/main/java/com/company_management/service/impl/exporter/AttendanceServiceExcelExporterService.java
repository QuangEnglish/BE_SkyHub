package com.company_management.service.impl.exporter;

import com.company_management.common.AbstractExporter;
import com.company_management.model.response.AttendanceExportExcelResponse;
import com.company_management.utils.Constants;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;


@Service
public class AttendanceServiceExcelExporterService extends AbstractExporter {
    private XSSFSheet sheet;

    private static final Integer STAFF_INFO_START_COLUMN_INDEX = 5;
    private static final Integer FONT_SIZE = 11;

    public void writeDataLines(XSSFWorkbook workbook, XSSFSheet sheet,
                               List<AttendanceExportExcelResponse> attendanceExportExcelResponses) {
        int rowIndex = 5;
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        XSSFFont font = workbook.createFont();
        font.setFontHeight(FONT_SIZE);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        for (AttendanceExportExcelResponse item : attendanceExportExcelResponses){
            XSSFRow row = sheet.createRow(rowIndex);
            String stringIndex = String.valueOf(item.getIndex());
            createCell(row, 0, stringIndex, cellStyle);
            createCell(row, 1, item.getEmployeeCode(), cellStyle);
            createCell(row, 2, item.getEmployeeName(), cellStyle);
            createCell(row, 3, "Vào", cellStyle);
            int columnIndex = 4;
            for(String itemCheckIn: item.getCheckIn().getCheckIn()){
                createCell(row, columnIndex, itemCheckIn, cellStyle);
                columnIndex++;
            }
            createCell(row, 35, String.valueOf(item.getWorkingTime()), cellStyle);
            createCell(row, 36, String.valueOf(item.getWorkingPoint()), cellStyle);
            createCell(row, 37, String.valueOf(item.getTotalPenalty()), cellStyle);
            createCell(row, 38, String.valueOf(item.getTotalTimeOt()), cellStyle);
            createCell(row, 39, String.valueOf(item.getTotalTimeLeave()), cellStyle);
            rowIndex++;
            XSSFRow rowTwo = sheet.createRow(rowIndex);
            createCell(rowTwo, 3, "Ra", cellStyle);
            int columIndexOut = 4;
            for(String itemCheckOut: item.getCheckOut().getCheckOut()){
                createCell(rowTwo, columIndexOut, itemCheckOut, cellStyle);
                columIndexOut++;
            }
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex, 1, 1));
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex, 2, 2));
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex, 35, 35));
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex, 36, 36));
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex, 37, 37));
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex, 38, 38));
            sheet.addMergedRegion(new CellRangeAddress(rowIndex-1, rowIndex, 39, 39));
            rowIndex++;
        }

        rowIndex++;
        XSSFRow rowThree = sheet.createRow(rowIndex);
        createCell(rowThree, 3, "Ghi chú");
        rowIndex++;
        XSSFRow rowFour = sheet.createRow(rowIndex);
        createCell(rowFour, 3, "V: Vắng(Không chấm công)");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 3, 5));
        rowIndex++;
        XSSFRow rowThur = sheet.createRow(rowIndex);
        createCell(rowThur, 3, "off: Nghỉ thứ 7, chủ nhật");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 3, 5));

        setWidthOfColumns(sheet);
    }

    public void setWidthOfColumns(XSSFSheet sheet) {
        sheet.setColumnWidth(0, 10 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(1, 20 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(2, 20 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(3, 15 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(4, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(5, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(6, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(7, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(8, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(9, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(10, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(11, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(12, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(13, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(14, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(15, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(16, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(17, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(18, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(19, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(20, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(21, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(22, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(23, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(24, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(25, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(26, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(27, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(28, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(29, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(30, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(31, 12 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(32, 20 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(33, 20 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(34, 20 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(35, 20 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(36, 20 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(37, 20 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(38, 20 * Constants.CHARACTER_WIDTH);
        sheet.setColumnWidth(39, 20 * Constants.CHARACTER_WIDTH);
    }


    public void writeHeaderLines(XSSFWorkbook workbook, XSSFSheet sheet,
                                 List<AttendanceExportExcelResponse> attendanceExportExcelResponses,
                                 int daysInMonth,
                                 int month,
                                 int year) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        XSSFFont font2 = workbook.createFont();
        font2.setFontHeight(20);
        font2.setColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFont(font2);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setWrapText(true);
        cellStyle2.setBorderTop(BorderStyle.THIN);
        cellStyle2.setBorderBottom(BorderStyle.THIN);
        cellStyle2.setBorderLeft(BorderStyle.THIN);
        cellStyle2.setBorderRight(BorderStyle.THIN);
        XSSFFont font = workbook.createFont();
        font.setFontHeight(FONT_SIZE);
        cellStyle2.setFont(font);
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle2.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle cellStyle4 = workbook.createCellStyle();
        cellStyle4.setWrapText(true);
        cellStyle4.setBorderTop(BorderStyle.THIN);
        cellStyle4.setBorderBottom(BorderStyle.THIN);
        cellStyle4.setBorderLeft(BorderStyle.THIN);
        cellStyle4.setBorderRight(BorderStyle.THIN);
        font.setFontHeight(9);
        font.setColor(IndexedColors.BLACK.getIndex());
        cellStyle4.setFont(font);
        cellStyle4.setAlignment(HorizontalAlignment.LEFT);
        cellStyle4.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle4.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle4.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        XSSFRow row1 = sheet.createRow(0);
        XSSFRow row2 = sheet.createRow(1);
        XSSFRow row3 = sheet.createRow(2);
        XSSFRow row4 = sheet.createRow(3);
        XSSFRow row5 = sheet.createRow(4);
        XSSFRow row6 = sheet.createRow(5);

        row1.setHeightInPoints(Constants.HEADER_HEIGHT);
        row4.setHeightInPoints(Constants.HEADER_HEIGHT);
        createCell(row1, 0, "BẢNG THỐNG KÊ CHẤM CÔNG", cellStyle);
        createCell(row2, 0, "Tháng");
        createCell(row2, 1, String.valueOf(month), cellStyle);
        createCell(row3, 0, "Năm");
        createCell(row3, 1, String.valueOf(year), cellStyle);
        createCell(row4, 0, "STT", cellStyle2);
        createCell(row4, 1, "Mã nhân viên", cellStyle2);
        createCell(row4, 2, "Tên nhân viên", cellStyle2);
        createCell(row4, 3, "", cellStyle2);
        createCell(row4, 35, "Tổng số giờ", cellStyle2);
        createCell(row4, 36, "Hệ số công", cellStyle2);
        createCell(row4, 37, "Tổng phút phạt", cellStyle2);
        createCell(row4, 38, "Số giờ tăng ca", cellStyle2);
        createCell(row4, 39, "Số ngày nghỉ phép", cellStyle2);

        for (int i = 1; i <= daysInMonth; i++) {
            createCell(row4, i+3,"N"+i, cellStyle2);
        }
        createCell(row5, 0, "HRM HỆ THỐNG VĂN PHÒNG ĐIỆN TỬ NODO JSC", cellStyle4);
        row5.setHeightInPoints(20.0f);
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 39));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
    }
}
