package com.tecsun.sisp.adapter.modules.fairJob.entity.response;

import java.util.List;

/**所有城市类
 * Created by Administrator on 2017/11/23 0023.
 */
public class AllCity {

    private List<CityVo> redCityVo; //热门城市
    private List<CityVo> allCitys;         //全国城市
    public List<CityVo> getRedCityVo() {
        return redCityVo;
    }

    public void setRedCityVo(List<CityVo> redCityVo) {
        this.redCityVo = redCityVo;
    }

    public List<CityVo> getAllCitys() {
        return allCitys;
    }

    public void setAllCitys(List<CityVo> allCitys) {
        this.allCitys = allCitys;
    }
}
