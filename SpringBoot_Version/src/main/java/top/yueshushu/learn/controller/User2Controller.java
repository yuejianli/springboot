package top.yueshushu.learn.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.annotation.ApiVersion;

@RestController
@ApiVersion(value = 2)
@RequestMapping("/api/{version}/user")
@Slf4j
public class User2Controller {

    @GetMapping("/get/{id}")
    public String getById(@PathVariable("id") Integer id) {
        // 执行 查询的业务处理
        return "V2 版本 查询用户";
    }

    @GetMapping("/change/{id}")
    public String changeById(@PathVariable("id") Integer id) {
        // 执行 改变的 业务处理
        return "V2 版本改变用户";
    }
}
