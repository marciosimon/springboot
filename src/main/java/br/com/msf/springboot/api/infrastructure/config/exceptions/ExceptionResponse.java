package br.com.msf.springboot.api.infrastructure.config.exceptions;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, String message, String details) {
}