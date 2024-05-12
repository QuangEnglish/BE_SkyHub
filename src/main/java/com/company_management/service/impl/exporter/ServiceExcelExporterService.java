package com.company_management.service.impl.exporter;

import com.company_management.common.AbstractExporter;
import com.company_management.model.response.AttendanceExportExcelResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceExcelExporterService extends AbstractExporter {
    private DateFormat dateFormatter = new SimpleDateFormat("ddMMYYYY_hhmmss");
    public final String EXCEL_EXTENSION = ".xlsx";
    public final String EXCEL_FILE_NAME_PREFIX = "dich_vu";

    private final AttendanceServiceExcelExporterService attendanceServiceExcelExporterService;

    public void export(int daysInMonth, List<AttendanceExportExcelResponse> attendanceExportExcelResponses,
                       HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        super.setResponseHeader(response, "application/octet-stream", EXCEL_EXTENSION, EXCEL_FILE_NAME_PREFIX);

        if (!attendanceExportExcelResponses.isEmpty()) {
            XSSFSheet attendanceServiceSheet = workbook.createSheet("Bảng thống kê chấm công");
            attendanceServiceExcelExporterService.writeHeaderLines(workbook, attendanceServiceSheet, attendanceExportExcelResponses, daysInMonth);
            attendanceServiceExcelExporterService.writeDataLines(workbook, attendanceServiceSheet, attendanceExportExcelResponses);
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
