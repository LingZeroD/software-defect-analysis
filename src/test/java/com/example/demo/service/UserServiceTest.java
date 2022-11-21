package com.example.demo.service;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void loginIn1() {
        assertEquals("aki",userService.LoginIn("aki","123").getUsername());
    }

    @Test
    public void loginIn2() {
        assertSame(userService.LoginIn("ak","123"),null);
    }

    @Test
    public void getInfo1() {
        assertEquals("aki",userService.getInfo("aki").getUsername());
    }

    @Test
    public void getInfo2() {
        assertSame(userService.getInfo("ak"),null);
    }

    @Test
    public void register1() {
        User user = new User();
        user.setUsername("aki");
        user.setPassword("123456");
        assertSame(userService.register(user),-1);
    }

    @Test
    public void register2() {
        User user = new User();
        user.setUsername("sxy");
        user.setPassword("123456");
        assertTrue(userService.register(user)>0);
    }

    @Test
    public void update() {
        User user = new User();
        user.setUsername("momo");
        user.setPassword("123456");
        user.setEmail("sss");
        assertTrue(userService.update(user)>0);
    }

}