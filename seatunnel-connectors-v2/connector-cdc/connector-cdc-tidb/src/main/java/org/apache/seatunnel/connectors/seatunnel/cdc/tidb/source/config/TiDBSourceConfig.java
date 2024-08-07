package org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source.config;

import org.apache.seatunnel.connectors.cdc.base.config.SourceConfig;
import org.apache.seatunnel.connectors.cdc.base.config.StartupConfig;
import org.apache.seatunnel.connectors.cdc.base.config.StopConfig;

public class TiDBSourceConfig implements SourceConfig {
    @Override
    public StartupConfig getStartupConfig() {
        return null;
    }

    @Override
    public StopConfig getStopConfig() {
        return null;
    }

    @Override
    public int getSplitSize() {
        return 0;
    }

    @Override
    public boolean isExactlyOnce() {
        return false;
    }
}
