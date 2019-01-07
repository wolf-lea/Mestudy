package com.tecsun.sisp.fakamanagement.modules.service.impl.card;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardCabinetBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardBoxVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardCabinetVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardFloorVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;

/**
 * 
* @ClassName: CardCabinetServiceImpl 
* @Description: TODO(卡柜数据操作类) 
* @author huangtao
* @date 2017年7月10日 上午11:47:34 
*
 */
@Service("cardCabinetService")
public class CardCabinetServiceImpl extends BaseService {

	/**
	 * mapper文件namespace属性值
	 */
	private final static String packageName="com.tecsun.sisp.fakamanagement.modules.service.impl.card.CardCabinetServiceImpl.";

	/**
	 * 创建卡柜
	 * @param ccvo 卡柜对象
	 * @return
	 */
	public Integer insertCabinet(CardCabinetVO ccvo) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"insertCabinet",ccvo);
	}

	/**
	 * 创建卡层
	 * @param cfvo 卡层对象
	 * @return
	 */
	public Integer insertFloor(CardFloorVO cfvo) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"insertFloor",cfvo);
	}

	/**
	 * 创建卡盒
	 * @param cbvo 卡盒对象
	 * @return
	 */
	public Integer insertBox(CardBoxVO cbvo) {
		return this.getFakaSqlSessionTemplate().insert(packageName+"insertBox",cbvo);
	}
	
	/**
	 * 创建卡柜、层、卡盒
	 * @param vo
	 * @return
	 */
	public String createCabinet(CardCabinetVO vo){
		String result="";
		try {
			insertCabinet(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return "创建卡柜失败"+e.getMessage();
		}
		CardFloorVO cfvo=new CardFloorVO();
		cfvo.setCcid(vo.getId());
		result=createFloor(cfvo);
		return result;
	}
	

	/**
	 * 创建层、卡盒
	 * @param vo
	 * @return
	 */
	public String createFloor(CardFloorVO vo) {
		try {
			insertFloor(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return "创建卡层失败"+e.getMessage();
		}
		try {
			CardBoxVO cbvo=new CardBoxVO();
			cbvo.setCfid(vo.getId());
			cbvo.setMaxnum(250);
			insertBox(cbvo);
		} catch (Exception e) {
			e.printStackTrace();
			return "创建卡盒失败"+e.getMessage();
		}
		return "创建成功";
	}

	/**
	 * 查询卡盒
	 * @param cbvo 查询条件对象  一般根据cfid查询
	 * @return
	 */
	public List<CardBoxVO> queryBoxs(CardBoxVO cbvo) {
		return this.getFakaSqlSessionTemplate().selectList(packageName+"queryBoxs",cbvo);
	}
	
	/**
	 * 查询卡层 
	 * @param cfvo 查询条件对象  一般根据ccid查询
	 * @return
	 */
	public List<CardFloorVO> queryFloors(CardFloorVO cfvo) {
		return this.getFakaSqlSessionTemplate().selectList(packageName+"queryFloors",cfvo);
	}
	
	/**
	 * 查询卡柜
	 * @param ccvo 查询条件对象  一般根据userid查询
	 * @return
	 */
	public List<CardCabinetVO> queryCabinets(CardCabinetVO ccvo) {
		return this.getFakaSqlSessionTemplate().selectList(packageName+"queryCabinets",ccvo);
	}
	/**
	 * 查询卡柜、层、卡盒下有没有卡
	 * @param bean 查询条件对象  一般是传入各级id进行查询
	 * @return
	 */
	public Integer isHaveCard(CardCabinetBean bean) {
		return this.getFakaSqlSessionTemplate().selectOne(packageName+"isHaveCard",bean);
	}
	
	
	/**
	 * 删除卡层
	 * @param cfvo
	 * @return
	 */
	public Integer deleteFloors(CardFloorVO cfvo) {
		return this.getFakaSqlSessionTemplate().delete(packageName+"deleteFloors",cfvo);
	}
	
	
	/**
	 * 删除卡柜
	 * @param ccvo
	 * @return
	 */
	public Integer deleteCabinets(CardCabinetVO ccvo) {
		return this.getFakaSqlSessionTemplate().delete(packageName+"deleteCabinets",ccvo);
	}
	
	/**
	 * 删除卡盒
	 * @param cbvo
	 * @return
	 */
	public Integer deleteBoxs(CardBoxVO cbvo) {
		return this.getFakaSqlSessionTemplate().delete(packageName+"deleteBoxs",cbvo);
	}
	/**
	 * 修改最大数量
	 * @param cbvo
	 * @return
	 */
	public Integer updateMaxNum(CardCabinetBean cbvo) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateMaxNum",cbvo);
	}
	
	/**
	 * 删除卡层及卡盒
	 * @param vo
	 * @throws Exception
	 */
	public void deleteFloor(CardFloorVO vo) throws Exception{
		CardBoxVO cbvo=new CardBoxVO();
		cbvo.setCfid(vo.getId());
		for (CardBoxVO bvo : queryBoxs(cbvo)) {
			deleteBoxs(bvo);
		}
		deleteFloors(vo);
	}
	
	/**
	 * 删除卡柜及卡层、卡盒
	 * @param vo
	 * @throws Exception
	 */
	public void deleteCabinet(CardCabinetVO vo) throws Exception{
		CardFloorVO cfvo=new CardFloorVO();
		cfvo.setCcid(vo.getId());
		for (CardFloorVO fvo : queryFloors(cfvo)) {
			deleteFloor(fvo);
		}
		deleteCabinets(vo);
	}

	public Integer updateCcid(CardCabinetVO bean) {
		return this.getFakaSqlSessionTemplate().update(packageName+"updateCcid",bean);
	}

}
