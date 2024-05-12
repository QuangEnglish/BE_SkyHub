package com.company_management.common;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AbstractExporter {

    public final String EXCEL_FILE_NAME_DATE_FORMAT = "yyyyMMdd_HHmmss";
    public final String UTF_8 = "UTF-8";
    public void setResponseHeader(HttpServletResponse response, String contentType, String extension, String fileNamePrefix) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat(EXCEL_FILE_NAME_DATE_FORMAT);
        String timestamp = dateFormatter.format(new Date());
        String fileName = "";
        if (fileNamePrefix != null) {
            fileName = fileNamePrefix + timestamp + extension;
        } else {
            fileName = "testfile";
        }
        response.setContentType(contentType);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;
        response.setHeader(headerKey, headerValue);
        response.setHeader("filename", fileName);
        response.setCharacterEncoding(UTF_8);
    }
}

