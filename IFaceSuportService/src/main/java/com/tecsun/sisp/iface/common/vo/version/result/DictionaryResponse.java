package com.tecsun.sisp.iface.common.vo.version.result;

/**
 * ClassName:DictionaryResponse
 * Description:字典表
 * Author:张清洁
 * CreateTime:2015年12月21日 11时：39分
 */
public class DictionaryResponse {

    private String dictId;
    private String dictName;

    public DictionaryResponse() {
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
