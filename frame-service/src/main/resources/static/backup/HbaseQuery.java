package com.hj.bi.dao.data.hbase;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author shisp
 * @date 2018-8-07 17:35:26
 */
@Component
public class HbaseQuery {

    @Resource(name = "hbaseTemplate")
    private HbaseTemplate hbaseTemplate;

    /**
     * 通过表名和rowkey获取一行数据
     * 
     * @param query
     *            query.tableName query.rowkey
     * @return
     */
    public Map<String, Object> findOneByTableNameAndRowKey(HQueryParam query) {
        if (StringUtils.isBlank(query.getTableName()) || StringUtils.isBlank(query.getRowkey())) {
            return Collections.emptyMap();
        }
        return hbaseTemplate.get(query.getTableName(), query.getRowkey(), new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                Map<String, Object> map = new HashMap<>();
                if (ceList != null && !ceList.isEmpty()) {
                    for (Cell cell : ceList) {
                        // String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(),
                        // cell.getFamilyLength()); // 族
                        String quali = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                        String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                        map.put(quali, value);
                    }
                }
                return map;
            }
        });
    }

    /**
     * 通过表名 rowkey 列族 列 获取一个数据
     * 
     * @param query
     *            query.tableName query.rowKey query.familyName query.qualifier
     * @return
     */
    public String findValueByParams(HQueryParam query) {
        if (StringUtils.isBlank(query.getTableName()) || StringUtils.isBlank(query.getRowkey()) || StringUtils.isBlank(query.getFamilyName())
                || StringUtils.isBlank(query.getQualifier())) {
            return "";
        }
        return hbaseTemplate.get(query.getTableName(), query.getRowkey(), query.getFamilyName(), query.getQualifier(), new RowMapper<String>() {
            @Override
            public String mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                String res = "";
                if (ceList != null && !ceList.isEmpty()) {
                    for (Cell cell : ceList) {
                        res = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                    }
                }
                return res;
            }
        });
    }

    /**
     * 通过表名，开始行键和结束行键获取数据
     * 
     * @param query
     *            query.tableName query.startRow query.stopRow
     * @return
     */
    public List<Map<String, Object>> findListByTableNameAndRows(HQueryParam query) {
        String startRow = query.getStartRow();
        String stopRow = query.getStopRow();
        if (StringUtils.isBlank(query.getTableName())) {
            return Collections.emptyList();
        }
        Scan scan = new Scan();
        if (startRow == null) {
            startRow = "";
        }
        if (stopRow == null) {
            stopRow = "";
        }
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(stopRow));
        /*
         * PageFilter filter = new PageFilter(5); scan.setFilter(filter); //分页过滤
         */
        // RowFilter rowFilter = new RowFilter(CompareOp.EQUAL, new
        // BinaryPrefixComparator("row01".getBytes())); //filter
        // scan.setFilter(rowFilter); //filter

        return hbaseTemplate.find(query.getTableName(), scan, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                Map<String, Object> map = new HashMap<>();
                String row = "";
                if (ceList != null && !ceList.isEmpty()) {
                    for (Cell cell : ceList) {
                        row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                        String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                        // String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(),
                        // cell.getFamilyLength()); //获取family name
                        String quali = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                        map.put(quali, value);
                    }
                    map.put("row", row);
                }
                return map;
            }
        });
    }

    /**
     * 通过表名，过滤器，获取数据
     * 
     * @param query
     *            query.tableName query.scan
     * @return
     */
    public List<Map<String, Object>> findListByScanColumn(HQueryParam query) {
        if (StringUtils.isBlank(query.getTableName()) || null == query.getScan()) {
            return Collections.emptyList();
        }
        return hbaseTemplate.find(query.getTableName(), query.getScan(), new RowMapper<Map<String, Object>>() {

            @Override
            public Map<String, Object> mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                
                Map<String, Object> map = new HashMap<>();
                if (ceList != null && !ceList.isEmpty()) {
                    for (Cell cell : ceList) {
                        String row = Bytes.toString(cell.getRowArray(),cell.getRowOffset(),cell.getRowLength());
                        String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                        String quali = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                        map.put(quali, value);
                        map.put("row", row);
                    }
                }
                return map;
            }
        });
    }
}
