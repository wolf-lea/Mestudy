package com.tecsun.sisp.npm.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.RowSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;
import com.tecsun.sisp.npm.bean.WebConnectionVO;
import com.tecsun.sisp.npm.util.PublicMethod;
import com.tecsun.sisp.npm.util.StaticBean;

import bee.cloud.engine.db.DataType;
import bee.cloud.engine.db.cnd.CndFactory;
import bee.cloud.engine.db.core.Cnd;
import bee.cloud.engine.db.siud.Select;

@Path("/npmanagement/web")
public class WebConnectionApplication extends NetSuperApplication {

    public WebConnectionApplication(@Context HttpServletRequest request,@Context HttpServletResponse response, @Context UriInfo ui) throws SQLException {
        super(request, ui);
    }

    /**
     * 按时间段查询网办访问量
     * @param startDate
     * @param endDate
     * @param searchType
     * @return
     */
    @GET
    @Path("/getWebInfo")
    public String getWebInfo(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate,@QueryParam("searchType") String searchType) {
        WebConnectionVO vo = new WebConnectionVO(doe);
      //1.年，2.月(2015-02)，3.日
        if("1".equals(searchType)){
        	startDate=PublicMethod.formatDate(PublicMethod.getYearFirst(Integer.parseInt(startDate)),"yyyy-MM-dd")+" 00:00:00";
        	endDate=PublicMethod.formatDate(PublicMethod.getYearLast(Integer.parseInt(endDate)),"yyyy-MM-dd")+" 23:59:59";
        }else if("2".equals(searchType)){
        	startDate=startDate+"-01 00:00:00";
            endDate=PublicMethod.formatDate(PublicMethod.getLastDayOfMonth(Integer.parseInt(endDate.split("-")[0]),Integer.parseInt(endDate.split("-")[1])),"yyyy-MM-dd")+" 23:59:59";
        }else if("3".equals(searchType)){
        	startDate=startDate+" 00:00:00";
            endDate=endDate+" 23:59:59";
        }
        StringBuffer sql = new StringBuffer();
        List<Map> list = new ArrayList<Map>();
       try {
            Cnd cnd = CndFactory.getCnd(DataType.ORACLE, "1", Cnd.EQ, "1");
            if(searchType.equals("1")){
            	sql.append("select to_char(create_time,'yyyy') as create_time,count(1) as count FROM T_WEB_CONNECTION_INFO where ");
            	sql.append("(create_time>= to_date('"+startDate+"','yyyy-MM-dd HH24:MI:SS')) AND ");
            	sql.append("(create_time< to_date('"+endDate+"','yyyy-MM-dd HH24:MI:SS')) ");
            	sql.append("group by to_char(create_time,'yyyy') order by create_time asc");
            }else if(searchType.equals("2")){
            	sql.append("select to_char(create_time,'yyyy-MM') as create_time,count(1) as count FROM T_WEB_CONNECTION_INFO where ");
            	sql.append("(create_time>= to_date('"+startDate+"','yyyy-MM-dd HH24:MI:SS')) AND ");
            	sql.append("(create_time< to_date('"+endDate+"','yyyy-MM-dd HH24:MI:SS')) ");
            	sql.append("group by to_char(create_time,'yyyy-MM')  order by create_time asc");          
            }else{
            	sql.append("select to_char(create_time,'yyyy-MM-dd') as create_time,count(1) as count FROM T_WEB_CONNECTION_INFO where ");
            	sql.append("(create_time>= to_date('"+startDate+"','yyyy-MM-dd HH24:MI:SS')) AND ");
            	sql.append("(create_time< to_date('"+endDate+"','yyyy-MM-dd HH24:MI:SS')) ");
            	sql.append("group by to_char(create_time,'yyyy-MM-dd')  order by create_time asc");
            }
            System.out.println("============="+sql.toString());
           RowSet rs = doe.query(sql.toString());
           
           while(rs.next()){
        	   String create_time = rs.getString("create_time");
        	   String count = rs.getString("count");
        	   Map<String, String> map = new HashMap<String, String>();
        	   map.put("name", create_time);
        	   map.put("group", "网站访问量");
        	   map.put("value", count);
        	   list.add(map);
           }
            return ok(list, null);
        } catch (SQLException e) {
        	logger.error("查询网办访问量出差",e);
            return error("查询出错");
        }
    }
}
