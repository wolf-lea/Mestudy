package com.tecsun.sisp.iface.server.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sandwich on 2015/12/11.
 */
public class JsonHelp {
        private static final ObjectMapper mapper = new ObjectMapper();

        public static ObjectMapper getInstance() {

            return mapper;
        }

        /**
         * jsonתjavaBean
         * @param jsonString
         * @param t
         * @return
         */
        public static <T> Object jsonToJavaBean(String jsonString , Class<T> t) {
            Object result = null;
            try {
                result = mapper.readValue(jsonString, t);
            } catch (Exception e) {
            }
            return result;
        }

        /**
         * javaBean , ���� , ���� תjson
         * @param t
         * @return
         */
        public static String javaBeanToJson(Object o){
            String json = null;
            try {
                json = mapper.writeValueAsString(o);
            }  catch (Exception e) {
            }
            return json;
        }

        /**
         * jsonתMap
         * @param jsonStr
         * @return
         */
        @SuppressWarnings("unchecked")
        public static <T> Map<String, Object> jsonToMap(String jsonStr) {
            try {
                return mapper.readValue(jsonStr, Map.class);
            } catch (Exception e) {
                return null;
            }
        }

        /**
         * MapתjavaBean
         * @param map
         * @param clazz
         * @return
         */
        @SuppressWarnings("rawtypes")
        public static <T> T mapToJavaBean(Map map, Class<T> clazz) {
            return mapper.convertValue(map, clazz);
        }

        /**
         * JSON�ַ�ת��ΪMap��JavaBean
         * @param jsonStr
         * @param clazz
         * @return
         * @throws Exception
         */
        public static <T> Map<String, T> jsonToMap(String jsonStr, Class<T> clazz)
                throws Exception {
            Map<String, Map<String, Object>> map = mapper.readValue(jsonStr,
                    new TypeReference<Map<String, T>>() {
                    });
            Map<String, T> result = new HashMap<String, T>();
            for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
                result.put(entry.getKey(), mapToJavaBean(entry.getValue(), clazz));
            }
            return result;
        }

        /**
         * JSON�����ַ�ת��ΪList��JavaBean
         * @param jsonArrayStr
         * @param clazz
         * @return
         * @throws Exception
         */
        public static <T> List<T> jsonToList(String jsonArrayStr, Class<T> clazz)
                throws Exception {
            List<Map<String, Object>> list = mapper.readValue(jsonArrayStr,
                    new TypeReference<List<T>>() {
                    });
            List<T> result = new ArrayList<T>();
            for (Map<String, Object> map : list) {
                result.add(mapToJavaBean(map, clazz));
            }
            return result;
        }
}
