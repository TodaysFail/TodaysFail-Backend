package com.todaysfail.config.mysql;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Profile({"prod", "dev"})
@RequiredArgsConstructor
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    private static final String MASTER = "master";
    private static final String SLAVE = "slave";

    private final ReadOnlyDataSourceCycle<String> readOnlyDataSourceCycle =
            new ReadOnlyDataSourceCycle<>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        List<String> readOnlyDataSourceLookupKeys =
                targetDataSources.keySet().stream()
                        .map(String::valueOf)
                        .filter(lookupKey -> lookupKey.contains(SLAVE))
                        .toList();
        readOnlyDataSourceCycle.setReadOnlyDataSourceLookupKeys(readOnlyDataSourceLookupKeys);
    }

    @Override
    public Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                ? readOnlyDataSourceCycle.getReadOnlyDataSourceLookupKey()
                : MASTER;
    }
}
