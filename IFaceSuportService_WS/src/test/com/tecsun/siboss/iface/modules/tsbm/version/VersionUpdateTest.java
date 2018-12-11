package com.tecsun.siboss.iface.modules.tsbm.version;

import com.tecsun.sisp.iface.common.util.JsonMapper;
import com.tecsun.sisp.iface.common.util.http.HttpClientUtil;
import com.tecsun.sisp.iface.common.vo.version.param.DeviceParam;
import com.tecsun.sisp.iface.common.vo.version.result.Result;
import com.tecsun.sisp.iface.server.util.DictionaryUtil;
import org.junit.Test;

/**
 * Created by zhongzhiyong on 16-1-7.
 */

public class VersionUpdateTest {
    @Test
    public void testGetDevInfos() {
        String url = DictionaryUtil.getHost("/iface/tsbm/version/checkUpdate");

        try {
            DeviceParam param = new DeviceParam();
            param.setCpu("BFEBFBFF000406E3");
            param.setAppType("DZD_HNSQ");
            param.setSoftwareVersion("2");
//        param.setLongitude(106.72);
//        param.setLatitude(26.57);
            String jsonResult = HttpClientUtil.getHttpData(url, param, true);
            System.out.printf(jsonResult);
            Result devBean = JsonMapper.buildNormalMapper().fromJson(jsonResult, Result.class);
            System.out.println(devBean.getStatusCode());
            System.out.println(devBean.getMessage());
            System.out.println(devBean.getData());
            System.out.println(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDownloadNewVersion() {
        String url = DictionaryUtil.getHost("/iface/tsbm/version/downloadVersion");
        try {
            DeviceParam param = new DeviceParam();
            param.setCpu("BFEBFBFF000406E3");
            param.setAppType("DZD_HNSQ");
            String jsonResult = HttpClientUtil.getHttpData(url, param, true);
            System.out.println(url + "==" + jsonResult);
            //Result devBean= JsonMapper.buildNormalMapper().fromJson(jsonResult, Result.class);
            //System.out.println(devBean.getStatusCode());
            //System.out.println(devBean.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testCheckVersion() {
        String url = DictionaryUtil.getHost("/iface/tsbm/version/checkVersion_V160530?tokenCode=siboss:iface:token:tsb_1472712083717");
        try {
            DeviceParam param = new DeviceParam();
            param.setCpu("02536c33000002fd");
            param.setSoftwareVersion("8");
            param.setAppType("TSB_BM5_KDBL");
            String jsonResult = HttpClientUtil.getHttpData(url, param, true);
            System.out.print(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testCheckVersion_old() {
        String url1 = DictionaryUtil.getHost("/iface/tsbm/version/checkVersion");
        String url = "http://11.11.11.108:80/siboss/iface/tsbm/version/checkVersion";
        try {
            DeviceParam param = new DeviceParam();
            param.setCpu("TSB201501150001");
            param.setSoftwareVersion("2");
            //param.setAppType("TSB_BM5_KDBL");
            String jsonResult = HttpClientUtil.getHttpData(url, param, true);
            System.out.print(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
