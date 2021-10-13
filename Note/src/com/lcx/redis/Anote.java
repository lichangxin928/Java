package com.lcx.redis;

public class Anote {
   /*
   * redis 数据结构
   * 	1. redis 存储的是：key value 格式数据，key都是字符串，value有吴忠不同数据结构
   * 		数据结构：
   * 			1. String
   * 			2. hash
   * 			3. list
   * 			4. set: 不允许重复元素
   * 			5. sortedset:不允许重复元素，并且元素有序
   * 	2. String
   * 		1. 存储：set key value
   * 		2. 获取：get key
   * 		3. 删除：del key
   * 	3. hash
   * 		1. 存储：hset key field value
   *		2. 获取：hget key field
   *            hgetall：获取所有map
   *		3. 删除：hdel key field
   * 	4. list
   * 		1. 存储：lpush key value ：左边加入
   *                rpush key value ：右边加入
   * 		2. 获取：lrange key start end：范围获取
   *
   * 		3. 删除：lpop key：删除列表最左边元素，并将元素返回
   *                rpop keu：删除列表最右边元素，并将元素返回
   *    5. set：不允许重复
   *        1. 存储：sadd key value
   *        2. 获取：smember key:获取set 集合中的所有元素
   *        3. 删除：srem key value：删除set 集合中的某个元素
   *    6. soutedset：有序且不重复
   *        1. 储存：zadd key score value：
   *        2. 获取：zrange key start end
   *        3. 删除：zrem key value
   *    7. m通用命令
   *        1. keys * ：查询所有键
   *        2. type key：获取对应 value类型
   *        3. del key：删除指定的key value
   * redis 持久化
   *    1. redis 是一个内存数据库，当 redis 服务器重启过后，将数据保存到硬盘中
   *    2. 持久化机制
   *        1. RDB：默认方式，不需要进行配置，默认就使用这种机制
   *        2. AOF：采用日志的方式，可以记录每一条命令的炒作。可以每一次操作后，持久化数据
   * Jedis
   *    jedis：一款操作 redis 的数据库工具
   *
   * jedis 连接池 JedisPool
   *    使用：
   *
   *
   * */


}
