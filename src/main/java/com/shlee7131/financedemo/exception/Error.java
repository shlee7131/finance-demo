package com.shlee7131.financedemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
public class Error {
    private String message;
    private Integer statusCode;
    private LocalDate created_at;
}
