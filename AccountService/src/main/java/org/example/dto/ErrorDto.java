package org.example.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ErrorDto {
    private List<String> messages;

    private HttpStatus status;

    private int error;

    private Timestamp timestamp;

    private String path;
}