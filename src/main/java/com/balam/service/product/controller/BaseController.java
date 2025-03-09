package com.balam.service.product.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {
    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    private final DataSource dataSource;

    public BaseController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/heartbeat")
    public Map<String, String> heartbeat() {
        Map<String, String> response = new HashMap<>();
        response.put("appName", appName);
        response.put("version", appVersion);
        response.put("currentTime", LocalDateTime.now().toString());
        return response;
    }

    @GetMapping("/health-check")
    public Map<String, Map<String, String>> healthCheck() {
        Map<String, Map<String, String>> response = new HashMap<>();
        response.put("database", checkDatabaseConnection());
        return response;
    }

    private Map<String, String> checkDatabaseConnection() {
        Map<String, String> details = new HashMap<>();
        details.put("provided", dataSourceUrl);
        String status = "failure";
        try (Connection connection = dataSource.getConnection()) {
            status = connection.isValid(2) ? "success" : "failure";
            DatabaseMetaData metaData = connection.getMetaData();
            details.put("connected", metaData.getURL());
            details.put("type", metaData.getDatabaseProductName());
            details.put("version", metaData.getDatabaseProductVersion());
        } catch (SQLException e) {
            details.put("error",  e.getMessage());
        } finally {
            details.put("status",  status);
        }
        return details;
    }
}
