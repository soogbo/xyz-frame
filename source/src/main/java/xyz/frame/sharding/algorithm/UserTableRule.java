package xyz.frame.sharding.algorithm;

import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import xyz.frame.pojo.po.UserSharding;

/**
 * 用户表分表规则
 */
public class UserTableRule extends TableRuleConfiguration {

    public UserTableRule(String logicDataSource, String inline) {
        this.setLogicTable(UserSharding.TABLE_NAME);
        this.setActualDataNodes(String.format("%s.%s_${%s}", logicDataSource, UserSharding.TABLE_NAME, inline));
        this.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration(UserSharding.SHARDING_COLUMN, UserPreciseShardingAlgorithm.class.getName()));
        this.setKeyGeneratorColumnName(UserSharding.GENERATE_COLUMN);
    }
    
}
