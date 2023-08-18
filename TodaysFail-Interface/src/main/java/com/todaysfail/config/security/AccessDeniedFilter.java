package com.todaysfail.config.security;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todaysfail.common.dto.ErrorReason;
import com.todaysfail.common.dto.ErrorResponse;
import com.todaysfail.common.exception.BaseErrorCode;
import com.todaysfail.common.exception.GlobalErrorCode;
import com.todaysfail.common.exception.TodaysFailCodeException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class AccessDeniedFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return PatternMatchUtils.simpleMatch(SwaggerPatterns, servletPath);
    }

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
        } catch (AccessDeniedException e) {
            ErrorResponse access_denied =
                    new ErrorResponse(
                            GlobalErrorCode.ACCESS_TOKEN_NOT_EXIST.getErrorReason(),
                            request.getRequestURL().toString());
            responseToClient(response, access_denied);
        }
    }

    private ErrorResponse getErrorResponse(BaseErrorCode errorCode, String path) {
        ErrorReason errorReason = errorCode.getErrorReason();
        return new ErrorResponse(
                errorReason.getStatus(), errorReason.getCode(), errorReason.getReason(), path);
    }

    private void responseToClient(HttpServletResponse response, ErrorResponse errorResponse)
            throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorResponse.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
