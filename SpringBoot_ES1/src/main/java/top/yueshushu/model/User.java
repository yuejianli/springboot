package top.yueshushu.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-08-11
 */
@Data
public class User implements Serializable {
	private Integer id;
	private String name;
	private String nickName;
	private Integer age;
	private String sex;
	private String description;
}
