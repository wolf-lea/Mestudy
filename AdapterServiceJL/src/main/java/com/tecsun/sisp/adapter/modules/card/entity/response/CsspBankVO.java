package com.tecsun.sisp.adapter.modules.card.entity.response;

import java.util.List;

public class CsspBankVO {
    private String name;

    private List<BankDataVO> bank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BankDataVO> getBank() {
        return bank;
    }

    public void setBank(List<BankDataVO> bank) {
        this.bank = bank;
    }
}
