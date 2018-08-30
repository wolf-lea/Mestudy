package com.tecsun.sisp.adapter.common.util;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:TestResponse
 * Description:解析mybatis查询结果
 * 1、解析返回属性对象为日期类型的数据
 * 2、解析返回属性对象为BLOB类型的数据
 */

public class CustomObjectMapper extends ObjectMapper{
    public CustomObjectMapper(){
        CustomSerializerFactory factory=new CustomSerializerFactory();
        factory.addGenericMapping(Date.class,new JsonSerializer<Date>() {
            @Override
            public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                   if (date!=null){
                       SimpleDateFormat dateFormat=new SimpleDateFormat(Constants.DATEOATTER);
                       jsonGenerator.writeString(dateFormat.format(date));
                   }
            }
        });
        factory.addGenericMapping(Blob.class,new JsonSerializer<Blob>() {
            @Override
            public void serialize(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                try {
                    if (blob!=null){
                        byte[] blobArray=blob.getBytes(1,(int)blob.length());
                        jsonGenerator.writeBinary(blobArray);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
//        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
//                jsonGenerator.writeString("");
//            }
//        });

        this.setSerializerFactory(factory);
    }

}
