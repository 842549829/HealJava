package com.his.heal;

import com.his.heal.services.IUserServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HealApplicationTests1 {

    @Autowired
    private IUserServices userServices;

    @Test
    public void contextLoads()
    {
        var d = userServices.getOne(null);
        System.out.println(d.getUserName());

    }
}
