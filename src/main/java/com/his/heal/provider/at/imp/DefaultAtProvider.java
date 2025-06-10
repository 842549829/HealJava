package com.his.heal.provider.at.imp;

import com.his.heal.provider.at.contract.AtProvider;

public class DefaultAtProvider implements AtProvider {
    private final String atName;

    public DefaultAtProvider(String atName) {
        this.atName = atName;
    }

    @Override
    public String getAtName() {
        return atName;
    }
}
