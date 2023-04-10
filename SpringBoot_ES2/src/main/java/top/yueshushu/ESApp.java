package top.yueshushu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-08-11
 */
@SpringBootApplication
@Slf4j
public class ESApp {
	public static void main(String[] args) {
		SpringApplication.run(ESApp.class, args);
		log.info("两个蝴蝶飞学习 ES");
	}
}
