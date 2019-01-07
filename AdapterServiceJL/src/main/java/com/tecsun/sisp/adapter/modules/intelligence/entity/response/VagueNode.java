package com.tecsun.sisp.adapter.modules.intelligence.entity.response;

import java.util.List;

/**捷通华声-多选答案
 * Created by danmeng on 2017/8/29.
 */
public class VagueNode {
    private List<String> vagueList;//问题列表
    private List<ItemMsg> itemList;//问题列表详细
    private String promptVagueMsg;//前置提示语
    private String endVagueMsg;//后置提示语

    public List<String> getVagueList() {
        return vagueList;
    }

    public void setVagueList(List<String> vagueList) {
        this.vagueList = vagueList;
    }

    public List<ItemMsg> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemMsg> itemList) {
        this.itemList = itemList;
    }

    public String getPromptVagueMsg() {
        return promptVagueMsg;
    }

    public void setPromptVagueMsg(String promptVagueMsg) {
        this.promptVagueMsg = promptVagueMsg;
    }

    public String getEndVagueMsg() {
        return endVagueMsg;
    }

    public void setEndVagueMsg(String endVagueMsg) {
        this.endVagueMsg = endVagueMsg;
    }
}
