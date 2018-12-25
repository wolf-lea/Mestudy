package com.fly.demo.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fly.common.PaginationSupport;
import com.fly.core.DbutilsTemplate;
import com.fly.demo.dao.MeasureDAO;
import com.fly.demo.entity.Measure;

/**
 * 
 * MeasureDAO 接口实现类
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@SuppressWarnings("unchecked")
@Repository
public class MeasureDAOImpl implements MeasureDAO
{
    @Autowired
    DbutilsTemplate dbutilsTemplate;
    
    /**
     * <默认构造函数>
     */
    public MeasureDAOImpl()
    {
        super();
    }
    
    /**
     * 构造whereClause
     * 
     * @param criteria
     * @return
     */
    private String buildWhereClause(Measure criteria)
    {
        if (criteria == null)
        {
            return "";
        }
        StringBuilder whereClause = new StringBuilder();
        if (criteria.getId() != null)
        {
            whereClause.append(" and id=?");
        }
        if (criteria.getNo() != null)
        {
            whereClause.append(" and no=?");
        }
        if (criteria.getAs() != null)
        {
            whereClause.append(" and As_=?");
        }
        if (criteria.getCr() != null)
        {
            whereClause.append(" and Cr=?");
        }
        if (criteria.getCd() != null)
        {
            whereClause.append(" and Cd=?");
        }
        if (criteria.getCu() != null)
        {
            whereClause.append(" and Cu=?");
        }
        if (criteria.getPb() != null)
        {
            whereClause.append(" and Pb=?");
        }
        if (criteria.getZn() != null)
        {
            whereClause.append(" and Zn=?");
        }
        if (criteria.getNi() != null)
        {
            whereClause.append(" and Ni=?");
        }
        if (criteria.getHg() != null)
        {
            whereClause.append(" and Hg=?");
        }
        if (criteria.getSn() != null)
        {
            whereClause.append(" and Sn=?");
        }
        if (criteria.getCo() != null)
        {
            whereClause.append(" and Co=?");
        }
        if (criteria.getAg() != null)
        {
            whereClause.append(" and Ag=?");
        }
        if (criteria.getSb() != null)
        {
            whereClause.append(" and Sb=?");
        }
        if (criteria.getBa() != null)
        {
            whereClause.append(" and Ba=?");
        }
        if (criteria.getMg() != null)
        {
            whereClause.append(" and Mg=?");
        }
        if (criteria.getTi() != null)
        {
            whereClause.append(" and Ti=?");
        }
        if (criteria.getW() != null)
        {
            whereClause.append(" and W=?");
        }
        if (criteria.getAl() != null)
        {
            whereClause.append(" and Al=?");
        }
        if (criteria.getTh() != null)
        {
            whereClause.append(" and Th=?");
        }
        if (criteria.getSr() != null)
        {
            whereClause.append(" and Sr=?");
        }
        if (criteria.getCs() != null)
        {
            whereClause.append(" and Cs=?");
        }
        if (whereClause.length() > 4)
        {
            return whereClause.substring(4);
        }
        return "";
    }
    
    /**
     * 构造whereParams
     * 
     * @param criteria
     * @return
     * 
     */
    private List<Object> buildWhereParams(Measure criteria)
    {
        if (criteria == null)
        {
            return Collections.emptyList();
        }
        List<Object> whereParams = new ArrayList<>();
        if (criteria.getId() != null)
        {
            whereParams.add(criteria.getId());
        }
        if (criteria.getNo() != null)
        {
            whereParams.add(criteria.getNo());
        }
        if (criteria.getAs() != null)
        {
            whereParams.add(criteria.getAs());
        }
        if (criteria.getCr() != null)
        {
            whereParams.add(criteria.getCr());
        }
        if (criteria.getCd() != null)
        {
            whereParams.add(criteria.getCd());
        }
        if (criteria.getCu() != null)
        {
            whereParams.add(criteria.getCu());
        }
        if (criteria.getPb() != null)
        {
            whereParams.add(criteria.getPb());
        }
        if (criteria.getZn() != null)
        {
            whereParams.add(criteria.getZn());
        }
        if (criteria.getNi() != null)
        {
            whereParams.add(criteria.getNi());
        }
        if (criteria.getHg() != null)
        {
            whereParams.add(criteria.getHg());
        }
        if (criteria.getSn() != null)
        {
            whereParams.add(criteria.getSn());
        }
        if (criteria.getCo() != null)
        {
            whereParams.add(criteria.getCo());
        }
        if (criteria.getAg() != null)
        {
            whereParams.add(criteria.getAg());
        }
        if (criteria.getSb() != null)
        {
            whereParams.add(criteria.getSb());
        }
        if (criteria.getBa() != null)
        {
            whereParams.add(criteria.getBa());
        }
        if (criteria.getMg() != null)
        {
            whereParams.add(criteria.getMg());
        }
        if (criteria.getTi() != null)
        {
            whereParams.add(criteria.getTi());
        }
        if (criteria.getW() != null)
        {
            whereParams.add(criteria.getW());
        }
        if (criteria.getAl() != null)
        {
            whereParams.add(criteria.getAl());
        }
        if (criteria.getTh() != null)
        {
            whereParams.add(criteria.getTh());
        }
        if (criteria.getSr() != null)
        {
            whereParams.add(criteria.getSr());
        }
        if (criteria.getCs() != null)
        {
            whereParams.add(criteria.getCs());
        }
        return whereParams;
    }
    
    /**
     * 根据条件删除数据
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public int deleteByCriteria(Measure criteria)
    {
        StringBuilder sql = new StringBuilder("delete from t_measure");
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        return dbutilsTemplate.update(sql.toString(), whereParams.toArray());
    }
    
    /**
     * 根据主键id删除数据
     * 
     * @param id 主键
     * @return
     * 
     */
    @Override
    public int deleteById(Long id)
    {
        String sql = "delete from t_measure where id=?";
        return dbutilsTemplate.update(sql, id);
    }
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     * 
     */
    @Override
    public int deleteById(Long[] ids)
    {
        Long[][] idsArr = new Long[ids.length][1];
        for (int i = 0; i < ids.length; i++)
        {
            idsArr[i][0] = ids[i];
        }
        String sql = "delete from t_measure where id=?";
        return dbutilsTemplate.batch(sql, idsArr).length;
    }
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     * 
     */
    @Override
    public int deleteById(List<Long> ids)
    {
        Long[][] idsArr = new Long[ids.size()][1];
        for (int i = 0; i < ids.size(); i++)
        {
            idsArr[i][0] = ids.get(i);
        }
        String sql = "delete from t_measure where id=?";
        return dbutilsTemplate.batch(sql, idsArr).length;
    }
    
    /**
     * 增加记录(插入全字段)
     * 
     * @param bean 待插入对象
     * @return
     * 
     */
    @Override
    public int insert(Measure bean)
    {
        String sql =
            "insert into t_measure (no, As_, Cr, Cd, Cu, Pb, Zn, Ni, Hg, Sn, Co, Ag, Sb, Ba, Mg, Ti, W, Al, Th, Sr, Cs ) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return dbutilsTemplate.update(sql,
            bean.getNo(),
            bean.getAs(),
            bean.getCr(),
            bean.getCd(),
            bean.getCu(),
            bean.getPb(),
            bean.getZn(),
            bean.getNi(),
            bean.getHg(),
            bean.getSn(),
            bean.getCo(),
            bean.getAg(),
            bean.getSb(),
            bean.getBa(),
            bean.getMg(),
            bean.getTi(),
            bean.getW(),
            bean.getAl(),
            bean.getTh(),
            bean.getSr(),
            bean.getCs());
    }
    
    /**
     * 增加记录(仅插入非空字段)
     * 
     * @param bean 待插入对象
     * @return
     * 
     */
    @Override
    public int insertSelective(Measure bean)
    {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<Object> params = new ArrayList<>();
        if (bean.getNo() != null)
        {
            columns.append(", no");
            values.append(", ?");
            params.add(bean.getNo());
        }
        if (bean.getAs() != null)
        {
            columns.append(", As_");
            values.append(", ?");
            params.add(bean.getAs());
        }
        if (bean.getCr() != null)
        {
            columns.append(", Cr");
            values.append(", ?");
            params.add(bean.getCr());
        }
        if (bean.getCd() != null)
        {
            columns.append(", Cd");
            values.append(", ?");
            params.add(bean.getCd());
        }
        if (bean.getCu() != null)
        {
            columns.append(", Cu");
            values.append(", ?");
            params.add(bean.getCu());
        }
        if (bean.getPb() != null)
        {
            columns.append(", Pb");
            values.append(", ?");
            params.add(bean.getPb());
        }
        if (bean.getZn() != null)
        {
            columns.append(", Zn");
            values.append(", ?");
            params.add(bean.getZn());
        }
        if (bean.getNi() != null)
        {
            columns.append(", Ni");
            values.append(", ?");
            params.add(bean.getNi());
        }
        if (bean.getHg() != null)
        {
            columns.append(", Hg");
            values.append(", ?");
            params.add(bean.getHg());
        }
        if (bean.getSn() != null)
        {
            columns.append(", Sn");
            values.append(", ?");
            params.add(bean.getSn());
        }
        if (bean.getCo() != null)
        {
            columns.append(", Co");
            values.append(", ?");
            params.add(bean.getCo());
        }
        if (bean.getAg() != null)
        {
            columns.append(", Ag");
            values.append(", ?");
            params.add(bean.getAg());
        }
        if (bean.getSb() != null)
        {
            columns.append(", Sb");
            values.append(", ?");
            params.add(bean.getSb());
        }
        if (bean.getBa() != null)
        {
            columns.append(", Ba");
            values.append(", ?");
            params.add(bean.getBa());
        }
        if (bean.getMg() != null)
        {
            columns.append(", Mg");
            values.append(", ?");
            params.add(bean.getMg());
        }
        if (bean.getTi() != null)
        {
            columns.append(", Ti");
            values.append(", ?");
            params.add(bean.getTi());
        }
        if (bean.getW() != null)
        {
            columns.append(", W");
            values.append(", ?");
            params.add(bean.getW());
        }
        if (bean.getAl() != null)
        {
            columns.append(", Al");
            values.append(", ?");
            params.add(bean.getAl());
        }
        if (bean.getTh() != null)
        {
            columns.append(", Th");
            values.append(", ?");
            params.add(bean.getTh());
        }
        if (bean.getSr() != null)
        {
            columns.append(", Sr");
            values.append(", ?");
            params.add(bean.getSr());
        }
        if (bean.getCs() != null)
        {
            columns.append(", Cs");
            values.append(", ?");
            params.add(bean.getCs());
        }
        StringBuilder sql = new StringBuilder("insert into t_measure (").append(columns.substring(1)).append(")");
        sql.append(" values(").append(values.substring(1)).append(")");
        return dbutilsTemplate.update(sql.toString(), params.toArray());
    }
    
    /**
     * 查询全部
     * 
     * @return
     * 
     */
    @Override
    public List<Measure> queryAll()
    {
        String sql = "select id, no, As_, Cr, Cd, Cu, Pb, Zn, Ni, Hg, Sn, Co, Ag, Sb, Ba, Mg, Ti, W, Al, Th, Sr, Cs from t_measure";
        return dbutilsTemplate.query(Measure.class, sql);
    }
    
    /**
     * 根据条件查询
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public List<Measure> queryByCriteria(Measure criteria)
    {
        StringBuilder sql = new StringBuilder("select id, no, As_, Cr, Cd, Cu, Pb, Zn, Ni, Hg, Sn, Co, Ag, Sb, Ba, Mg, Ti, W, Al, Th, Sr, Cs from t_measure ");
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        return dbutilsTemplate.query(Measure.class, sql.toString(), whereParams.toArray());
    }
    
    /**
     * 根据id查找数据
     * 
     * @param id 主键
     * @return
     * 
     */
    @Override
    public Measure queryById(Long id)
    {
        String sql = "select id, no, As_, Cr, Cd, Cu, Pb, Zn, Ni, Hg, Sn, Co, Ag, Sb, Ba, Mg, Ti, W, Al, Th, Sr, Cs from t_measure where id=?";
        return (Measure)dbutilsTemplate.queryFirst(Measure.class, sql, id);
    }
    
    /**
     * 根据条件分页查询
     * 
     * @param criteria 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     * 
     */
    @Override
    public PaginationSupport<Measure> queryForPagination(Measure criteria, int pageNo, int pageSize)
    {
        StringBuilder sql = new StringBuilder("select id, no, As_, Cr, Cd, Cu, Pb, Zn, Ni, Hg, Sn, Co, Ag, Sb, Ba, Mg, Ti, W, Al, Th, Sr, Cs from t_measure");
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        return dbutilsTemplate.queryForPagination(Measure.class, sql.toString(), pageNo, pageSize, whereParams.toArray());
    }
    
    /**
     * 根据条件查询数据条数
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public long queryTotal(Measure criteria)
    {
        StringBuilder sql = new StringBuilder("select count(1) from t_measure");
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        return dbutilsTemplate.queryForLong(sql.toString(), whereParams.toArray());
    }
    
    /**
     * 根据复杂条件更新全字段数据
     * 
     * @param bean 待更新对象
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public int updateByCriteria(Measure bean, Measure criteria)
    {
        StringBuilder sql = new StringBuilder("update t_measure set no=?, As_=?, Cr=?, Cd=?, Cu=?, Pb=?, Zn=?, Ni=?, Hg=?, Sn=?, Co=?, Ag=?, Sb=?, Ba=?, Mg=?, Ti=?, W=?, Al=?, Th=?, Sr=?, Cs=?");
        List<Object> params = new ArrayList<>();
        params.add(bean.getNo());
        params.add(bean.getAs());
        params.add(bean.getCr());
        params.add(bean.getCd());
        params.add(bean.getCu());
        params.add(bean.getPb());
        params.add(bean.getZn());
        params.add(bean.getNi());
        params.add(bean.getHg());
        params.add(bean.getSn());
        params.add(bean.getCo());
        params.add(bean.getAg());
        params.add(bean.getSb());
        params.add(bean.getBa());
        params.add(bean.getMg());
        params.add(bean.getTi());
        params.add(bean.getW());
        params.add(bean.getAl());
        params.add(bean.getTh());
        params.add(bean.getSr());
        params.add(bean.getCs());
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        params.addAll(whereParams);
        return dbutilsTemplate.update(sql.toString(), params.toArray());
    }
    
    /**
     * 根据复杂条件更新非空字段数据
     * 
     * @param bean 待更新对象
     * @param criteria 条件对象
     * @return
     * 
     */
    @Override
    public int updateByCriteriaSelective(Measure bean, Measure criteria)
    {
        StringBuilder sql = new StringBuilder("update t_measure set");
        StringBuilder columns = new StringBuilder();
        List<Object> params = new ArrayList<>();
        if (bean.getNo() != null)
        {
            columns.append(", no=?");
            params.add(bean.getNo());
        }
        if (bean.getAs() != null)
        {
            columns.append(", As_=?");
            params.add(bean.getAs());
        }
        if (bean.getCr() != null)
        {
            columns.append(", Cr=?");
            params.add(bean.getCr());
        }
        if (bean.getCd() != null)
        {
            columns.append(", Cd=?");
            params.add(bean.getCd());
        }
        if (bean.getCu() != null)
        {
            columns.append(", Cu=?");
            params.add(bean.getCu());
        }
        if (bean.getPb() != null)
        {
            columns.append(", Pb=?");
            params.add(bean.getPb());
        }
        if (bean.getZn() != null)
        {
            columns.append(", Zn=?");
            params.add(bean.getZn());
        }
        if (bean.getNi() != null)
        {
            columns.append(", Ni=?");
            params.add(bean.getNi());
        }
        if (bean.getHg() != null)
        {
            columns.append(", Hg=?");
            params.add(bean.getHg());
        }
        if (bean.getSn() != null)
        {
            columns.append(", Sn=?");
            params.add(bean.getSn());
        }
        if (bean.getCo() != null)
        {
            columns.append(", Co=?");
            params.add(bean.getCo());
        }
        if (bean.getAg() != null)
        {
            columns.append(", Ag=?");
            params.add(bean.getAg());
        }
        if (bean.getSb() != null)
        {
            columns.append(", Sb=?");
            params.add(bean.getSb());
        }
        if (bean.getBa() != null)
        {
            columns.append(", Ba=?");
            params.add(bean.getBa());
        }
        if (bean.getMg() != null)
        {
            columns.append(", Mg=?");
            params.add(bean.getMg());
        }
        if (bean.getTi() != null)
        {
            columns.append(", Ti=?");
            params.add(bean.getTi());
        }
        if (bean.getW() != null)
        {
            columns.append(", W=?");
            params.add(bean.getW());
        }
        if (bean.getAl() != null)
        {
            columns.append(", Al=?");
            params.add(bean.getAl());
        }
        if (bean.getTh() != null)
        {
            columns.append(", Th=?");
            params.add(bean.getTh());
        }
        if (bean.getSr() != null)
        {
            columns.append(", Sr=?");
            params.add(bean.getSr());
        }
        if (bean.getCs() != null)
        {
            columns.append(", Cs=?");
            params.add(bean.getCs());
        }
        sql.append(columns.substring(1));
        
        String whereClause = buildWhereClause(criteria);
        List<Object> whereParams = buildWhereParams(criteria);
        if (StringUtils.isNotEmpty(whereClause))
        {
            sql.append(" where ").append(whereClause);
        }
        params.addAll(whereParams);
        return dbutilsTemplate.update(sql.toString(), params.toArray());
    }
    
    /**
     * 根据id更新全部数据
     * 
     * @param bean 待更新对象
     * @return
     * 
     */
    @Override
    public int updateById(Measure bean)
    {
        String sql = "update t_measure set no=?, As_=?, Cr=?, Cd=?, Cu=?, Pb=?, Zn=?, Ni=?, Hg=?, Sn=?, Co=?, Ag=?, Sb=?, Ba=?, Mg=?, Ti=?, W=?, Al=?, Th=?, Sr=?, Cs=? where id=?";
        return dbutilsTemplate.update(sql,
            bean.getNo(),
            bean.getAs(),
            bean.getCr(),
            bean.getCd(),
            bean.getCu(),
            bean.getPb(),
            bean.getZn(),
            bean.getNi(),
            bean.getHg(),
            bean.getSn(),
            bean.getCo(),
            bean.getAg(),
            bean.getSb(),
            bean.getBa(),
            bean.getMg(),
            bean.getTi(),
            bean.getW(),
            bean.getAl(),
            bean.getTh(),
            bean.getSr(),
            bean.getCs(),
            bean.getId());
    }
    
    /**
     * 根据id更新非空字段数据
     * 
     * @param bean 待更新对象
     * @return
     * 
     */
    @Override
    public int updateByIdSelective(Measure bean)
    {
        StringBuilder sql = new StringBuilder("update t_measure set");
        StringBuilder columns = new StringBuilder();
        List<Object> params = new ArrayList<>();
        if (bean.getNo() != null)
        {
            columns.append(", no=?");
            params.add(bean.getNo());
        }
        if (bean.getAs() != null)
        {
            columns.append(", As_=?");
            params.add(bean.getAs());
        }
        if (bean.getCr() != null)
        {
            columns.append(", Cr=?");
            params.add(bean.getCr());
        }
        if (bean.getCd() != null)
        {
            columns.append(", Cd=?");
            params.add(bean.getCd());
        }
        if (bean.getCu() != null)
        {
            columns.append(", Cu=?");
            params.add(bean.getCu());
        }
        if (bean.getPb() != null)
        {
            columns.append(", Pb=?");
            params.add(bean.getPb());
        }
        if (bean.getZn() != null)
        {
            columns.append(", Zn=?");
            params.add(bean.getZn());
        }
        if (bean.getNi() != null)
        {
            columns.append(", Ni=?");
            params.add(bean.getNi());
        }
        if (bean.getHg() != null)
        {
            columns.append(", Hg=?");
            params.add(bean.getHg());
        }
        if (bean.getSn() != null)
        {
            columns.append(", Sn=?");
            params.add(bean.getSn());
        }
        if (bean.getCo() != null)
        {
            columns.append(", Co=?");
            params.add(bean.getCo());
        }
        if (bean.getAg() != null)
        {
            columns.append(", Ag=?");
            params.add(bean.getAg());
        }
        if (bean.getSb() != null)
        {
            columns.append(", Sb=?");
            params.add(bean.getSb());
        }
        if (bean.getBa() != null)
        {
            columns.append(", Ba=?");
            params.add(bean.getBa());
        }
        if (bean.getMg() != null)
        {
            columns.append(", Mg=?");
            params.add(bean.getMg());
        }
        if (bean.getTi() != null)
        {
            columns.append(", Ti=?");
            params.add(bean.getTi());
        }
        if (bean.getW() != null)
        {
            columns.append(", W=?");
            params.add(bean.getW());
        }
        if (bean.getAl() != null)
        {
            columns.append(", Al=?");
            params.add(bean.getAl());
        }
        if (bean.getTh() != null)
        {
            columns.append(", Th=?");
            params.add(bean.getTh());
        }
        if (bean.getSr() != null)
        {
            columns.append(", Sr=?");
            params.add(bean.getSr());
        }
        if (bean.getCs() != null)
        {
            columns.append(", Cs=?");
            params.add(bean.getCs());
        }
        sql.append(columns.substring(1));
        sql.append(" where id=?");
        params.add(bean.getId());
        return dbutilsTemplate.update(sql.toString(), params.toArray());
    }
    
}