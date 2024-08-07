package org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.utils;

import org.apache.seatunnel.shade.com.google.common.collect.Lists;

import org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.config.TiDBSourceConfig;

import org.tikv.common.TiSession;
import org.tikv.common.meta.TiDBInfo;
import org.tikv.common.meta.TiTableInfo;

import io.debezium.relational.TableId;

import java.util.List;
import java.util.stream.Collectors;

public class TableDiscoveryUtils {

    public static List<TableId> listTables(TiSession tiSession, TiDBSourceConfig sourceConfig) {
        List<TiDBInfo> databases =
                tiSession.getCatalog().listDatabases().stream()
                        .filter(
                                tiDBInfo ->
                                        tiDBInfo.getName().equals(sourceConfig.getDatabaseName()))
                        .collect(Collectors.toList());
        List<TableId> tableIds = Lists.newArrayList();
        for (TiDBInfo tiDBInfo : databases) {
            for (TiTableInfo tiTableInfo : tiDBInfo.getTables()) {
                if (tiTableInfo.getName().equals(sourceConfig.getTableName())) {
                    tableIds.add(new TableId(tiDBInfo.getName(), null, tiTableInfo.getName()));
                }
            }
        }
        return tableIds;
    }
}
