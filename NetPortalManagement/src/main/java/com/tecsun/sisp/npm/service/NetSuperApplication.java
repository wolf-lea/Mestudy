package com.tecsun.sisp.npm.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import bee.cloud.engine.db.annotation.Application;
import bee.cloud.service.app.SuperApplication;

@Application(name = "sisp_source", alias = "sisp_source", dsn = "sisp_source", entityPkg = {"com.tecsun.sb.entity", "com.tecsun.sb.entity.common", "bee.cloud.engine.entity", "bee.cloud.engine.dog.entity", "bee.cloud.entity", "com.tecsun.sisp.npm.bean"})
public class NetSuperApplication extends SuperApplication {

    public NetSuperApplication(@Context HttpServletRequest request, @Context UriInfo ui) throws SQLException {
        super(request);
        // TODO Auto-generated constructor stub
    }


}
