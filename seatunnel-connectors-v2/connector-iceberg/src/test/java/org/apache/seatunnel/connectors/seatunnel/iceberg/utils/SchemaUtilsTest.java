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

package org.apache.seatunnel.connectors.seatunnel.iceberg.utils;

class SchemaUtilsTest {

    //    @Test
    //    void testToIcebergSchemaWithPk() {
    //        String[] fieldNames = new String[] {"id", "name", "description", "weight"};
    //        SeaTunnelDataType<?>[] dataTypes =
    //                new SeaTunnelDataType[] {
    //                    BasicType.LONG_TYPE,
    //                    BasicType.STRING_TYPE,
    //                    BasicType.STRING_TYPE,
    //                    BasicType.STRING_TYPE
    //                };
    //        SeaTunnelRowType rowType = new SeaTunnelRowType(fieldNames, dataTypes);
    //        List<String> pks = Arrays.asList("id", "name");
    //        ReadonlyConfig readonlyConfig =
    //                ReadonlyConfig.fromMap(
    //                        new HashMap<String, Object>() {
    //                            {
    //                                put(SinkConfig.TABLE_PRIMARY_KEYS.key(), String.join(",",
    // pks));
    //                            }
    //                        });
    //        Schema schema = SchemaUtils.toIcebergSchema(rowType);
    //        Assertions.assertNotNull(schema);
    //        Assertions.assertEquals(fieldNames.length, schema.columns().size());
    //        for (Types.NestedField column : schema.columns()) {
    //            Assertions.assertEquals(fieldNames[column.fieldId() - 1], column.name());
    //            if (pks.contains(column.name())) {
    //                Assertions.assertEquals(Boolean.TRUE, column.isRequired());
    //            } else {
    //                Assertions.assertEquals(Boolean.FALSE, column.isRequired());
    //            }
    //        }
    //        Assertions.assertNotNull(schema.identifierFieldIds());
    //        Assertions.assertEquals(pks.size(), schema.identifierFieldIds().size());
    //        for (Integer identifierFieldId : schema.identifierFieldIds()) {
    //            Assertions.assertEquals(
    //                    pks.get(identifierFieldId - 1), fieldNames[identifierFieldId - 1]);
    //        }
    //    }
    //
    //    @Test
    //    void testToIcebergSchemaWithoutPk() {
    //        String[] fieldNames = new String[] {"id", "name", "description", "weight"};
    //        SeaTunnelDataType<?>[] dataTypes =
    //                new SeaTunnelDataType[] {
    //                    BasicType.LONG_TYPE,
    //                    BasicType.STRING_TYPE,
    //                    BasicType.STRING_TYPE,
    //                    BasicType.STRING_TYPE
    //                };
    //        SeaTunnelRowType rowType = new SeaTunnelRowType(fieldNames, dataTypes);
    //        ReadonlyConfig readonlyConfig =
    //                ReadonlyConfig.fromMap(
    //                        new HashMap<String, Object>() {
    //                            {
    //                            }
    //                        });
    //        Schema schema = SchemaUtils.toIcebergSchema(rowType);
    //        Assertions.assertNotNull(schema);
    //        Assertions.assertEquals(fieldNames.length, schema.columns().size());
    //        for (Types.NestedField column : schema.columns()) {
    //            Assertions.assertEquals(fieldNames[column.fieldId() - 1], column.name());
    //            Assertions.assertEquals(Boolean.FALSE, column.isRequired());
    //        }
    //    }
}
