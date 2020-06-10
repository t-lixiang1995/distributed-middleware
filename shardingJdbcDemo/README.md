Sharding-JDBC应用实例
=================================
#Sharding-JDBC认为对于分片策略存有两种维度：
* 数据源分片策略（DatabaseShardingStrategy）：数据被分配的目标数据源
* 表分片策略（TableShardingStrategy）：数据被分配的目标表
两种分片策略API完全相同，但是表分片策略是依赖于数据源分片策略的（即：先分库然后才有分表）
​

#分片策略
* NoneShardingStrategy
* StandardShardingStrategy
* ComplexShardingStrategy
* HintShardingStrategy
* InlineShardingStrategy

#分片算法
* io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm
* io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm
* io.shardingsphere.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm
* io.shardingsphere.api.algorithm.sharding.hint.HintShardingAlgorithm