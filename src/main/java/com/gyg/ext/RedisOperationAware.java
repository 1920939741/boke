package com.gyg.ext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ,;,,;
 * ,;;'(    社
 * __      ,;;' ' \   会
 * /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 * ,;' \    /-.,,(   ) \    码
 * ) /       ) / )|    农
 * ||        ||  \)
 * (_\       (_\
 * redis操作pronnel的封装
 * @author ：吉金武
 * @date ：Created in 2020/12/8
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class RedisOperationAware {

    @Autowired
    private RedisTemplate redisTemplate;

    private ValueOperations valueOperations;
    private HashOperations hashOperations;
    private ZSetOperations zSetOperations;
    private SetOperations setOperations;

    @PostConstruct
    public void init(){
         valueOperations = redisTemplate.opsForValue();
         hashOperations = redisTemplate.opsForHash();
         zSetOperations = redisTemplate.opsForZSet();
         setOperations = redisTemplate.opsForSet();
    }

    /**
     * ------------------------------------------String类型操作---------------------------------------------
     */

    /**
     * 添加
     * @param key
     * @param value
     */
    public void set(String key,Object value){
        valueOperations.set(key,value);
    }

    public void set(String key,Object value,long ttl,TimeUnit timeUnit){
        valueOperations.set(key,value,ttl,timeUnit);
    }

    public boolean del(String key){
        return redisTemplate.delete(key);
    }
    /**
     * 获取key
     * @param key
     * @param clazz
     * @param <K>
     * @return
     */
    public <K> K get(String key,Class<?> clazz){
        Assert.notNull(clazz,"泛型方法不能为null!");
       return (K)valueOperations.get(key);
    }

    /**
     * 自增
     * @param key
     * @return
     */
    public Long incr(String key){
        return (Long)valueOperations.increment(key);
    }

    /**
     * 对应redis的命令setnx
     * @param key
     * @param value
     * @return
     */
    public boolean setNX(String key,Object value,long ttl,TimeUnit timeUnit){
        return valueOperations.setIfAbsent(key,value,ttl,timeUnit);
    }

    /**
     * 泛型方法
     * @param clazz 泛型方法返回类型
     * @param key 批量获取key
     * @param <T> 泛型方法
     * @return
     */
    public <T> List<T> mgGt(Class<T> clazz,String...key){
        return (List<T>)valueOperations.multiGet(Arrays.asList(key));
    }

    public boolean setEXNX(String key ,Object value, long ttl, TimeUnit timeUnit){
        return valueOperations.setIfAbsent(key, value, ttl, timeUnit);
    }

    public Boolean setIfAbsent(String key, Object value){
        return valueOperations.setIfAbsent(key,value);
    }

    /**
     * ------------------------------------------Hah类型操作---------------------------------------------
     */

    /**
     * 设置hash的 key 中的域 field 的值设为value
     * @param key
     * @param field
     * @param value
     */
    public void hSet(String key,String field,Object value){
        hashOperations.put(key,field,value);
    }

    /**
     * 获取key
     * @param key
     * @param field
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T hGet(String key,String field,Class<T> clazz){
        Assert.notNull(clazz,"泛型方法不能为null!");
        return (T)hashOperations.get(key,field);
    }

    /**
     * 删除key
     * @param key
     * @param field
     */
    public void delete(String key ,Object...field){
        hashOperations.delete(key,field);
    }

    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * ------------------------------------------zSet类型操作---------------------------------------------
     */
    public void zAdd(String key, Object value, Long score){
        zSetOperations.add(key,value,score);
    }


    public void remove(String key,Object...value){
        zSetOperations.remove(key,value);
    }



    public <T> Set<T> zRange(Class<T> clazz,String key,Integer start,Integer end){
       return (Set<T>) zSetOperations.range(key,start,end);
    }

    public Long zCard(String key){
       return zSetOperations.zCard(key);
    }


    public boolean expire(String key, long ttl, TimeUnit timeUnit) {
        return redisTemplate.expire(key,ttl,timeUnit);
    }

/**
 * ------------------------------------------Set类型操作---------------------------------------------
 *
 */

    public Long sAdd(String key, Object object){
        return setOperations.add(key,object);
    }

    public <T> Set<T> sRandomMembers(String key,long count){
        return setOperations.distinctRandomMembers(key,count);
    }

}
