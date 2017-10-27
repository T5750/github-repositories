package cn.ictgu.tools;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集合工具类
 */
@Log4j2
public class CollectionUtils {

    /**
     * 将 List 集合转化为 Map, 可将 List 对象的任意字段当做 Map 的 key
     * @param list          对象集合
     * @param methodName    获取字段值的方法，比如 "getId"
     * @param <V>           key的类型，比如 Long
     * @param <T>           对象类型，比如 User
     * @return  Map
     */
    @SuppressWarnings("unchecked")
    public static <V, T> Map<V, List<T>> listToListMap(List<T> list, String methodName){
        Map<V, List<T>> map = new HashMap<>();
        if (list.size() == 0){
            return map;
        }
        try{
            for (T obj : list) {
                Method method = obj.getClass().getMethod(methodName);
                V key = (V) method.invoke(obj);
                if (map.get(key) == null){
                    List<T> objList = new ArrayList<>();
                    objList.add(obj);
                    map.put(key, objList);
                }else {
                    map.get(key).add(obj);
                }
            }
        }catch (Exception exception){
            log.error("List 转化为 Map 失败！");
        }
        return map;
    }

}
