package com.his.heal.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "动力机器感应数据")
public record DbDto(
        @Schema(description = "主键Id")
        String id,
        @Schema(description = "水压(MPa)")
        String Pressure,
        @Schema(description = "气压(MPa)")
        String GasPressure,
        @Schema(description = "压缩空气(Mpa)")
        String CompressedAir) {
}
