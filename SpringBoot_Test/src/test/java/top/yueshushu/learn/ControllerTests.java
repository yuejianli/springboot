package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import top.yueshushu.learn.controller.RedisController;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:RedisDBTests
 * @Description Redis测试使用
 * @Author yjl
 * @Date 2021/5/18 17:50
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class ControllerTests {

    // 必须要注入 关联组件，UserService， 否则会出现问题。
    @MockBean
    private UserService userService;

    @Resource
    private MockMvc mockMvc;

    @Test
    public void findAllTest() throws Exception{
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/getAll")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.info("打印信息: {}", result.getResponse());
    }
}
