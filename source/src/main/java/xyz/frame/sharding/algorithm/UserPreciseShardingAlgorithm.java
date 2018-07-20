package xyz.frame.sharding.algorithm;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * 用户表分表配置策略
 * 
 * @author shisp
 * @date 2018-7-18 14:44:13
 */
public class UserPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long userId = shardingValue.getValue();
        // userId取模分表
        String calculateTableName = shardingValue.getLogicTableName().concat("_").concat(String.valueOf(userId % 3));
        if (availableTargetNames.contains(calculateTableName)) {
            return calculateTableName;
        }
        return shardingValue.getLogicTableName().concat("_").concat("0");
    }
}
