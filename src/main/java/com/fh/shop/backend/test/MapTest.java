package com.fh.shop.backend.test;

import java.util.*;

public class MapTest {
    public static void main(String[] args) {
        Map stuMap = new HashMap();
        stuMap.put("name","秦琼");
        stuMap.put("age",25);
        stuMap.put("sex","男");

        //对Map使用迭代器，用以获取一个完整的键值对
        Iterator iterator = stuMap.entrySet().iterator();
        //对是否有下一个键值对进行判断
        while (iterator.hasNext()){
            //因iterator.next()输出的是Object，所以要进行强转
            Map.Entry next = (Map.Entry) iterator.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }

        System.out.println("===========");

        //使用迭代器，只获取key，通过key来获取value
        Iterator keyIterator = stuMap.keySet().iterator();
        while (keyIterator.hasNext()){
            String keyNext = (String) keyIterator.next();
            System.out.println(keyNext+":"+stuMap.get(keyNext));
        }

        System.out.println("+++++++++++");

        Map stuMap2 = new HashMap();
        stuMap2.put("name","秦琼2");
        stuMap2.put("age",25);
        stuMap2.put("sex","男2");

        List<Map> list = new ArrayList<>();
        list.add(stuMap);
        list.add(stuMap2);

        //在list中加有map，对map循环遍历
        for (Map map : list) {
            Iterator mapIterator = map.keySet().iterator();
            while (mapIterator.hasNext()){
                String key = (String) mapIterator.next();
                System.out.println(key+":"+map.get(key));
            }
        }

        System.out.println("-----------");

        //创建list，将list放进map
        //创建list，将list放进map
        List<String> stuList = new ArrayList<>();
        stuList.add("水怪");
        stuList.add("山精");
        stuList.add("树妖");
        stuMap2.put("stuList",stuList);

        List<String> stuList2 = new ArrayList<>();
        stuList2.add("水怪2");
        stuList2.add("山精2");
        stuList2.add("树妖2");
        stuMap2.put("stuList2",stuList2);

        List<Map> list2 = new ArrayList<>();
        list.add(stuMap);
        list.add(stuMap2);
        for (Map mapInfo : list2) {
            Iterator mapInfoKey = mapInfo.keySet().iterator();
            while (mapInfoKey.hasNext()){
                String mapKey = (String) mapInfoKey.next();

                if (mapKey.equals("stuList2")){
                    List<String> mapInfoList = (List<String>) mapInfo.get(mapKey);
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String s : mapInfoList) {
                        stringBuffer.append(s).append(",");
                    }
                    System.out.println(mapKey+":"+stringBuffer.toString());
                }
                else {
                    System.out.println(mapKey+":"+mapInfo.get(mapKey));
                }
            }
        }


    }

}
