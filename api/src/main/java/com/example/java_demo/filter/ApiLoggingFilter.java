package com.example.java_demo.filter;

import com.fasterxml.jackson.databind.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;
import org.springframework.web.util.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

@Component
public class ApiLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(ApiLoggingFilter.class);

    private static final int MAX_BODY_LENGTH = 5000;
    private static final String TRACE_ID = "traceId";
    private static final String HEADER_NAME = "X-Trace-Id";

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        String path = request.getRequestURI();

        return path.startsWith("/auth/login")
                || path.startsWith("/auth/refresh")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/actuator");
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        String traceId = UUID.randomUUID().toString();
        MDC.put(TRACE_ID, traceId);
        response.setHeader(HEADER_NAME, traceId);

        long start = System.currentTimeMillis();

        ContentCachingRequestWrapper req =
                new ContentCachingRequestWrapper(request, 1024 * 1024);

        ContentCachingResponseWrapper resp =
                new ContentCachingResponseWrapper(response);

        try {

            filterChain.doFilter(req, resp);

        } finally {

            long duration = System.currentTimeMillis() - start;

            try {

                logRequestResponse(req, resp, duration);

            } catch (Exception ex) {

                log.error("Error while logging api", ex);

            }

            resp.copyBodyToResponse();
        }
    }

    private void logRequestResponse(
            ContentCachingRequestWrapper request,
            ContentCachingResponseWrapper response,
            long duration) {

        String requestBody = getRequestBody(request);
        String responseBody = getResponseBody(response);

        Map<String, String> headers = getHeaders(request);

        log.info("""
                ==================== API LOG ====================
                URL            : {}
                Method         : {}
                Query          : {}
                Status         : {}
                Duration(ms)   : {}
                Client IP      : {}

                Headers:
                {}

                Request:
                {}

                Response:
                {}

                =================================================
                """,
                request.getRequestURI(),
                request.getMethod(),
                request.getQueryString(),
                response.getStatus(),
                duration,
                getClientIp(request),
                pretty(headers),
                requestBody,
                responseBody
        );
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {

        byte[] bytes = request.getContentAsByteArray();

        if (bytes.length == 0) {
            return "";
        }

        String body = new String(bytes, StandardCharsets.UTF_8);

        body = maskJson(body);

        return truncate(prettyJson(body));
    }

    private String getResponseBody(ContentCachingResponseWrapper response) {

        byte[] bytes = response.getContentAsByteArray();

        if (bytes.length == 0) {
            return "";
        }

        String body = new String(bytes, StandardCharsets.UTF_8);

        body = maskJson(body);

        return truncate(prettyJson(body));
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {

        Map<String, String> headers = new LinkedHashMap<>();

        Enumeration<String> names = request.getHeaderNames();

        while (names.hasMoreElements()) {

            String name = names.nextElement();

            String value = request.getHeader(name);

            if ("authorization".equalsIgnoreCase(name)
                    || "cookie".equalsIgnoreCase(name)) {

                value = "*********";
            }

            headers.put(name, value);
        }

        return headers;
    }

    private String getClientIp(HttpServletRequest request) {

        String forwarded = request.getHeader("X-Forwarded-For");

        if (forwarded != null && !forwarded.isBlank()) {

            return forwarded.split(",")[0].trim();
        }

        return request.getRemoteAddr();
    }

    private String pretty(Object object) {

        try {

            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);

        } catch (Exception ex) {

            return String.valueOf(object);
        }
    }

    private String prettyJson(String text) {

        if (text == null || text.isBlank()) {
            return text;
        }

        try {

            JsonNode node = mapper.readTree(text);

            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(node);

        } catch (Exception ex) {

            return text;
        }
    }

    private String truncate(String text) {

        if (text == null) {
            return null;
        }

        if (text.length() <= MAX_BODY_LENGTH) {
            return text;
        }

        return text.substring(0, MAX_BODY_LENGTH)
                + "...(truncated)";
    }

    private String maskJson(String text) {

        try {

            JsonNode node = mapper.readTree(text);

            maskNode(node);

            return mapper.writeValueAsString(node);

        } catch (Exception ex) {

            return text;
        }
    }

    private void maskNode(JsonNode node) {

        if (node.isObject()) {

            Iterator<String> fields = node.fieldNames();

            while (fields.hasNext()) {

                String field = fields.next();

                JsonNode child = node.get(field);

                if (isSensitiveField(field)) {

                    ((com.fasterxml.jackson.databind.node.ObjectNode) node)
                            .put(field, "***MASKED***");

                } else {

                    maskNode(child);
                }
            }
        }

        if (node.isArray()) {

            for (JsonNode child : node) {

                maskNode(child);
            }
        }
    }

    private boolean isSensitiveField(String field) {

        String name = field.toLowerCase();

        return name.contains("password")
                || name.contains("token")
                || name.contains("secret")
                || name.contains("access_token")
                || name.contains("refresh_token");
    }
}
