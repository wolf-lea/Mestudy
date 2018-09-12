package com.tecsun.sisp.fakamanagement.modules.task;

import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.service.impl.store.BatchManageServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.store.DispatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @author: liang
 * @date 2018/2/8 20:15
 **/
public class BatchDownloadThread implements Runnable{
    private String userid;
    private Integer[] ids;
    private BatchManageServiceImpl batchManageService;


    public BatchDownloadThread(String userid,Integer[] ids,BatchManageServiceImpl batchManageService){
        this.userid=userid;
        this.ids=ids;
        this.batchManageService=batchManageService;
    }

    public void run() {
        Result r=new Result();
        for(int i=0;i<ids.length;i++){
            r=batchManageService.handleBatchInfo(ids,userid,i);
            //更新批次下载状态--成功或者失败
            batchManageService.updateStatusByResult(r,userid,ids[i]);
        }
    }

}
