package xyz.frame.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author shisp
 * @date 2018-8-07 17:35:26
 */
//@Component
public class HbaseRepository {
    private static final Logger logger = LoggerFactory.getLogger(HbaseRepository.class);

    @Resource(name = "hbaseConnection")
    private Connection conn;

    
    /**
     * 通过表名和rowkey获取一行数据
     * 
     * @param query
     *            query.tableName query.rowkey
     * @return
     */
    public Map<String, String> findOneByTableNameAndRowKey2(HQueryParam query) {
        if (StringUtils.isBlank(query.getTableName()) || StringUtils.isBlank(query.getRowkey())) {
            return Collections.emptyMap();
        }
        Map<String, String> resultMap = new HashMap<>();
        try (Table table = conn.getTable(TableName.valueOf(query.getTableName()))){
            Get get = new Get(Bytes.toBytes(query.getRowkey()));
            Result result = table.get(get);
            // 方式一：
            //使用cell获取result里面的数据
            resultMap = getResultMap(result.cellScanner());
        } catch (IOException e) {
            logger.error("hbase findOneByTableNameAndRowKey2 error,query={}", query, e);
        }
        return resultMap;        
        // 方式二：
        /*List<Cell> ceList = result.listCells();
        if (ceList != null && !ceList.isEmpty()) {
            for (Cell cell : ceList) {
                String row = Bytes.toString(cell.getRowArray(),cell.getRowOffset(),cell.getRowLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                String qualify = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                map.put(qualify, value);
                map.put(HbaseEnum.ROWKEY.getName(), row);
            }
        }*/
    }

    public List<Map<String, String>> findListByScanColumn2(HQueryParam query) {
        if (StringUtils.isBlank(query.getTableName()) || null == query.getScan()) {
            return Collections.emptyList();
        }
        List<Map<String, String>> list = new ArrayList<>();
        try (Table table = conn.getTable(TableName.valueOf(query.getTableName()))){
            ResultScanner scanner = table.getScanner(query.getScan());
            for (Result result : scanner) {
                list.add(getResultMap(result.cellScanner()));
            }
        } catch (IOException e) {
            logger.error("hbase findOneByTableNameAndRowKey2 error,query={}", query, e);
        }
        return list;
    }

    /**
     * cellScanner 中读取数据
     * 
     * @param cellScanner
     * @return
     * @throws IOException
     */
    private Map<String, String> getResultMap(CellScanner cellScanner) throws IOException {
        Map<String, String> map = new HashMap<>();
        while (cellScanner.advance()) {
            Cell cell = cellScanner.current();
            // 使用 CellUtil工具类，从cell中把数据获取出来
             String famliy = Bytes.toString(CellUtil.cloneFamily(cell));
            String qualify = Bytes.toString(CellUtil.cloneQualifier(cell));
            String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            map.put(qualify, value);
            map.put(HbaseEnum.ROWKEY.getName(), rowkey);
            map.put(HbaseEnum.FAMILY.getName(), famliy);
        }
        return map;
    }

}
