import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/4.
 */

public class test {

    public static void main(String[] args) throws ClassNotFoundException {

        Map map2 = new HashMap();
//        String[] product_id = {"10000", "10001", "10002"};
        String[] bundle_id = {"com.bleach.cjgame6","com.bleach.cjgame30","com.bleach.cjgame98","com.bleach.cjgame198","com.bleach.cjgame328","com.bleach.cjgame648","com.bleach.cjgameyueka","com.bleach.cjgameyongjiuka"};
        map2.put("bundle_id", bundle_id);
        String json2 = JSON.toJSONString(map2);
        System.out.println(json2);
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

//
//        String project_id = "20454";
//        String authBase64 = "32165:fHC8fdeSc7sy05zI";
//        String url = "https://api.xsolla.com/merchant/merchants/32165/token";
//
//
//
//
//
//
//        Map map = new HashMap();
//        map.put("project_id", project_id);
//        map.put("authBase64", authBase64);
//        map.put("url", url);
//
//
//        System.out.println("map: "+ JSON.toJSONString(map));




//
//        JSONArray array = JSONArray.fromObject("[{'name':'hehe','age':22}]");
//        List<Person> list = JSONArray.toList(array, Person.class);// 过时方法
//        System.out.println(list.get(0).getName());

        // 转换方法2

//
//        List<String> list = new ArrayList<String>();
//        list.add("a");
//        list.add("b");
//        String listStr = JSON.toJSONString(list);
//        List<String> list1 = JSONArray.parseArray(listStr, String.class);
//        System.out.println("list1: "+list1);









    }


}