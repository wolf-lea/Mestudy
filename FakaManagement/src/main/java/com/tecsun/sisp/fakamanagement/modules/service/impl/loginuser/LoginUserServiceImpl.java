package com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.CardCountBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.LoginPassswordVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.CardCountVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("loginUserService")
public class LoginUserServiceImpl extends BaseService{
	
	
	private final static String packageName="com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl.";
	
	//通过用户ID查询发卡网点
	public String getFkwd(String userid){
		return this.getUserSqlSessionTemplate().selectOne(packageName+"getFkwd", userid);
	}
    //通过用户ID查询入库网点
    public ORGVO getRkwd(Integer userid){
        return this.getUserSqlSessionTemplate().selectOne(packageName+"getRkwd", userid);
    }

    //通过网点ID查询发卡网点
    public DistinctAndBankVO getFkwdById(Integer orgId){
        return this.getUserSqlSessionTemplate().selectOne(packageName+"getFkwdById", orgId);
    }

	public List<DistinctAndBankVO> queryFkwdByCode(DistinctAndBankVO vo){
        return this.getUserSqlSessionTemplate().selectList(packageName+"queryFkwdByCode", vo);
    }
	public String getName(String userid){
		return this.getUserSqlSessionTemplate().selectOne(packageName+"getName", userid);
	}
    //通过发卡网点查询区域编码
	public String getReginalCode(String fkwd){
		return this.getUserSqlSessionTemplate().selectOne(packageName+"getReginalCode", fkwd);
	}
	public List<ORGVO> queryAllFkwd(String parentid){
		return this.getUserSqlSessionTemplate().selectList(packageName + "queryAllFkwd", parentid);
	}
    public List<ORGVO> queryAllBranch(ORGVO orgvo){
        return this.getUserSqlSessionTemplate().selectList(packageName + "queryAllBranch", orgvo);
    }
    //通过发卡网点查询银行编码
    public String getBankCode(String fkwd){
        return this.getUserSqlSessionTemplate().selectOne(packageName + "getBankCode", fkwd);
    }

    //查询银行信息
    public List<DistinctAndBankVO> queryBankInfo(){
        return this.getUserSqlSessionTemplate().selectList(packageName + "queryBankInfo");
    }

    //查询区域信息
    public List<DistinctAndBankVO> queryDistinctInfo(DistinctAndBankVO vo){
        return this.getUserSqlSessionTemplate().selectList(packageName+"queryDistinctInfo", vo);
    }

    //通过编码查询区域名称
    public List<DistinctAndBankVO> queryDistinctByCode(DistinctAndBankVO vo){
        return this.getUserSqlSessionTemplate().selectList(packageName+"queryDistinctByCode", vo);
    }

    //通过编码查询银行名称
    public List<DistinctAndBankVO> queryBankByCode(DistinctAndBankVO vo){
        return this.getUserSqlSessionTemplate().selectList(packageName+"queryBankByCode", vo);
    }

    //通过编码查询渠道名称
    public List<DistinctAndBankVO> queryChannelByCode(DistinctAndBankVO vo){
        return this.getUserSqlSessionTemplate().selectList(packageName+"queryChannelByCode", vo);
    }

    //查询区域信息及所有下级信息
    public List<String> queryNextQY(String code){
        return this.getUserSqlSessionTemplate().selectList(packageName+"queryNextQY", code);
    }
    //通过用户ID查询入库网点
    public List<CardCountVO> queryAllOrg(CardCountBean cardCountBean){
      return this.getUserSqlSessionTemplate().selectList(packageName+"queryAllOrg", cardCountBean);
	}

    //查询发行服务登录密码
    public LoginPassswordVO getPasswordInfo(LoginPassswordVO bean){
        return this.getUserSqlSessionTemplate().selectOne(packageName+"getPasswordInfo", bean);
    }
    //修改发行服务登录密码
    public List<LoginPassswordVO> updatePassword(LoginPassswordVO bean){
        return this.getUserSqlSessionTemplate().selectList(packageName+"updatePassword", bean);
    }

    //查询用户是否为人社角色
    public List<LoginPassswordVO> checkRsTypeByUserId(LoginPassswordVO bean){
        return this.getUserSqlSessionTemplate().selectList(packageName+"checkRsTypeByUserId", bean);
    }

}
