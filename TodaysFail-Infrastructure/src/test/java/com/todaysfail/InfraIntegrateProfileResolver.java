package com.todaysfail;

import org.springframework.test.context.ActiveProfilesResolver;

public class InfraIntegrateProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> testClass) {
        return new String[] {"test", "infrastructure"};
    }
}
