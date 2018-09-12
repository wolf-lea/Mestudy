package com.tecsun.sisp.fakamanagement.modules.controller.store;

import com.tecsun.sisp.fakamanagement.common.CommUtil;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardCabinetBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardCabinetVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.store.CardCabinetManageServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 卡柜管理
 * @author liang
 * @date 2018/01/23
 */
@Controller
@RequestMapping("/faka/cardCabinetManage")
public class CardCabinetManageController extends BaseController {

    public static final Logger logger=Logger.getLogger(CardCabinetManageController.class);

    @Autowired
    private CardCabinetManageServiceImpl cardCabinetManageService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    /**
     * 新增卡柜
     * @param bean 柜名 发卡网点
     * @return
     */
    @RequestMapping(value="/addCabinet",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result addCabinet(@RequestBody CardCabinetBean bean){
        try{
            if(bean==null)
                return error("参数为空，请输入参数！");

            String fkwd=loginUserService.getFkwd(bean.getUserid());
            if(null==fkwd||fkwd.equals("")){
                return error("发卡机构获取编码为空");
            }
            bean.setUserid(fkwd);//通过userid获取发卡机构编码

            CardCabinetVO qvo=new CardCabinetVO();
            qvo.setCcid(bean.getCcid());
            qvo.setUserid(bean.getUserid());
            //根据卡柜名称和用户id查询卡柜数量
            int count= cardCabinetManageService.countCardBinets(qvo);
            if(count>0)
                return error("卡柜名称已存在！");

            CardCabinetVO cvo=new CardCabinetVO();
            cvo.setCcid(bean.getCcid());
            cvo.setUserid(bean.getUserid());
            boolean b=cardCabinetManageService.addCabinet(cvo)==1?true:false;
            if(b){
                logger.info("新增卡柜成功!");
                return ok("新增卡柜成功！");
            }else {
                logger.error("新增卡柜失败!");
                return error("新增卡柜失败！");
            }
        }catch (Exception e){
            logger.error("新增卡柜异常!",e);
            return error("新增卡柜异常！");
        }
    }

    /**
     * 查询卡柜信息
     * @param
     * @return
     */
    @RequestMapping(value="/getCabinets",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getCabinets(@RequestBody CardCabinetBean bean, HttpServletRequest req){
        try{
            if(bean.getUserid()==null || "".equals(bean.getUserid()))
                return error("发卡网点为空！");

            String fkwd=loginUserService.getFkwd(bean.getUserid());
            if(null==fkwd||fkwd.equals("")){
                return error("发卡机构获取编码为空");
            }
            bean.setUserid(fkwd);//通过userid获取发卡机构编码

            String pageno=req.getParameter("pageno");
            String pagesize=req.getParameter("pagesize");
            int no=Integer.parseInt(String.valueOf(CommUtil.isEmptyStr(pageno)?1:pageno));
            int size=Integer.parseInt(String.valueOf(CommUtil.isEmptyStr(pagesize)?10:pagesize));
            CardCabinetVO cvo=new CardCabinetVO();
            Page<CardCabinetVO> page=new Page<>(no,size);
            cvo.setUserid(bean.getUserid());
            page=cardCabinetManageService.getCabinets(page,cvo);
            return ok(page.getCount(),"查询卡柜成功！",page.getData());
        }catch (Exception e){
            logger.error("查询卡柜异常!",e);
            return error("查询卡柜异常！");
        }
    }

    /**
     * 修改卡柜
     * @param bean 柜子id 柜名 发卡网点
     * @return
     */
    @RequestMapping(value="/updateCabinet",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result updateCabinet(@RequestBody CardCabinetBean bean){
        try{
            if(bean==null)
                return error("参数为空，请输入参数！");

            String fkwd=loginUserService.getFkwd(bean.getUserid());
            if(null==fkwd||fkwd.equals("")){
                return error("发卡机构获取编码为空");
            }
            bean.setUserid(fkwd);//通过userid获取发卡机构编码

            CardCabinetVO cvo=new CardCabinetVO();
            cvo.setCcid(bean.getCcid());//柜子名称
            cvo.setUserid(bean.getUserid());
            //根据卡柜名称和用户id查询卡柜数量
            int count= cardCabinetManageService.countCardBinets(cvo);
            if(count>0)
                return error("卡柜名称已存在！");

            cvo.setId(bean.getCcaid());//柜子id
            boolean b=cardCabinetManageService.updateCabinet(cvo)==1?true:false;
            if(b){
                logger.info("修改卡柜成功!");
                return ok("修改卡柜成功！");
            }else {
                logger.error("修改卡柜失败!");
                return error("修改卡柜失败！");
            }
        }catch (Exception e){
            logger.error("修改卡柜异常!",e);
            return error("修改卡柜异常！");
        }
    }

}
