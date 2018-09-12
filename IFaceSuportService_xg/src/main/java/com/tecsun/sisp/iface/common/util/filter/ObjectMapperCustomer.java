package com.tecsun.sisp.iface.common.util.filter;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * Created by DG on 2015/10/8.
 */
public class ObjectMapperCustomer extends ObjectMapper {

    public ObjectMapperCustomer(){

        super();

        //this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);

        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeString("");
            }
        });
    }

}
