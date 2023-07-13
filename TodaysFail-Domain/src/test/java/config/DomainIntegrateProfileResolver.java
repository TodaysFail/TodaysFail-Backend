package config;

import org.springframework.test.context.ActiveProfilesResolver;

public class DomainIntegrateProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> testClass) {
        return new String[] {"domain", "infrastructure"};
    }
}
