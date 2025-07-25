package com.his.heal;

import com.his.heal.dto.DbDto;
import com.his.heal.dto.MongoDbTestDto;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class MongodbTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoad(){
        var dbDto = new MongoDbTestDto();
        dbDto.setId(new ObjectId());
        dbDto.setAge(18);
        dbDto.setName("<UNK>");
        //mongoTemplate.insert(dbDto);
        var entity = mongoTemplate.save(dbDto);
        System.out.println(entity);
    }

    @Test
    void findAll(){
        var all = mongoTemplate.findAll(MongoDbTestDto.class);
        System.out.println(all);
    }

}
