package top.yueshushu.learn.util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
@Slf4j
@Component(value = "redisUtil")
public class RedisUtil {
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/** -------------------1.key相关操作--------------------- */
	/******1.1 删除操作开始******/
	/**
	 * 功能描述: 通过key删除key
	 */
	public void delByKey(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 功能描述: 删除某个key是否成功，如果删除失败,则返回false.
	 *
	 * @param key redis的键值
	 * @return 返回是否删除成功
	 */
	public boolean remove(String key) {
		return Boolean.TRUE.equals(redisTemplate.delete(key));
	}

	/**
	 * 功能描述: （谨慎使用） 以key为 前缀 模糊删除所有相关缓存数据 key+*
	 *
	 * @param key 删除redis键值对的 前缀匹配
	 */
	public void deleteByPrefix(String key) {
		Set<String> keys = redisTemplate.keys(key + "*");
		if (!!CollectionUtils.isEmpty(keys)) {
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 功能描述:（谨慎使用）以key为 后缀 模糊删除所有相关缓存数据 *+key
	 *
	 * @param key 删除redis键值对的 后缀
	 */
	public void deleteBySuffix(String key) {
		Set<String> keys = redisTemplate.keys("*" + key);
		if (!CollectionUtils.isEmpty(keys)) {
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 功能描述: 批量删除多个key
	 *
	 * @param keys 删除多个key,集合的形式
	 */
	public void delete(Collection<String> keys) {
		redisTemplate.delete(keys);
	}

	public Long unlink(List<String> keys){
		return redisTemplate.unlink(keys);
	}

	public Boolean unlink(String key){
		return redisTemplate.unlink(key);
	}

	/**
	 * 功能描述: （谨慎使用） 以key为 前缀 模糊删除所有相关缓存数据 key+*
	 * @param key 删除redis键值对的 前缀匹配
	 */
	public void unlinkByPrefix(String key) {
		Set<String> keys = redisTemplate.keys(key + "*");
		if (!!CollectionUtils.isEmpty(keys)) {
			redisTemplate.unlink(keys);
		}
	}
	/**
	 * 功能描述:（谨慎使用）以key为 后缀 模糊删除所有相关缓存数据 *+key
	 *
	 * @param key 删除redis键值对的 后缀
	 */
	public void unlinkBySuffix(String key) {
		Set<String> keys = redisTemplate.keys("*" + key);
		if (!CollectionUtils.isEmpty(keys)) {
			redisTemplate.unlink(keys);
		}
	}


	/******1.1 删除操作结束******/
	/******1.2 设置值操作开始******/
	/**
	 * 功能描述: 通过key键存储值，可以是字符串，也可以是Java Bean对象。
	 *
	 * @param key   redis的键值
	 * @param value 存储的值信息
	 * @return 返回是否存储成功
	 */
	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 功能描述: 通过key键存储值,有失效时间，可以是字符串，也可以是Java Bean对象。
	 *
	 * @param key           redis的键值
	 * @param value         存储的值信息
	 * @param expireSeconds 失效的时间，单位是秒
	 * @return 返回是否存储成功
	 */
	public boolean set(String key, Object value, long expireSeconds) {
		try {
			if (expireSeconds > 0) {
				redisTemplate.opsForValue().set(key, value, expireSeconds, TimeUnit.SECONDS);
			} else {
				redisTemplate.opsForValue().set(key, value);
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 功能描述: 通过key键存储值,有失效时间，可以是字符串，也可以是Java Bean对象。
	 *
	 * @param key      redis的键值
	 * @param value    存储的值信息
	 * @param time     失效的时间
	 * @param timeUnit 失效的时间单位，有秒，分钟，小时，天。
	 * @return 返回是否存储成功
	 */
	public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, timeUnit);
			} else {
				redisTemplate.opsForValue().set(key, value);
			}
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/**
	 * 功能描述: 通过key键存储值,指定失效日期，可以是字符串，也可以是Java Bean对象。
	 * @param key redis的键值
	 * @param value 存储的值信息
	 * @param date 失效的时间
	 * @return 返回是否存储成功
	 */
	public boolean setExpireAt(String key, Object value, Date date) {
		try {
			redisTemplate.opsForValue().set(key, value);
			redisTemplate.expireAt(key, date);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/**
	 * 功能描述: 如果key值，不存在，才进行设置。 通过key键存储值，可以是字符串，也可以是Java Bean对象。
	 *
	 * @param key   redis的键值
	 * @param value 存储的值信息
	 * @return 返回是否存储成功
	 */
	public boolean setIfAbsent(String key, Object value) {
		try {
			return redisTemplate.opsForValue().setIfAbsent(key, value);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 功能描述: 如果key值，不存在，才进行设置。通过key键存储值,有失效时间，可以是字符串，也可以是Java Bean对象。
	 *
	 * @param key           redis的键值
	 * @param value         存储的值信息
	 * @param expireSeconds 失效的时间，单位是秒
	 * @return 返回是否存储成功
	 */
	public boolean setIfAbsent(String key, Object value, long expireSeconds) {
		try {
			if (expireSeconds > 0) {
				return redisTemplate.opsForValue().setIfAbsent(key, value, expireSeconds, TimeUnit.SECONDS);
			} else {
				return redisTemplate.opsForValue().setIfAbsent(key, value);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 功能描述: 如果key值，不存在，才进行设置。通过key键存储值,有失效时间，可以是字符串，也可以是Java Bean对象。
	 *
	 * @param key      redis的键值
	 * @param value    存储的值信息
	 * @param time     失效的时间
	 * @param timeUnit 失效的时间单位，有秒，分钟，小时，天。
	 * @return 返回是否存储成功
	 */
	public boolean setIfAbsent(String key, Object value, long time, TimeUnit timeUnit) {
		try {
			if (time > 0) {
				return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
			} else {
				return redisTemplate.opsForValue().setIfAbsent(key, value);
			}
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/**
	 * 功能描述:批量添加值,map形式
	 *
	 * @param maps 要添加的键值对集合
	 */
	public void multiSet(Map<String, Object> maps) {
		redisTemplate.opsForValue().multiSet(maps);
	}

	/**
	 * 功能描述: 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
	 *
	 * @param maps 要添加的键值对集合
	 * @return 之前已经存在返回false, 不存在返回true
	 */
	public boolean multiSetIfAbsent(Map<String, Object> maps) {
		return redisTemplate.opsForValue().multiSetIfAbsent(maps);
	}


	/******1.2 设置值操作结束******/
	/******1.3 获取值操作开始******/
	/**
	 * 功能描述: 获取指定 key 的值,Object类型， 可以返回字符串，也可以返回 JavaBean 对象。
	 *
	 * @param key redis的键值
	 * @return 返回键值所对应的值
	 */
	public Object getForObject(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 功能描述: 获取指定 key 的值,常用于返回 JavaBean 对象。
	 *
	 * @param key redis的键值
	 * @return 返回键值所对应的值，JavaBean形式。
	 */
	public <V> V get(Object key) {
		try {
			return (V) redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 功能描述: 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
	 *
	 * @param key      redis的键值
	 * @param newValue 设置新值
	 * @return 返回key的旧值
	 */
	public Object getAndSet(String key, Object newValue) {
		return redisTemplate.opsForValue().getAndSet(key, newValue);
	}

	/**
	 * 功能描述: 批量获取相应的key的值列表信息
	 *
	 * @param keys
	 * @return
	 */
	public List<Object> multiGet(Collection<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}


	/******1.3 获取值操作结束******/
	/******1.4 过期操作开始******/
	/**
	 * 功能描述: 设置key的过期时间，单位是小时
	 *
	 * @param key      redis的键值
	 * @param timeHour 过期时间，单位是小时
	 */
	public void setExpire(String key, int timeHour) {
		redisTemplate.expire(key, timeHour, TimeUnit.HOURS);
	}

	/**
	 * 功能描述: 设置key的过期时间，单位可以自定义
	 *
	 * @param key      redis的键值
	 * @param timeout  过期时间
	 * @param timeUnit 过期时间的基本单位
	 */
	public void setExpire(String key, int timeout, TimeUnit timeUnit) {
		redisTemplate.expire(key, timeout, timeUnit);
	}

	/**
	 * 功能描述: 设置key的过期时间，是否设置成功。 单位可以自定义
	 *
	 * @param key      redis的键值
	 * @param timeout  过期时间
	 * @param timeUnit 过期时间的基本单位
	 * @return 返回是否设置成功
	 */
	public Boolean expire(String key, long timeout, TimeUnit timeUnit) {
		return redisTemplate.expire(key, timeout, timeUnit);
	}

	/**
	 * 功能描述: 设置key值在 date 时间时过期。
	 *
	 * @param key  redis的键值
	 * @param date 设置的过期时间
	 * @return 返回是否设置成功
	 */
	public Boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}

	/**
	 * 功能描述: 返回 key 的剩余的过期时间,单位是秒
	 *
	 * @param key redis的键值
	 * @return 返回 key 的剩余的过期时间,单位是秒
	 */
	public Long getExpire(String key) {

		return redisTemplate.getExpire(key);
	}

	/**
	 * 功能描述: 返回 key 的剩余的过期时间,单位自定义
	 *
	 * @param key      redis的键值
	 * @param timeUnit 日期单位
	 * @return 返回 key 的剩余的过期时间,单位自定义
	 */
	public Long getExpire(String key, TimeUnit timeUnit) {

		return redisTemplate.getExpire(key, timeUnit);
	}

	/**
	 * 功能描述: 移除 key 的过期时间，key 将持久保持
	 *
	 * @param key redis的键值
	 * @return 返回是否设置成功
	 */
	public Boolean persist(String key) {
		return redisTemplate.persist(key);
	}
	/******1.4 过期值操作结束******/
	/******1.5 其他key操作开始******/
	/**
	 * 功能描述: 序列化key
	 *
	 * @param key redis的键值
	 * @return 返回序列化好的key值
	 */
	public byte[] dump(String key) {

		return redisTemplate.dump(key);
	}

	/**
	 * 功能描述:是否存在key
	 *
	 * @param key redis的键值
	 * @return true表示存在该key, false表示不存在该key
	 */
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 功能描述: 查找能匹配到的key
	 *
	 * @param pattern 匹配的正则式
	 * @return 查找能匹配到的key
	 */
	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 功能描述: 查找能匹配到的key
	 *
	 * @param keyPattern 匹配的正则式
	 * @return 查找能匹配到的key
	 */
	public Set<String> getKeys(String keyPattern) {
		return redisTemplate.keys(keyPattern);
	}

	/**
	 * 功能描述: 将当前数据库的 key 移动到给定的数据库 db 当中
	 *
	 * @param key     redis的键值
	 * @param dbIndex 要移动到的数据，默认为0~15
	 * @return
	 */
	public Boolean move(String key, int dbIndex) {
		return redisTemplate.move(key, dbIndex);
	}

	/**
	 * 功能描述： 从当前数据库中随机返回一个 key
	 *
	 * @return 随机返回一个key
	 */
	public String randomKey() {
		return redisTemplate.randomKey();
	}

	/**
	 * 功能描述：修改 key 的名称
	 *
	 * @param oldKey 老的键
	 * @param newKey 新的键值
	 */
	public void rename(String oldKey, String newKey) {
		redisTemplate.rename(oldKey, newKey);
	}

	/**
	 * 功能描述: 仅当 newkey 不存在时，将 oldKey 改名为 newkey
	 *
	 * @param oldKey 老的键
	 * @param newKey 新的键值
	 * @return 返回设置是否成功
	 */
	public Boolean renameIfAbsent(String oldKey, String newKey) {
		return redisTemplate.renameIfAbsent(oldKey, newKey);
	}

	/**
	 * 功能描述: 返回 key 所储存的值的类型 为五种数据类型和none
	 *
	 * @param key redis的键值
	 * @return 返回当前键值的数据类型
	 */
	public DataType type(String key) {
		return redisTemplate.type(key);
	}
	/******1.5 其他key操作结束******/

	/** -------------------string相关操作--------------------- */
	/**
	 * 功能描述:设置指定 key 的值
	 *
	 * @param key   redis的键值
	 * @param value 键值要保存的字符串值
	 */
	public void setForStr(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 功能描述: 返回指定 key 的值
	 *
	 * @param key redis的键值
	 */
	public String getForStr(String key) {
		Object value = redisTemplate.opsForValue().get(key);
		return null == value ? null : value.toString();
	}

	/**
	 * 功能描述:返回 key 中字符串值的子字符
	 *
	 * @param key   redis的键值
	 * @param start 开始索引
	 * @param end   结束索引
	 * @return 返回截取字符串的值
	 */
	public String getRange(String key, long start, long end) {
		return redisTemplate.opsForValue().get(key, start, end);
	}

	/**
	 * 功能描述: 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
	 *
	 * @param key    redis的键值
	 * @param value  要替换的值
	 * @param offset 从指定位置开始覆写
	 */
	public void setRange(String key, String value, long offset) {
		redisTemplate.opsForValue().set(key, value, offset);
	}

	/**
	 * 功能描述:获取字符串的长度
	 *
	 * @param key redis的键值
	 * @return 返回字符串的长度
	 */
	public Long size(String key) {
		return redisTemplate.opsForValue().size(key);
	}

	/**
	 * 功能描述: 将key对应的数字型字符串值加1
	 *
	 * @param key redis的键值 存储的是数字型字符串
	 * @return 返回增加后的新值
	 */
	public Long increment(String key) {
		return redisTemplate.opsForValue().increment(key);
	}

	/**
	 * 功能描述: 增加(自增长), 负数则为自减
	 *
	 * @param key   redis的键值 存储的是数字型字符串
	 * @param delta 增加的步长
	 * @return 返回增加后的新值
	 */
	public Long increment(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * 功能描述: 增加(自增长), 负数则为自减，可增加浮点型数
	 *
	 * @param key       redis的键值 存储的是数字型字符串
	 * @param increment 增加的步长
	 * @return 返回增加后的新值
	 */
	public Double incrementByDouble(String key, double increment) {
		return redisTemplate.opsForValue().increment(key, increment);
	}

	/**
	 * 功能描述: 追加到末尾,字符串添加
	 *
	 * @param key   redis的键值
	 * @param value 要追加的值
	 * @return 返回追加后的新值
	 */
	public Integer append(String key, String value) {
		return redisTemplate.opsForValue().append(key, value);
	}


	/** -------------------3.hash相关操作------------------------- */
	/******3.1放置值开始******/
	/**
	 * 功能描述: 往key 里面放置单个值
	 *
	 * @param key     redis的键值
	 * @param hashKey 列字段名
	 * @param value   列字段对应的值
	 */
	public void hPut(String key, String hashKey, Object value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	/**
	 * 功能描述: 往key 里面放置多个值
	 *
	 * @param key  redis的键值
	 * @param maps 字段键值集合
	 */
	public void hPut(String key, Map<String, Object> maps) {

		redisTemplate.opsForHash().putAll(key, maps);
	}

	/**
	 * 功能描述: 添加字段键值对,使用hPut() 方法
	 *
	 * @param key    redis的键值
	 * @param hKey   字段名
	 * @param hValue 字段值
	 * @return
	 * @deprecated 暂时不再使用
	 */
	@Deprecated
	public boolean hSet(String key, Object hKey, Object hValue) {
		try {
			redisTemplate.opsForHash().put(key, hKey, hValue);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/**
	 * 功能描述: 批量添加字段键值对，使用hPut() 方法
	 *
	 * @param key  redis的键值
	 * @param maps 字段键值对集合
	 * @return
	 * @deprecated 暂时不再使用
	 */
	@Deprecated
	public boolean hSet(String key, Map<String, ?> maps) {
		try {
			redisTemplate.opsForHash().putAll(key, maps);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/**
	 * 功能描述: 仅当hashKey不存在时才设置
	 *
	 * @param key     redis的键值
	 * @param hashKey 字段名
	 * @param value   字段名对应的值
	 * @return
	 */
	public Boolean hPutIfAbsent(String key, String hashKey, Object value) {
		return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	/**
	 * 功能描述:为哈希表 key 中的指定字段的整数值加上增量 increment
	 *
	 * @param key       redis的键值
	 * @param field     字段名
	 * @param increment 步长
	 * @return
	 */
	public Long hIncrBy(String key, Object field, long increment) {
		return redisTemplate.opsForHash().increment(key, field, increment);
	}

	/**
	 * 功能描述:为哈希表 key 中的指定字段的整数值加上增量 increment,浮点数
	 *
	 * @param key   redis的键值
	 * @param field 字段名
	 * @param delta 步长，可为浮点数
	 * @return
	 */
	public Double hIncrByFloat(String key, Object field, double delta) {
		return redisTemplate.opsForHash().increment(key, field, delta);
	}

	/******3.1放置值结束******/
	/******3.2获取值开始******/

	/**
	 * 功能描述: 获取单个键值对
	 *
	 * @param key   redis的键值
	 * @param field 字段名
	 * @return
	 */
	public Object hGet(String key, String field) {
		try {
			return redisTemplate.opsForHash().get(key, field);
		} catch (Exception e) {
			log.error("发生异常", e);
			return null;
		}
	}

	/**
	 * 功能描述: 查询指定列的值
	 *
	 * @param key      redis的键值
	 * @param hashKeys 字段名集合
	 * @return
	 */
	public List hMulGet(String key, Collection hashKeys) {

		List<?> list = redisTemplate.opsForHash().multiGet(key, hashKeys);
		return list.stream().filter(Objects::nonNull).collect(Collectors.toList());
	}

	/**
	 * 功能描述: 查询指定列的值
	 *
	 * @param key      redis的键值
	 * @param hashKeys 字段名集合
	 * @return
	 */
	public List hMulGet(String key, Object[] hashKeys) {
		if (null == hashKeys || hashKeys.length == 0) {
			return new ArrayList();
		}
		List<Object> keyList = Arrays.asList(hashKeys);
		List<?> list = redisTemplate.opsForHash().multiGet(key, keyList);
		return list.stream().filter(Objects::nonNull).collect(Collectors.toList());
	}

	/**
	 * 功能描述: 根据大Key获取所有键值对,返回的是一个Object对象,需要强转成Map对象
	 *
	 * @param key redis的键值
	 * @return
	 */
	public Object hGet(String key) {
		try {
			return redisTemplate.opsForHash().entries(key);
		} catch (Exception e) {
			log.error("发生异常", e);
			return null;
		}
	}

	/**
	 * 功能描述: 获取所有给定字段的值
	 *
	 * @param key redis的键值
	 * @return 返回当前 key对应的所有字段的键值对集合
	 */
	public Map<Object, Object> hGetAll(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 功能描述: 获取所有给定字段的值
	 *
	 * @param key    redis的键值
	 * @param fields 指定的字段集合
	 * @return 返回当前 key对应的筛选字段里面的键值对集合
	 */
	public List<Object> hMultiGet(String key, Collection<Object> fields) {
		return redisTemplate.opsForHash().multiGet(key, fields);
	}

	/**
	 * 功能描述:获取所有哈希表中的字段
	 *
	 * @param key redis的键值
	 * @return
	 */
	public Set<Object> hKeys(String key) {

		return redisTemplate.opsForHash().keys(key);
	}

	/**
	 * 功能描述: 获取key里面的所有的值，返回map
	 *
	 * @param key redis键值
	 * @return
	 */
	public Object getHashValues(String key) {

		return redisTemplate.opsForHash().values(key);
	}

	/**
	 * 功能描述: 弹出元素后删除，返回弹出的那个元素
	 *
	 * @param key   key
	 * @param field 属性
	 * @param <T>   返回类型
	 * @return
	 */
	public <T> T hPop(String key, String field) {

		Object result = redisTemplate.opsForHash().get(key, field);
		if (null != result) {
			hDel(key, field);
		}
		return (T) result;
	}

	/**
	 * 功能描述: 弹出多个符合条件的元素后删除，返回弹出的那些元素
	 *
	 * @param key      key
	 * @param hashKeys 属性值
	 * @return
	 */
	public List hMulPop(String key, Collection hashKeys) {
		List results = hMulGet(key, hashKeys);
		if (CollectionUtils.isEmpty(results)) {
			return Collections.emptyList();
		}

		redisTemplate.opsForHash().delete(key, hashKeys.toArray());
		return results;
	}

	/**
	 * 功能描述: 获取hash列表中该键的各个属性值
	 *
	 * @param key key
	 * @return
	 */
	public Set<String> getHashFields(String key) {
		Object o = redisTemplate.opsForHash().keys(key);
		return (Set<String>) o;
	}
	/******3.2获取值结束******/
	/******3.3 删除操作开始******/

	/**
	 * 功能描述: 移除相应的字段名,返回删除的属性的那个值。
	 *
	 * @param key   redis的键值
	 * @param field 字段名
	 * @return 返回该字段名所对应的值
	 */
	@SuppressWarnings("unchecked")
	public Object hDelete(String key, String field) {
		Object result = redisTemplate.opsForHash().get(key, field);
		if (null != result) {
			hDel(key, field);
			return result;
		}
		return null;

	}

	/**
	 * 功能描述: 删除key里面所有的字段集合,返回删除的成功数目。
	 *
	 * @param key      redis键值
	 * @param hashKeys 字段集合
	 * @return
	 */
	public Long deleteHashKeys(String key, Object[] hashKeys) {
		return redisTemplate.opsForHash().delete(key, hashKeys);
	}

	/**
	 * 功能描述: 删除key里面所有的字段集合,返回删除的成功数目。
	 *
	 * @param key      redis键值
	 * @param hashKeys 字段集合
	 * @return
	 */
	public List deleteHashKeysForList(String key, Object[] hashKeys) {
		List results = hMulGet(key, hashKeys);
		if (CollectionUtils.isEmpty(results)) {
			return Collections.emptyList();
		}
		redisTemplate.opsForHash().delete(key, hashKeys);
		return results;
	}

	/**
	 * 功能描述: 移除指定列的值
	 *
	 * @param key      redis的键值
	 * @param hashKeys 字段名集合
	 * @return
	 */
	public List hDeleteForList(String key, Collection<Object> hashKeys) {
		List results = hMulGet(key, hashKeys);
		if (CollectionUtils.isEmpty(results)) {
			return Collections.emptyList();
		}
		redisTemplate.opsForHash().delete(key, hashKeys.toArray());
		return results;
	}

	/**
	 * 功能描述: 删除指定的字段集合,是否全部删除成功。
	 *
	 * @param key   redis的键值
	 * @param field 字段集合
	 * @return
	 */
	public boolean hDel(String key, Object... field) {
		try {
			redisTemplate.opsForHash().delete(key, field);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}
	/******3.3 删除操作结束******/
	/******3.4其他操作开始******/
	/**
	 * 功能描述:查看哈希表 key 中，指定的字段是否存在
	 *
	 * @param key   redis的键值
	 * @param field 字段名
	 * @return 查看哈希表 key 中，指定的字段是否存在
	 */
	public boolean hExists(String key, String field) {

		return redisTemplate.opsForHash().hasKey(key, field);
	}

	/**
	 * 功能描述: 根据大Key获取所有键值对,建议使用 hSize() 方法。
	 *
	 * @param key redis的键值
	 * @return
	 * @deprecated 暂时不再使用
	 */
	@Deprecated
	public Long hLen(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	/**
	 * 功能描述: 根据大Key获取所有键值对
	 *
	 * @param key redis的键值
	 * @return
	 */
	public Long hSize(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	/**
	 * 功能描述: key里面是否该有字段
	 *
	 * @param key   redis的键值
	 * @param field 字段名
	 * @return
	 */
	public boolean hasKey(String key, Object field) {
		try {
			return redisTemplate.opsForHash().hasKey(key, field);
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/******3.4其他操作结束******/
	/** ------------------------4.list相关操作---------------------------- */
	/*******4.1 放置值操作开始******/
	/**
	 * 功能描述: 左放置
	 *
	 * @param key   redis键值
	 * @param value 要放置的值
	 * @return
	 */
	public boolean leftPush(String key, Object value) {
		try {
			redisTemplate.opsForList().leftPush(key, value);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/**
	 * 功能描述: 左批量放置集合形式
	 *
	 * @param key  redis键值
	 * @param list 要放置的值集合
	 * @return
	 */
	public boolean leftPushAll(String key, Collection list) {
		try {
			redisTemplate.opsForList().leftPushAll(key, list);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/**
	 * 功能描述: 批量放置值，可变参数形式放置
	 *
	 * @param key   redis的键值
	 * @param value 放置的值
	 * @return
	 */
	public Long lLeftPushAll(String key, Object... value) {
		return redisTemplate.opsForList().leftPushAll(key, value);
	}

	/**
	 * 功能描述:当list存在的时候才加入
	 *
	 * @param key   redis的键值
	 * @param value 放置的值
	 * @return
	 */
	public Long lLeftPushIfPresent(String key, Object value) {
		return redisTemplate.opsForList().leftPushIfPresent(key, value);
	}

	/**
	 * 功能描述:如果pivot存在,再pivot前面添加
	 *
	 * @param key   redis的键值
	 * @param pivot 检测的值
	 * @param value 要放置的值
	 * @return
	 */
	public Long lLeftPush(String key, Object pivot, Object value) {
		return redisTemplate.opsForList().leftPush(key, pivot, value);
	}

	/**
	 * 功能描述: 右放置
	 *
	 * @param key   redis键值
	 * @param value 要放置的值
	 * @return
	 */
	public boolean rightPush(String key, Object value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	public boolean rightPushAll(String key, Collection values) {
		try {
			redisTemplate.opsForList().rightPushAll(key, values);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 功能描述: 从右边添加,可变参数形式
	 *
	 * @param key   redis的键值
	 * @param value 添加的值
	 * @return
	 */
	public Long lRightPushAll(String key, Object... value) {
		return redisTemplate.opsForList().rightPushAll(key, value);
	}

	/**
	 * 功能描述: 为已存在的列表添加值，从右添加
	 *
	 * @param key   redis的键值
	 * @param value 添加的值
	 * @return
	 */
	public Long lRightPushIfPresent(String key, Object value) {
		return redisTemplate.opsForList().rightPushIfPresent(key, value);
	}

	/**
	 * 功能描述:在pivot元素的右边添加值
	 *
	 * @param key   redis的键值
	 * @param pivot 检测的值
	 * @param value 要放置的值
	 * @return
	 */
	public Long lRightPush(String key, Object pivot, Object value) {
		return redisTemplate.opsForList().rightPush(key, pivot, value);
	}

	/**
	 * 功能描述: 更新指定索引的元素
	 *
	 * @param key   redis中的键
	 * @param index 更新的索引
	 * @param value 新值
	 * @return
	 */
	public boolean update(String key, int index, Object value) {
		try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			log.error("redisUtil的update异常:", e);
			return false;
		}
	}
	/*******4.1 放置值操作结束******/
	/*******4.2 获取值操作开始******/
	/**
	 * 功能描述: 通过索引获取列表中的元素
	 *
	 * @param key   redis的键值
	 * @param index 索引位置
	 * @return
	 */
	public Object lIndex(String key, long index) {
		return redisTemplate.opsForList().index(key, index);
	}

	/**
	 * 功能描述: 获取列表指定范围内的元素
	 *
	 * @param key   redis的键值
	 * @param start 开始位置, 0是开始位置
	 * @param end   结束位置, -1返回所有
	 * @return
	 */
	public List<Object> lRange(String key, long start, long end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	/**
	 * 功能描述: 从左到右全部查询集合中的数据信息
	 *
	 * @param key redis键值
	 * @return
	 */
	public Object range(String key) {
		try {
			List<Object> list = redisTemplate.opsForList().range(key, 0, -1);
			if (!org.springframework.util.CollectionUtils.isEmpty(list)) {
				return list;
			}
			return Collections.emptyList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Collections.emptyList();
		}
	}

	/**
	 * 功能描述: 从左边出栈,不删除元素。
	 *
	 * @param key redis键值
	 * @return
	 */
	public Object leftPop(String key) {
		try {
			return redisTemplate.opsForList().leftPop(key, 1, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("发生异常", e);
			return null;
		}
	}

	/**
	 * 功能描述: 从右边出栈，不删除元素。
	 *
	 * @param key redis键值
	 * @return
	 */
	public Object rightPop(String key) {
		try {
			return redisTemplate.opsForList().rightPop(key, 1, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("发生异常", e);
			return null;
		}
	}
	/*******4.2 获取值操作结束******/
	/*******4.3 删除值操作开始******/
	/**
	 * 功能描述:移出并获取列表的第一个元素
	 *
	 * @param key redis的键值
	 * @return 删除的元素
	 */
	public Object lLeftPop(String key) {

		return redisTemplate.opsForList().leftPop(key);
	}

	/**
	 * 功能描述： 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 *
	 * @param key      redis的键值
	 * @param timeout  等待时间
	 * @param timeUnit 时间单位
	 * @return
	 */
	public Object lBLeftPop(String key, long timeout, TimeUnit timeUnit) {
		return redisTemplate.opsForList().leftPop(key, timeout, timeUnit);
	}

	/**
	 * 功能描述：移除并获取列表最后一个元素
	 *
	 * @param key redis的键值
	 * @return 删除的元素
	 */
	public Object lRightPop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	/**
	 * 功能描述:移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 *
	 * @param key      redis的键值
	 * @param timeout  等待时间
	 * @param timeUnit 时间单位
	 * @return
	 */
	public Object lBRightPop(String key, long timeout, TimeUnit timeUnit) {
		return redisTemplate.opsForList().rightPop(key, timeout, timeUnit);
	}

	/**
	 * 功能描述：删除集合中值等于value得元素
	 *
	 * @param key   redis中的键
	 * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
	 *              index<0, 从尾部开始删除第一个值等于value的元素;
	 * @param value 要删除的值
	 * @return
	 */
	public Long lRem(String key, long index, Object value) {
		return redisTemplate.opsForList().remove(key, index, value);
	}

	/**
	 * 功能描述:删除所有等于value 的元素
	 *
	 * @param key   redis中的键
	 * @param value 要删除的值
	 * @return
	 */
	public long lRem(String key, Object value) {
		try {
			return redisTemplate.opsForList().remove(key, 0, value);
		} catch (Exception e) {
			log.error("发生异常", e);
			return 0;
		}
	}
	/*******4.3 删除值操作结束******/
	/*******4.5 其他操作开始******/
	/**
	 * 功能描述:移除列表的最后一个元素，并将该元素添加到另一个列表并返回
	 *
	 * @param sourceKey      源集合
	 * @param destinationKey 目标集合
	 * @return
	 */
	public Object lRightPopAndLeftPush(String sourceKey, String destinationKey) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
				destinationKey);
	}

	/**
	 * 功能描述：从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它；
	 * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
	 *
	 * @param sourceKey      源集合
	 * @param destinationKey 目标集合
	 * @param timeout        超时时间
	 * @param timeUnit       时间单位
	 * @return
	 */
	public Object lBRightPopAndLeftPush(String sourceKey, String destinationKey,
										long timeout, TimeUnit timeUnit) {
		return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey,
				destinationKey, timeout, timeUnit);
	}

	/**
	 * 功能描述:裁剪list
	 *
	 * @param key   redis中的键
	 * @param start 开始的索引位置
	 * @param end   结束的索引位置
	 */
	public void lTrim(String key, long start, long end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	/**
	 * 功能描述: 获取列表长度,建议使用 lSize 方法。
	 *
	 * @param key redis的键
	 * @return
	 * @deprecated 暂时不再使用
	 */
	@Deprecated
	public Long lLen(String key) {
		return redisTemplate.opsForList().size(key);
	}

	/**
	 * 功能描述: 获取列表长度
	 *
	 * @param key redis的键
	 * @return
	 */
	public Long lSize(String key) {
		return redisTemplate.opsForList().size(key);
	}
	/*******4.5 其他操作结束******/

	/** --------------------5.set相关操作-------------------------- */

	/*******5.1 添加放置操作开始******/
	/**
	 * 功能描述: 批量往Set 里面添加元素，可变 参数形式
	 *
	 * @param key    redis键值
	 * @param values 要放置的值
	 * @return
	 */
	public boolean sAdd(String key, Object... values) {
		try {
			redisTemplate.opsForSet().add(key, values);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/**
	 * 功能描述: 单个往Set 里面添加元素
	 *
	 * @param key   redis键值
	 * @param value 要放置的值
	 * @return
	 */
	public boolean sAdd(String key, Object value) {
		try {
			redisTemplate.opsForSet().add(key, value);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}
	/*******5.1 添加放置操作结束******/
	/*******5.2 查询操作开始******/
	/**
	 * 功能描述：返回里面的所有元素信息
	 *
	 * @param key redis键值
	 * @return
	 */
	public Object sMembers(String key) {
		try {
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			log.error("发生异常", e);
			return null;
		}
	}

	/**
	 * 功能描述：获取key里面的所有的元素
	 *
	 * @param key redis的键值
	 */
	public Set<Object> setMembers(String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 功能描述：随机获取集合中的一个元素
	 *
	 * @param key redis的键值
	 * @return
	 */
	public Object sRandomMember(String key) {
		return redisTemplate.opsForSet().randomMember(key);
	}

	/**
	 * 功能描述： 随机获取集合中count个元素，可能有重复的。
	 *
	 * @param key   redis的键值
	 * @param count 数目
	 * @return
	 */
	public List<Object> sRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().randomMembers(key, count);
	}

	/**
	 * 功能描述：随机获取集合中count个元素并且去除重复的，没有重复的。
	 *
	 * @param key   redis的键
	 * @param count 去重后的数目
	 * @return
	 */
	public Set<Object> sDistinctRandomMembers(String key, long count) {
		return redisTemplate.opsForSet().distinctRandomMembers(key, count);
	}
	/*******5.2 查询操作结束******/
	/*******5.3 删除操作开始******/

	/**
	 * 功能描述: 移除集合里面的单个元素
	 *
	 * @param key   redis键值
	 * @param value 要移除的单位元素的值
	 * @return
	 */
	public long sRem(String key, String value) {
		try {
			return redisTemplate.opsForSet().remove(key, value);
		} catch (Exception e) {
			log.error("发生异常", e);
			return 0;
		}
	}

	/**
	 * 功能描述: 批量移除集合里面的元素,是否全部移除成功。
	 *
	 * @param key     redis键值
	 * @param members 要移除的元素的值
	 * @return
	 */
	public boolean sRem(String key, Object... members) {
		try {
			redisTemplate.opsForSet().remove(key, members);
			return true;
		} catch (Exception e) {
			log.error("发生异常", e);
			return false;
		}
	}

	/**
	 * 功能描述：set移除元素
	 *
	 * @param key    redis键值
	 * @param values 要移除的元素
	 * @return
	 */
	public Long sRemMore(String key, Object... values) {
		return redisTemplate.opsForSet().remove(key, values);
	}


	/*******5.3 删除操作结束******/
	/*******5.4 交并差补操作开始******/
	/**
	 * 功能描述:获取两个集合的交集
	 *
	 * @param key      第一个主集合
	 * @param otherKey 第二个集合
	 * @return
	 */
	public Set<Object> sIntersect(String key, String otherKey) {
		return redisTemplate.opsForSet().intersect(key, otherKey);
	}

	/**
	 * 功能描述:key集合与otherKey集合的交集存储到destKey集合中
	 *
	 * @param key      第一个主集合
	 * @param otherKey 第二个集合
	 * @param destKey  将结果放置到目标集合里面
	 * @return
	 */
	public Long sIntersectAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().intersectAndStore(key, otherKey,
				destKey);
	}

	/**
	 * 功能描述:获取两个集合的并集
	 *
	 * @param key       第一个主集合
	 * @param otherKeys 第二个集合
	 * @return
	 */
	public Set<Object> sUnion(String key, String otherKeys) {
		return redisTemplate.opsForSet().union(key, otherKeys);
	}

	/**
	 * 功能描述：key集合与otherKey集合的并集存储到destKey中
	 *
	 * @param key      第一个主集合
	 * @param otherKey 第二个集合
	 * @param destKey  将结果放置到目标集合里面
	 * @return
	 */
	public Long sUnionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
	}

	/**
	 * 功能描述：获取两个集合的差集
	 *
	 * @param key      第一个主集合
	 * @param otherKey 第二个主集合
	 * @return
	 */
	public Set<Object> sDifference(String key, String otherKey) {
		return redisTemplate.opsForSet().difference(key, otherKey);
	}

	/**
	 * 功能描述： key集合与otherKey集合的差集存储到destKey中
	 *
	 * @param key      第一个主集合
	 * @param otherKey 其他集合
	 * @param destKey  将结果放置到目标集合里面
	 * @return
	 */
	public Long sDifference(String key, String otherKey, String destKey) {
		return redisTemplate.opsForSet().differenceAndStore(key, otherKey,
				destKey);
	}
	/*******5.4 交并差补操作结束******/
	/*******5.5 其他操作开始******/
	/**
	 * 功能描述：是否是集合里面的元素
	 *
	 * @param key   redis键值
	 * @param value 要检测的对象
	 * @return
	 */
	public boolean isMembers(String key, Object value) {
		return redisTemplate.opsForSet().isMember(key, value);
	}

	/**
	 * 功能描述：返回集合里面的元素数目,推荐使用 sSize方法
	 *
	 * @param key redis键值
	 * @return
	 * @deprecated 暂时不再使用
	 */
	@Deprecated
	public Long setSize(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	/**
	 * 功能描述：返回集合里面的元素数目
	 *
	 * @param key redis键值
	 * @return
	 */
	public Long sSize(String key) {
		return redisTemplate.opsForSet().size(key);
	}

	/**
	 * 功能描述:将元素value从一个集合移到另一个集合
	 *
	 * @param key     源集合的key
	 * @param value   要移除的值
	 * @param destKey 目标集合的key
	 * @return
	 */
	public Boolean sMove(String key, String value, String destKey) {
		return redisTemplate.opsForSet().move(key, value, destKey);
	}
	/*******5.5 其他操作结束******/
	/**------------------6.zSet相关操作--------------------------------*/

	/*******6.1 添加放置操作开始******/
	/**
	 * 功能描述：添加元素,有序集合是按照元素的score值由小到大排列
	 *
	 * @param key   redis的键
	 * @param value 放置的值
	 * @param score 成绩
	 * @return
	 */
	public Boolean zAdd(String key, Object value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	/**
	 * 功能描述:增加元素的score值，并返回增加后的值
	 *
	 * @param key   redis的键
	 * @param value 放置的值
	 * @param delta 增加成绩
	 * @return
	 */
	public Double zIncrementScore(String key, String value, double delta) {
		return redisTemplate.opsForZSet().incrementScore(key, value, delta);
	}
	/*******6.1 添加放置操作结束******/
	/*******6.2 查询操作开始******/
	/**
	 * 功能描述：返回该元素在集合的排名,有序集合是按照元素的score值由小到大排列. 最小的成绩是0
	 *
	 * @param key   redis的键
	 * @param value 元素
	 * @return 0表示第一位
	 */
	public Long zRank(String key, Object value) {
		return redisTemplate.opsForZSet().rank(key, value);
	}

	/**
	 * 功能描述：返回元素在集合的排名,按元素的score值由大到小排列。 最大的成绩是0
	 *
	 * @param key   redis的键
	 * @param value 要检测的元素
	 * @return
	 */
	public Long zReverseRank(String key, Object value) {
		return redisTemplate.opsForZSet().reverseRank(key, value);
	}

	/**
	 * 功能描述：获取集合的元素, 从小到大排序
	 *
	 * @param key   redis的键
	 * @param start 开始位置
	 * @param end   结束位置, -1查询所有
	 * @return
	 */
	public Set<Object> zRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().range(key, start, end);
	}

	/**
	 * 功能描述：获取集合的元素, 从大到小排序
	 *
	 * @param key   redis的键
	 * @param start 开始
	 * @param end   结束
	 * @return
	 */
	public Set<Object> zReverseRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().reverseRange(key, start, end);
	}

	/**
	 * 功能描述: 获取集合元素, 并且把score值也获取
	 *
	 * @param key   redis的键
	 * @param start 开始位置
	 * @param end   结束位置, -1查询所有
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<Object>> zRangeWithScores(String key, long start,
																   long end) {
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}

	/**
	 * 功能描述: 根据Score值查询集合元素，从小到大排序。不带成绩
	 *
	 * @param key redis的键
	 * @param min 成绩最小值
	 * @param max 成绩最大值
	 * @return
	 */
	public Set<Object> zRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScore(key, min, max);
	}

	/**
	 * 功能描述:根据Score值查询集合元素, 从小到大排序，带成绩。
	 *
	 * @param key redis的键
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key,
																		  double min, double max) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
	}

	/**
	 * 功能描述:根据Score值查询集合元素, 范围性查询，从小到大排序
	 *
	 * @param key   redis的键
	 * @param min   成绩最小值
	 * @param max   成绩最大值
	 * @param start 位置开始
	 * @param end   位置结束，-1表示查询全部。
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key,
																		  double min, double max, long start, long end) {
		return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max,
				start, end);
	}

	/**
	 * 功能描述：获取集合的元素, 从大到小排序, 并返回score值
	 *
	 * @param key   redis的键
	 * @param start 开始
	 * @param end   结束
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<Object>> zReverseRangeWithScores(String key,
																		  long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, start,
				end);
	}

	/**
	 * 功能描述：根据Score值查询集合元素, 从大到小排序，没有成绩。
	 *
	 * @param key redis的键
	 * @param min 最小成绩
	 * @param max 最大成绩
	 * @return
	 */
	public Set<Object> zReverseRangeByScore(String key, double min,
											double max) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
	}

	/**
	 * 功能描述：根据Score值查询集合元素, 从大到小排序
	 *
	 * @param key redis的键
	 * @param min 最小成绩
	 * @param max 最大成绩
	 * @return
	 */
	public Set<ZSetOperations.TypedTuple<Object>> zReverseRangeByScoreWithScores(
			String key, double min, double max) {
		return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key,
				min, max);
	}

	/**
	 * 功能描述：根据Score值查询集合元素, 从大到小排序，范围性排序
	 *
	 * @param key   redis的键
	 * @param min   最小成绩
	 * @param max   最大成绩
	 * @param start 位置开始
	 * @param end   位置结束，-1表示查询全部。
	 * @return
	 */
	public Set<Object> zReverseRangeByScore(String key, double min,
											double max, long start, long end) {
		return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max,
				start, end);
	}

	/**
	 * 功能描述：获取集合中value元素的score值
	 *
	 * @param key   redis的键
	 * @param value 值
	 * @return
	 */
	public Double zScore(String key, Object value) {
		return redisTemplate.opsForZSet().score(key, value);
	}
	/*******6.2 查询操作结束******/
	/*******6.3 删除操作开始******/
	/**
	 * 功能描述: 移除集合里面的元素
	 *
	 * @param key    redis的键
	 * @param values 移除 的元素集合
	 * @return
	 */
	public Long zRemove(String key, Object... values) {
		return redisTemplate.opsForZSet().remove(key, values);
	}

	/**
	 * 功能描述： 移除指定索引位置的成员
	 *
	 * @param key   redis的键
	 * @param start 位置开始
	 * @param end   位置结束，-1表示查询全部。
	 * @return
	 */
	public Long zRemoveRange(String key, long start, long end) {
		return redisTemplate.opsForZSet().removeRange(key, start, end);
	}

	/**
	 * 功能描述： 根据指定的score值的范围来移除成员
	 *
	 * @param key redis的键
	 * @param min 最小成绩
	 * @param max 最大成绩
	 * @return
	 */
	public Long zRemoveRangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
	}

	/*******6.3 删除操作结束******/
	/*******6.4 交并差补操作开始******/
	/**
	 * 功能描述：获取key和otherKey的并集并存储在destKey中
	 *
	 * @param key      第一个主集合
	 * @param otherKey 第二个的集合
	 * @param destKey  目标集合
	 * @return
	 */
	public Long zUnionAndStore(String key, String otherKey, String destKey) {
		return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
	}

	/**
	 * 功能描述: 获取key和其他的并集，并存储在destKey中
	 *
	 * @param key       第一个主集合
	 * @param otherKeys 其他的集合
	 * @param destKey   目标集合
	 * @return
	 */
	public Long zUnionAndStore(String key, Collection<String> otherKeys,
							   String destKey) {
		return redisTemplate.opsForZSet()
				.unionAndStore(key, otherKeys, destKey);
	}

	/**
	 * 功能描述：获取key和otherKey的交集并存储在destKey中
	 *
	 * @param key      第一个主集合
	 * @param otherKey 第二个的集合
	 * @param destKey  目标集合
	 * @return
	 */
	public Long zIntersectAndStore(String key, String otherKey,
								   String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKey,
				destKey);
	}

	/**
	 * 功能描述: 获取key和其他的交集，并存储在destKey中
	 *
	 * @param key       第一个主集合
	 * @param otherKeys 其他的集合
	 * @param destKey   目标集合
	 * @return
	 */
	public Long zIntersectAndStore(String key, Collection<String> otherKeys,
								   String destKey) {
		return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys,
				destKey);
	}
	/*******6.4 交并差补操作结束******/
	/*******6.5 其他操作开始******/
	/**
	 * 功能描述：统计score值范围内集合元素数量
	 *
	 * @param key redis的键
	 * @param min 最小成绩
	 * @param max 最大成绩
	 * @return
	 */
	public Long zCount(String key, double min, double max) {
		return redisTemplate.opsForZSet().count(key, min, max);
	}

	/**
	 * 功能描述： 获取集合的元素数量
	 *
	 * @param key redis的键
	 * @return
	 */
	public Long zZCard(String key) {
		return redisTemplate.opsForZSet().zCard(key);
	}
	/*******6.5 其他操作结束******/
}
