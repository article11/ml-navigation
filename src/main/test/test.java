import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by Administrator on 2016/11/4.
 */

public class test {

    public static void main(String[] args) throws ClassNotFoundException {
        String serverId="1";
        String playerName="test";
        String playerLevel="98";
        Map<String,String> map=new HashMap<String, String>();
        map.put("serverId",serverId);
        map.put("playerName",playerName);
        map.put("playerLevel",playerLevel);


        LinkedHashMap<String,Map<String,String>> maplist=new LinkedHashMap<String, Map<String,String>>();


        maplist.put(map.get("serverId"),map);


        System.out.println(JSON.toJSONString(maplist));


        Map<String,String> map2=new HashMap<String, String>();
        map2.put("serverId","20");
        map2.put("playerName",playerName);
        map2.put("playerLevel",playerLevel);

        maplist.put(map2.get("serverId"),map2);



        maplist.remove(map.get("serverId"));
        maplist.put(map.get("serverId"),map);



        Map<String,String> map3=new HashMap<String, String>();


        LinkedList<String> list=new LinkedList<String>();

        list.addFirst(map.get("serverId"));
        list.addFirst(map2.get("serverId"));


//        for(int i = 0; i < list.size(); i++)
//        {
//            String tem=list.get(i);
//            if(tem.equals(map.get("serverId"))){
//                list.remove(i);
//            }
//        }



        String jsonList=   JSON.toJSONString(list);

//        System.out.println(jsonList);




        Map<String,String> map4=new HashMap<String, String>();
        String json=   JSON.toJSONString(maplist);


        map4.put("lastServerList",jsonList);
        map4.put("serverPlayerShortInfo",json);

        String json4=   JSON.toJSONString(map4);


//        System.out.println(json4);
//        System.out.println(JSON.toJSON(map4));

        JSONObject jsonObject = JSONObject.parseObject(json4);

        System.out.println(jsonObject);

        System.out.println(jsonObject.get("lastServerList"));



        List s= JSON.parseArray((String) jsonObject.get("lastServerList"),String.class);



        LinkedList<String> list2=new LinkedList<String>();

        list2.addAll(s);

        System.out.println(list2);








//        List<String> list2=new ArrayList<String>();
//
//
//        list2= (List<String>) jsonObject.get("lastServerList");
//
//        System.out.println(list2);

    }



}