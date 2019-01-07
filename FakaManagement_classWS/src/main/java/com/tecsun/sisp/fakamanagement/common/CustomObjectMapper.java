package com.tecsun.sisp.fakamanagement.common;

import java.io.IOException;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

/**
 * 解决Date类型返回json格式为自定义格式
 * @author tan
 *
 */
public class CustomObjectMapper extends ObjectMapper {

	 public CustomObjectMapper(){  
          CustomSerializerFactory factory = new CustomSerializerFactory();  
          factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){  
              @Override  
              public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider provider)  
                    throws IOException, JsonProcessingException {  
            	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            	  	jsonGenerator.writeString(sdf.format(value));  
              }  
          });
         factory.addGenericMapping(Blob.class, new JsonSerializer<Blob>(){
             @Override
             public void serialize(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                 if (null != blob) {
                     try {
                         byte[] returnValue = blob.getBytes(1, (int) blob.length());
                         jsonGenerator.writeBinary(returnValue);
                     } catch (Exception e) {
                         throw new RuntimeException("Blob Encoding Error!");
                     }
                 }
             }
         });
          this.setSerializerFactory(factory);  
     } 

}
