package com.petrobest.pbmsapp.system.test;

import com.petrobest.pbmsapp.PbmsappApplication;
import com.petrobest.pbmsapp.system.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PbmsappApplication.class})
public class SysTest {

    @Autowired
    private ResourceService service;

    @Test
    public void test() {
        ArrayList<String> ids = new ArrayList<>();
        ids.add("1135353462807687170");
        service.batchRemoveChild(ids);
    }
}
