import com.tecsun.siboss.tsbm.common.util.JsonMapper;

import java.io.IOException;

public class Test{


    @org.junit.Test
    public void test1(){
        try {
            System.out.println(JsonMapper.buildNonEmptyMapper().toJson(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}