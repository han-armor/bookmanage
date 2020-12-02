package com.youo.bookmanage.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.youo.bookmanage.system.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;


    private MockMvc mvc;


    @BeforeEach
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }
    @Test
    void login() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/system/user/login/{name}/{password}","name1","newPsw")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void findUsersList() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/system/user//all/{current}/{size}",1,3)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void addUser() throws Exception{
        User user = new User( null,"pt","pt",1);
        String  json = JSONArray.toJSONString(user);
        mvc.perform(MockMvcRequestBuilders.post("/system/user/")
                .accept(MediaType.APPLICATION_JSON_UTF8)/*代表客户端希望接受的数据类型为application/json;charset=UTF-8*/
                .contentType(MediaType.APPLICATION_JSON_UTF8)/*代表发送端发送的数据格式是application/json;charset=UTF-8*/
                .content(json)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void delete() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/system/user/{name}","pt")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateUserPsw() throws Exception{
        mvc.perform(MockMvcRequestBuilders.put("/system/user/{name}/{psw}/{newpsw}","name1","name1","na1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void findPortUList()throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/system/user/all/{current}/{size}/{partid}",1,3,1)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
