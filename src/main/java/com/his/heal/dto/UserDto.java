package com.his.heal.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户数据传输对象")
public record UserDto(
        @Schema(description = "用户的唯一标识", example = "1")
        int id,

        @Schema(description = "用户姓名", example = "张三")
        String name
) {}