package com.bastawesy.core.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("foo");
        List<String> mockedList = Mockito.mock(ArrayList.class);
        mockedList.add("one");
        Mockito.verify(mockedList).add("one");
        assertEquals(1,mockedList.size());
    }

}
