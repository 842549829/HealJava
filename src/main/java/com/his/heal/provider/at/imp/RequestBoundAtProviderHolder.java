package com.his.heal.provider.at.imp;

import com.his.heal.provider.at.contract.AtProvider;
import com.his.heal.provider.at.contract.AtProviderHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class RequestBoundAtProviderHolder implements AtProviderHolder {
    public AtProvider getAtProvider() {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            var request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return (AtProvider) request.getAttribute("atProvider");
        }
        return null;
    }
}
