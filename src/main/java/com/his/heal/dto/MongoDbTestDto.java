package com.his.heal.dto;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "spring")
public class MongoDbTestDto {
    @Field("_id")
    private ObjectId id;
    @Field("names")
    private String name;
    @Field("ages")
    private int age;
}
