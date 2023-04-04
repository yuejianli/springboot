package top.yueshushu.learn.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.annotation.ApiVersion;

@RestController
@ApiVersion(value = 1)
@RequestMapping("/api/{version}/user")
@Slf4j
public class User1Controller {

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
      // 执行删除的业务处理
      return "V1 版本删除用户";
    }

    @GetMapping("/get/{id}")
    public String getById(@PathVariable("id") Integer id) {
        // 执行 查询的业务处理
        return "V1 版本 查询用户";
    }
}
