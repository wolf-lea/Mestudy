package com.tecsun.sisp.iface.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.tecsun.sisp.iface.common.vo.so.po.endow.YljfjsxxPO;

public class YljfjsxxComparator implements Comparator<YljfjsxxPO>{

	@Override
	public int compare(YljfjsxxPO po1, YljfjsxxPO po2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(po1.getKSNY());
			d2 = sdf.parse(po2.getKSNY());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d2.compareTo(d1);
	}

}
