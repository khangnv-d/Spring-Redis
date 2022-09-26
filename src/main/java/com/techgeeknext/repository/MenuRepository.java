package com.techgeeknext.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.techgeeknext.entity.Menu;
import java.util.List;

@Repository
public class MenuRepository {
    public static final String HASH_KEY_NAME = "MENU-ITEM";
    @Autowired
    private RedisTemplate redisTemplate;

    public Menu save(Menu menu){
        redisTemplate.opsForHash().put(HASH_KEY_NAME,menu.getId(),menu);
        return menu;
    }

    public List<Menu> findAll(){
        return redisTemplate.opsForHash().values(HASH_KEY_NAME);
    }

    public Menu findItemById(int id){
        return (Menu) redisTemplate.opsForHash().get(HASH_KEY_NAME,id);
    }

    public String deleteMenu(int id){
        redisTemplate.opsForHash().delete(HASH_KEY_NAME,id);
        return "Menu deleted successfully !!";
    }
}
