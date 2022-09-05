package top.yueshushu.learn;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.log4j.Log4j2;
import top.yueshushu.learn.mapper.UserMapper;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserMoreService;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-09-05
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class MoreTests {
	@Resource
	private UserMoreService userMoreService;
	@Resource
	private UserMapper userMapper;
	@Test
	public void updateTest(){
		User user = new User();
		user.setId(1);
		user.setName("修改");
		
		userMoreService.updateById(user);
		
		User idUser = userMoreService.getById(1);
		
		log.info(">>>{}",idUser);
	}
	
	
	@Test
	public void update2Test(){
		
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		
		updateWrapper.eq("id",2);
		
		User user = new User();
		user.setId(2);
		user.setName("修改");
	
		userMapper.update(user,updateWrapper);
		
		
		User idUser = userMoreService.getById(2);
		
		log.info(">>>{}",idUser);
	}
	/**
	 进行查询， 使用 lambdaQuery 进行查询
	 */
	@Test
	public void findConditionTest(){
		String name = "";
		List<User> list = userMoreService.lambdaQuery()
				//....
				// .eq(User::getId, 1)
				.like(StringUtils.isNotBlank(name),User::getName,name)
				.list();
		
		list.forEach(
				n-> log.info(n)
		);
	}
	
	/**
	 只查询部分信息
	 .select()  只查询某些字段
	 */
	@Test
	public void selectFieldsTest(){
		String name = "";
		List<User> list = userMoreService.lambdaQuery()
				.select(User::getId,User::getName)
				//....
				.like(StringUtils.isNotBlank(name),User::getName,name)
				.list();
		
		list.forEach(n-> log.info(n));
	}
	
	/**
	删除，  set 没有什么用。
	 */
	@Test
	public void removeTest(){
		userMoreService.lambdaUpdate()
				.set(User::getName, "修改了")
				.eq(User::getId, 1)
				.remove();
	}
	
	@Test
	public void update3Test(){
		LambdaUpdateWrapper<User> queryWrapper = new LambdaUpdateWrapper<>();
		queryWrapper.set(User::getName,"修改了")
				.eq(User::getId,1);
		userMoreService.update(queryWrapper);
	}
}
