package top.yueshushu.learn.controller;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import top.yueshushu.learn.model.UserRo;
import top.yueshushu.learn.model.UserVo;

import java.util.Collections;
import java.util.List;
@RestController
@Api(tags = {"用户接口"})
@Slf4j
public class UserController {

    @ApiOperation(value = "创建用户", notes = "根据UserRo 对象创建用户")
    @PostMapping("/add")
    public void save (@RequestBody  UserRo userRo) {
        log.info("添加用户 {}", userRo);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id 删除用户")
    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "id", value = "用户id",required = true, dataType = "Integer", paramType = "query", defaultValue = "1"
            )
    )
    @PostMapping("/delete/{id}")
    public void delete ( @PathVariable("id") Integer id) {
        log.info("删除用户, {}", id);
    }


    @ApiOperation(value = "查询用户", notes = "查询全部用户")
    @ApiResponses(
            @ApiResponse(
                    code = 200,
                    message = "查询成功",
                    response = UserVo.class
            )
    )
    @GetMapping("/findAll")
    public List<UserVo> findAll() {
        UserVo userVo = new UserVo();
        userVo.setName("岳泽霖");
        userVo.setAge(28);
        userVo.setSex("男");
        return Collections.singletonList(userVo);
    }

    @ApiOperation(value = "查询用户", notes = "查询全部用户")
    @ApiResponses(
            @ApiResponse(
                    code = 200,
                    message = "查询成功",
                    response = UserVo.class
            )
    )
    // 忽略接口
    @ApiIgnore
    @GetMapping("/findAllOld")
    public List<UserVo> findAllOld() {
        UserVo userVo = new UserVo();
        userVo.setName("岳泽霖");
        userVo.setAge(28);
        userVo.setSex("男");
        return Collections.singletonList(userVo);
    }
}
