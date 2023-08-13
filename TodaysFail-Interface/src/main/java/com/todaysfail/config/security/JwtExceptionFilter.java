package com.todaysfail.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todaysfail.common.dto.ErrorResponse;
import com.todaysfail.common.exception.BaseErrorCode;
import com.todaysfail.common.exception.TodaysFailCodeException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TodaysFailCodeException e) {
            responseToClient(
                    response,
                    getErrorResponse(e.getErrorCode(), request.getRequestURL().toString()));
        }
    }

    private ErrorResponse getErrorResponse(BaseErrorCode errorCode, String path) {

        return new ErrorResponse(errorCode.getErrorReason(), path);
    }

    private void responseToClient(HttpServletResponse response, ErrorResponse errorResponse)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorResponse.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
