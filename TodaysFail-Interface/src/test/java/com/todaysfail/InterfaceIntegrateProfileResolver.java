package com.todaysfail;

import org.springframework.test.context.ActiveProfilesResolver;

public class InterfaceIntegrateProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> testClass) {
        return new String[] {"common", "interface", "domain", "infrastructure"};
    }
}
