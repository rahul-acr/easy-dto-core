package com.easydto.domain;

import com.easydto.annotation.DtoProperty;

public class Account {

    private final String accountName;

    private final Long accountId;

    public Account(String accountName, Long accountId) {
        this.accountName = accountName;
        this.accountId = accountId;
    }

    @DtoProperty
    public String name() {
        return accountName;
    }

    @DtoProperty
    public Long accountId() {
        return accountId;
    }
}
