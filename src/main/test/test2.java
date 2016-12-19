import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * Created by Administrator on 2016/11/30.
 */
public class test2 {
    public static void main(String[] args) {
        String serverId = "1";
        String playerName = "test";
        String playerLevel = "98";
        Map<String, String> map = new HashMap<String, String>();
        map.put("serverId", serverId);
        map.put("playerName", playerName);
        map.put("playerLevel", playerLevel);
        map.put("time", String.valueOf(System.currentTimeMillis()));

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("serverId", "20");
        map2.put("playerName", playerName);
        map2.put("playerLevel", playerLevel);
        map2.put("time", String.valueOf(System.currentTimeMillis() + 1));

        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("serverId", "30");
        map3.put("playerName", playerName);
        map3.put("playerLevel", playerLevel);
        map3.put("time", String.valueOf(System.currentTimeMillis() + 2));


        HashMap<String, Map<String, String>> maplist = new HashMap<String, Map<String, String>>();
        maplist.put(map.get("serverId"), map);
//        maplist.put(map2.get("serverId"), map2);
//        maplist.put(map3.get("serverId"), map3);
        String jsonMap = JSON.toJSONString(maplist);

        System.out.println(jsonMap);


        HashMap<String, Map<String, String>> tempMap = (HashMap<String, Map<String, String>>) JSON.parseObject(jsonMap, Map.class);

        List<Map<String, String>> list = new ArrayList<Map<String, String>>(tempMap.values());
        Collections.sort(list, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                if (Long.parseLong(o2.get("time")) == Long.parseLong(o1.get("time"))) {
                    return 0;
                }
                if (Long.parseLong(o2.get("time")) > Long.parseLong(o1.get("time"))) {
                    return 1;
                }

                return -1;
            }
        });
        LinkedList<String> lastServerList = new LinkedList<String>();
        for (Map<String, String> temp : list) {
            lastServerList.addLast(temp.get("serverId"));
        }
        Map<String, String> resmap = new HashMap<String, String>();
        String lastServerListjson = JSON.toJSONString(lastServerList);
        String tempMapjson = JSON.toJSONString(tempMap);
        resmap.put("lastServerList", lastServerListjson);
        resmap.put("serverPlayerShortInfo", tempMapjson);
        String res = JSON.toJSONString(resmap);
        System.out.println(res);


//        HashMap<String, String> tempMap2 = (HashMap<String, String>) JSON.parseObject(jsonMap, Map.class);
//        long tempTime;
//
//
//        if(tempMap.size()>1){
//            for (String value : tempMap.values()) {
//                tempMap2 = (HashMap<String, String>) JSON.parseObject(value, Map.class);
//                tempTime = Long.parseLong(tempMap2.get("time"));
//            }
//
//
//
//
//        }


    }
}
