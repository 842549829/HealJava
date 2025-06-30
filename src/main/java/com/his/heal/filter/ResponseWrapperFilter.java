package com.his.heal.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.his.heal.dto.ResultDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 统一响应格式
 */
@Component
public class ResponseWrapperFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        CustomHttpServletResponse wrappedResponse = new CustomHttpServletResponse(response);
        filterChain.doFilter(request, wrappedResponse);

        byte[] responseData = wrappedResponse.getOutputInBytes();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // 尝试解析为 Result 结构
            JsonNode jsonNode = mapper.readTree(responseData);

            if (jsonNode.isObject() && jsonNode.has("code") && jsonNode.has("message") && jsonNode.has("data")) {
                // 已经是 Result 格式，原样输出
                writeResponse(response, jsonNode, mapper);
            } else {
                // 不是 Result，封装为 Result<T>
                ResultDto<JsonNode> result = new ResultDto<>();
                result.setCode(200);
                result.setMessage("success");
                result.setData(jsonNode); // 可能是对象、数组、null

                writeResponse(response, result, mapper);
            }

        } catch (Exception e) {
            // 不是合法 JSON，可能是字符串或基础类型
            String rawContent = new String(responseData, StandardCharsets.UTF_8).trim();

            Object dataValue;

            if (rawContent.isEmpty()) {
                dataValue = null;
            } else if (rawContent.equalsIgnoreCase("true") || rawContent.equalsIgnoreCase("false")) {
                dataValue = Boolean.valueOf(rawContent);
            } else if (rawContent.matches("-?\\d+")) {
                dataValue = Integer.valueOf(rawContent);
            } else if (rawContent.matches("-?\\d+(\\.\\d+)?")) {
                dataValue = Double.valueOf(rawContent);
            } else {
                dataValue = rawContent;
            }

            ResultDto<Object> result = new ResultDto<>();
            result.setCode(200);
            result.setMessage("success");
            result.setData(dataValue);

            writeResponse(response, result, mapper);
        }
    }

    private <T> void writeResponse(HttpServletResponse response, T body, ObjectMapper mapper) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        byte[] bytes = mapper.writeValueAsBytes(body);
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
    }

    public class CustomHttpServletResponse extends HttpServletResponseWrapper {

        private ByteArrayOutputStream output;
        private PrintWriter writer;
        private ServletOutputStream stream;

        public CustomHttpServletResponse(HttpServletResponse response) {
            super(response);
            output = new ByteArrayOutputStream();
        }

        public byte[] getOutputInBytes() {
            return output.toByteArray();
        }

        @Override
        public ServletOutputStream getOutputStream() {
            if (writer != null) {
                throw new IllegalStateException("getWriter() has already been called on this response.");
            }
            if (stream == null) {
                stream = new ServletOutputStream() {
                    @Override
                    public boolean isReady() {
                        return true;
                    }

                    @Override
                    public void setWriteListener(WriteListener writeListener) {

                    }

                    @Override
                    public void write(int b) {
                        output.write(b);
                    }
                };
            }
            return stream;
        }

        @Override
        public PrintWriter getWriter() throws UnsupportedEncodingException {
            if (stream != null) {
                throw new IllegalStateException("getOutputStream() has already been called on this response.");
            }
            if (writer == null) {
                writer = new PrintWriter(new OutputStreamWriter(output, getCharacterEncoding()));
            }
            return writer;
        }
    }
}
