import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/4.
 */

public class test {

    public static void main(String[] args) throws ClassNotFoundException {

//        Map map2 = new HashMap();
//        String[] product_id = {"10000", "10001", "10002"};
//        map2.put("product_id", product_id);
//        String json2 = JSON.toJSONString(map2);
//        System.out.println(json2);
//        JSONObject jsonObject = JSON.parseObject(json2);
//        Set<String> productIdSet = new HashSet<String>();
//        JSONArray jsonArray = jsonObject.getJSONArray("product_id");
//        for (int i = 0; i < jsonArray.size(); i++) {
//            productIdSet.add(jsonArray.getString(i));
//        }
//
//        System.out.println("productIdSet:" + productIdSet);
//
//
//        System.out.println(productIdSet.contains("101000"));


        String[] client_id1 = {"284503496053-6gfr5dah29trfetl1l9aqfclgauimlko.apps.googleusercontent.com", "284503496053-npllpcknsme1dgrrrl0kuicsl7cd5c9u.apps.googleusercontent.com", "834588391899-plser8kvd5t8tlo7kstju0dultibui4i.apps.googleusercontent.com"};
        Map map = new HashMap();
        map.put("client_id", client_id1);
        System.out.println("map: "+JSON.toJSONString(map));






//
//        JSONArray array = JSONArray.fromObject("[{'name':'hehe','age':22}]");
//        List<Person> list = JSONArray.toList(array, Person.class);// 过时方法
//        System.out.println(list.get(0).getName());

        // 转换方法2


        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        String listStr = JSON.toJSONString(list);
        List<String> list1 = JSONArray.parseArray(listStr, String.class);
        System.out.println("list1: "+list1);









    }


}