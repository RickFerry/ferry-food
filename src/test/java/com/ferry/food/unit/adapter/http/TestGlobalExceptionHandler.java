package com.ferry.food.unit.adapter.http;

import com.ferry.food.adapter.input.http.advice.GlobalExceptionHandler;
import com.ferry.food.domain.exceptions.BusinessException;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GlobalExceptionHandler Tests")
class TestGlobalExceptionHandler {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    @DisplayName("should handle EntityNotFoundException with 404 status")
    void testHandleEntityNotFoundException_DeveRetornar404() {
        // Arrange
        EntityNotFoundException ex = EntityNotFoundException.forEntity("Cozinha", 1L);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/cozinhas/1");
        ServletWebRequest webRequest = new ServletWebRequest(request);

        // Act
        ResponseEntity<GlobalExceptionHandler.ErrorResponse> response = 
            exceptionHandler.handleEntityNotFound(ex, webRequest);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(404);
        assertThat(response.getBody().getError()).isEqualTo("Not Found");
    }

    @Test
    @DisplayName("should handle BusinessException with 400 status")
    void testHandleBusinessException_DeveRetornar400() {
        // Arrange
        BusinessException ex = new BusinessException("Nome do restaurante é obrigatório");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/restaurantes");
        ServletWebRequest webRequest = new ServletWebRequest(request);

        // Act
        ResponseEntity<GlobalExceptionHandler.ErrorResponse> response = 
            exceptionHandler.handleBusinessException(ex, webRequest);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(400);
        assertThat(response.getBody().getError()).isEqualTo("Business Error");
        assertThat(response.getBody().getMessage()).contains("obrigatório");
    }

    @Test
    @DisplayName("should include error message in response")
    void testHandleException_DeveConterMensagem() {
        // Arrange
        BusinessException ex = new BusinessException("Erro de teste");
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletWebRequest webRequest = new ServletWebRequest(request);

        // Act
        ResponseEntity<GlobalExceptionHandler.ErrorResponse> response = 
            exceptionHandler.handleBusinessException(ex, webRequest);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("Erro de teste");
    }

    @Test
    @DisplayName("should include timestamp in error response")
    void testHandleException_DeveConterTimestamp() {
        // Arrange
        BusinessException ex = new BusinessException("Erro");
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletWebRequest webRequest = new ServletWebRequest(request);

        // Act
        ResponseEntity<GlobalExceptionHandler.ErrorResponse> response = 
            exceptionHandler.handleBusinessException(ex, webRequest);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }
}
