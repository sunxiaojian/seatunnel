/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.connectors.seatunnel.cdc.tidb.source;

import org.apache.seatunnel.api.configuration.Options;
import org.apache.seatunnel.api.configuration.ReadonlyConfig;
import org.apache.seatunnel.api.configuration.SingleChoiceOption;
import org.apache.seatunnel.connectors.cdc.base.option.SourceOptions;
import org.apache.seatunnel.connectors.cdc.base.option.StartupMode;
import org.apache.seatunnel.connectors.cdc.base.option.StopMode;

import org.tikv.common.ConfigUtils;
import org.tikv.common.TiConfiguration;

import java.util.Arrays;

/** TiDB source options */
public class TiDBSourceOptions {

    public static final SingleChoiceOption<String> DATABASE_NAME =
            (SingleChoiceOption<String>)
                    Options.key("database-name")
                            .stringType()
                            .noDefaultValue()
                            .withDescription("Database name of the TiDB server to monitor.");

    public static final SingleChoiceOption<String> TABLE_NAME =
            (SingleChoiceOption<String>)
                    Options.key("table-name")
                            .stringType()
                            .noDefaultValue()
                            .withDescription("Table name of the database to monitor.");

    public static final SingleChoiceOption<StartupMode> STARTUP_MODE =
            Options.key(SourceOptions.STARTUP_MODE_KEY)
                    .singleChoice(
                            StartupMode.class,
                            Arrays.asList(StartupMode.INITIAL, StartupMode.TIMESTAMP))
                    .defaultValue(StartupMode.INITIAL)
                    .withDescription(
                            "Optional startup mode for CDC source, valid enumerations are "
                                    + "\"initial\", \"earliest\", \"latest\", \"timestamp\"\n or \"specific\"");

    public static final SingleChoiceOption<StopMode> STOP_MODE =
            (SingleChoiceOption)
                    Options.key(SourceOptions.STOP_MODE_KEY)
                            .singleChoice(
                                    StopMode.class,
                                    Arrays.asList(
                                            StopMode.LATEST, StopMode.SPECIFIC, StopMode.NEVER))
                            .defaultValue(StopMode.NEVER)
                            .withDescription(
                                    "Optional stop mode for CDC source, valid enumerations are "
                                            + "\"never\", \"latest\" or \"specific\"");

    public static final SingleChoiceOption<String> PD_ADDRESSES =
            (SingleChoiceOption<String>)
                    Options.key("pd-addresses")
                            .stringType()
                            .noDefaultValue()
                            .withDescription("TiKV cluster's PD address");

    public static final SingleChoiceOption<Long> TIKV_GRPC_TIMEOUT =
            (SingleChoiceOption<Long>)
                    Options.key(ConfigUtils.TIKV_GRPC_TIMEOUT)
                            .longType()
                            .noDefaultValue()
                            .withDescription("TiKV GRPC timeout in ms");

    public static final SingleChoiceOption<Long> TIKV_GRPC_SCAN_TIMEOUT =
            (SingleChoiceOption<Long>)
                    Options.key(ConfigUtils.TIKV_GRPC_SCAN_TIMEOUT)
                            .longType()
                            .noDefaultValue()
                            .withDescription("TiKV GRPC scan timeout in ms");

    public static final SingleChoiceOption<Integer> TIKV_BATCH_GET_CONCURRENCY =
            (SingleChoiceOption<Integer>)
                    Options.key(ConfigUtils.TIKV_BATCH_GET_CONCURRENCY)
                            .intType()
                            .noDefaultValue()
                            .withDescription("TiKV GRPC batch get concurrency");

    public static final SingleChoiceOption<Integer> TIKV_BATCH_SCAN_CONCURRENCY =
            (SingleChoiceOption<Integer>)
                    Options.key(ConfigUtils.TIKV_BATCH_SCAN_CONCURRENCY)
                            .intType()
                            .noDefaultValue()
                            .withDescription("TiKV GRPC batch scan concurrency");

    public static TiConfiguration getTiConfiguration(
            final String pdAddrsStr, final ReadonlyConfig configuration) {

        final TiConfiguration tiConf = TiConfiguration.createDefault(pdAddrsStr);
        configuration.getOptional(TIKV_GRPC_TIMEOUT).ifPresent(tiConf::setTimeout);
        configuration.getOptional(TIKV_GRPC_SCAN_TIMEOUT).ifPresent(tiConf::setScanTimeout);
        configuration
                .getOptional(TIKV_BATCH_GET_CONCURRENCY)
                .ifPresent(tiConf::setBatchGetConcurrency);

        configuration
                .getOptional(TIKV_BATCH_SCAN_CONCURRENCY)
                .ifPresent(tiConf::setBatchScanConcurrency);
        return tiConf;
    }
}
