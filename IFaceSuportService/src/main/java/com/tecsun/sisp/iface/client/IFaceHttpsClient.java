package com.tecsun.sisp.iface.client;

import com.tecsun.sisp.iface.common.util.JsonMapper;
import com.tecsun.sisp.iface.common.vo.*;
import com.tecsun.sisp.iface.common.vo.card.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName: ClientSecQueryService
 * Description:  调用接口实现类
 * Author： 张清洁
 * CreateTime： 2015年06月09日 10时:57分
 */
public class IFaceHttpsClient {

    private final static String restUri = "/iface/rest/";
    private final static String cardUri = "/iface/cardInfo/";

    private static String tokenId = "";
    private static String ip;
    private static int port;
    private static int tokenTimeOut;
    private static String channelcode;
    private static String platformContext;
    private static Thread t;
    public static boolean isVerify;

    private IFaceHttpsClient() {
    }

    /**
     * 初始化定时签到程序
     * @param ip   访问的服务器ip
     * @param port 访问的服务器port
     * @param tokenTimeOut 每次签到的间隔时间(单位：分钟)
     * @param channelcode  渠道编码
     * @param platformContext 平台编码 暂定为：sisp
     * @throws Exception
     */
    public static void ClientInit(String ip, int port, int tokenTimeOut,boolean isVerify, String channelcode, String platformContext) throws Exception {
        if (t != null) {
            throw new Exception("Error: Do not init again !!!!! ");
        }
        if (ip == null || ip.length() == 0) {
            throw new Exception("请求参数 ip 不能为空");
        }
        if (port == 0) {
            throw new Exception("请求参数 port 不能为空");
        }
        if (tokenTimeOut <= 60) {
            tokenTimeOut = 60;
        }
        IFaceHttpsClient.isVerify=isVerify;
        IFaceHttpsClient.ip = ip;
        IFaceHttpsClient.port = port;
        IFaceHttpsClient.tokenTimeOut = tokenTimeOut;
        IFaceHttpsClient.channelcode = channelcode;
        IFaceHttpsClient.platformContext = platformContext.contains("/") ? platformContext : "/" + platformContext;
        t = new Thread(new TokenGetter());
        t.start();
    }

    private static String getUrl(String classUrl, String funcName) {
        return isVerify?"https://" + ip + ":" + port + "" + platformContext + classUrl + funcName + "?tokenId=" + tokenId:"http://" + ip + ":" + port + "" + platformContext + classUrl + funcName + "?tokenId=" + tokenId;
    }

    private static void setData(Map map, Page page) throws Exception {
        page.setCount(map.get("count") == null ? null : Long.parseLong(map.get("count").toString()));
        page.setPageNo(map.get("pageNo") == null ? null : Integer.parseInt(map.get("pageNo").toString()));
        page.setPageSize(map.get("pageSize") == null ? null : Integer.parseInt(map.get("pageSize").toString()));
    }

    /**
     * 修改手机号码	SISP_IFACE_SO_01
     *
     * @param bean
     * @throws Exception
     */
    public static void changePhoneNo(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "changePhoneNo");
        HttpClientUtil.getData(port, requestUrl, bean, true);
    }

    /**
     * 查询个人社保基本信息	SISP_IFACE_SO_002
     *
     * @param bean
     * @throws Exception
     */
    public static Page<TsgrcbxxVO> getPersonSureInfo(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getPersonSureInfo");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsgrcbxxVO> page = new Page<TsgrcbxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsgrcbxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsgrcbxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 个人缴费基数信息	SISP_IFACE_SO_003
     *
     * @param bean
     * @throws Exception
     */
    public static Page<TsgrjfjsxxVO> getPersonPay(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getPersonPay");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsgrjfjsxxVO> page = new Page<TsgrjfjsxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsgrjfjsxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsgrjfjsxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 个人养老保险缴费信息	SISP_IFACE_SO_004
     *
     * @param bean
     * @throws Exception
     */
    public static Page<TsgrjfxxVO> getPersonPesionPay(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getPersonPesionPay");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsgrjfxxVO> page = new Page<TsgrjfxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsgrjfxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsgrjfxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 失业保险基本信息	SISP_IFACE_SO_005
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TslybxjbxxVO> getLoseFee(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getLoseFee");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TslybxjbxxVO> page = new Page<TslybxjbxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TslybxjbxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TslybxjbxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 失业人员待遇支付明细表	SISP_IFACE_SO_006
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TssydyffxxVO> getLosePay(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getLosePay");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TssydyffxxVO> page = new Page<TssydyffxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TssydyffxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TssydyffxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 医疗保险费用结算信息	SISP_IFACE_SO_007
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsmtbxfyjsxxVO> getHealthFee(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getHealthFee");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsmtbxfyjsxxVO> page = new Page<TsmtbxfyjsxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsmtbxfyjsxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsmtbxfyjsxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 医疗保险个人帐户信息	SISP_IFACE_SO_008
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsmtbxgrjbxxVO> getHealthAccount(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getHealthAccount");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsmtbxgrjbxxVO> page = new Page<TsmtbxgrjbxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsmtbxgrjbxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsmtbxgrjbxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 医疗保险个人缴费信息	SISP_IFACE_SO_009
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsmtbxgrjfmxVO> getHealthPay(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getHealthPay");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsmtbxgrjfmxVO> page = new Page<TsmtbxgrjfmxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsmtbxgrjfmxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsmtbxgrjfmxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 生育保险基本信息	SISP_IFACE_SO_010
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TssybxjbxxVO> getBirthInfo(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getBirthInfo");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TssybxjbxxVO> page = new Page<TssybxjbxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TssybxjbxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TssybxjbxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }


    /**
     * 养老保险月账户	SISP_IFACE_SO_011
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsylyzhxxVO> getPesionMouthAccount(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getPesionMouthAccount");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsylyzhxxVO> page = new Page<TsylyzhxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsylyzhxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsylyzhxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }


    /**
     * 在职人员养老保险个人年帐户	SISP_IFACE_SO_012
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsylgrzhxxVO> getPesionYearAccount(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getPesionYearAccount");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsylgrzhxxVO> page = new Page<TsylgrzhxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsylgrzhxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsylgrzhxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 养老保险个人缴费明细	SISP_IFACE_SO_013
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsylbxgrjfmxVO> getPesionTreatment(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getPesionTreatment");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsylbxgrjfmxVO> page = new Page<TsylbxgrjfmxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsylbxgrjfmxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsylbxgrjfmxVO.class);
                page.setData(list);
            }
        }
        return page;
    }


    /**
     * 养老保险待遇支付信息	SISP_IFACE_SO_014
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsyldyffxxVO> getPesionPay(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getPesionPay");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsyldyffxxVO> page = new Page<TsyldyffxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsyldyffxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsyldyffxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 养老保险基本信息	SISP_IFACE_SO_015
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsylbxjbxxVO> getPesionInfo(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getPesionInfo");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsylbxjbxxVO> page = new Page<TsylbxjbxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsylbxjbxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsylbxjbxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 离退休人员待遇信息	SISP_IFACE_SO_016
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsylbxltxrydyxxVO> getQuitPay(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getQuitPay");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsylbxltxrydyxxVO> page = new Page<TsylbxltxrydyxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsylbxltxrydyxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsylbxltxrydyxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 个人工伤保险基本信息	SISP_IFACE_SO_017
     *
     * @param bean
     * @throws Exception
     */
    public static Page<TsgsbxjbxxVO> getHurtInfo(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getHurtInfo");

        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsgsbxjbxxVO> page = new Page<TsgsbxjbxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsgsbxjbxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsgsbxjbxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }

    /**
     * 获取个人基本信息
     * @param bean
     * @return
     * @throws Exception
     */
    public static Page<TsjbxxVO> getSelfInfo(SecQueryBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(restUri, "getSelfInfo");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        Page<TsjbxxVO> page = new Page<TsjbxxVO>();
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            setData(map, page);
            if (map.get("data") != null) {
                List<TsjbxxVO> list = JsonMapper.buildNormalMapper().fromJsonToList(JsonMapper.toNormalJson(map.get("data")), TsjbxxVO.class);
                page.setData(list);
            }
        }
        return page;
    }


    /**
     * 卡业务  SISP_IFACE_CARD_001 卡基础数据（不包含个人照片)  社保卡号和身份证号码至少有一项不为空
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Result getCardInfo(CardInfoBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(cardUri, "getCardInfo");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            if (map != null) {
                CardBaseInfo info = JsonMapper.buildNormalMapper().fromJson(JsonMapper.toNormalJson(map), CardBaseInfo.class);
                result.setData(info);
            }
        }
        return result;
    }

    /**
     * SISP_IFACE_CARD_002 卡基础数据（包含个人照片） 社保卡号和身份证号码至少有一项不为空
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Result getCardAllInfo(CardInfoBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(cardUri, "getCardAllInfo");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            if (map != null) {
                CardAllInfo info = JsonMapper.buildNormalMapper().fromJson(JsonMapper.toNormalJson(map), CardAllInfo.class);
                result.setData(info);
            }
        }
        return result;
    }

    /**
     * SISP_IFACE_CARD_004 制卡进度查询
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static  Result getCardStatus(CardInfoBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(cardUri, "getCardStatus");
        String data = HttpClientUtil.getData(port, requestUrl, bean, true);
        Result result = JsonMapper.buildNormalMapper().fromJson(data, Result.class);
        if (result.getData() != null) {
            Map map = (Map) result.getData();
            if (map != null) {
                SettingCardQuery info = JsonMapper.buildNormalMapper().fromJson(JsonMapper.toNormalJson(map), SettingCardQuery.class);
                result.setData(info);
            }
        }
        return result;
    }

    /**
     * SISP_IFACE_CARD_005 领卡启用
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Result setStart(CardInfoBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(cardUri, "setStart");
        String result = HttpClientUtil.getData(port, requestUrl, bean, true);
        return JsonMapper.buildNormalMapper().fromJson(result, Result.class);
    }

    /**
     * SISP_IFACE_CARD_006 临时挂失
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Result setLoss(CardInfoBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(cardUri, "setLoss");
        String result = HttpClientUtil.getData(port, requestUrl, bean, true);
        return JsonMapper.buildNormalMapper().fromJson(result, Result.class);
    }

    /**
     * SISP_IFACE_CARD_007 解挂
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Result setHanging(CardInfoBean bean) throws Exception {
        if (t == null) {
            throw new Exception(" Please init   !!!!! ");
        }
        String requestUrl = getUrl(cardUri, "setHanging");
        String result = HttpClientUtil.getData(port, requestUrl, bean, true);
        return JsonMapper.buildNormalMapper().fromJson(result, Result.class);
    }

    /**
     * 定时更新tokenId
     */
    private static class TokenGetter implements Runnable {
        public void run() {
            String url = isVerify ? "https://" + ip + ":" + port + platformContext + "/iface/user/checkLogin" : "http://" + ip + ":" + port + platformContext + "/iface/user/checkLogin";
            while (true) {
                try {
                    IFaceHttpsClient.tokenId = LoginUtil.checkLogin(port, url, channelcode);
                    Thread.sleep(tokenTimeOut * 60 * 1000);
                } catch (InterruptedException e) {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
