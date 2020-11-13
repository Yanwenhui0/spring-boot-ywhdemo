package org.redisson.config;

import org.redisson.config.*;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/9
 */
public class ConfigExt extends Config {
    public ConfigExt() {
    }

    @Override
    public ClusterServersConfig getClusterServersConfig() {
        return super.getClusterServersConfig();
    }

    @Override
    public void setClusterServersConfig(ClusterServersConfig clusterServersConfig) {
        super.setClusterServersConfig(clusterServersConfig);
    }

    @Override
    public void setSingleServerConfig(SingleServerConfig singleConnectionConfig) {
        super.setSingleServerConfig(singleConnectionConfig);
    }

    @Override
    public SentinelServersConfig getSentinelServersConfig() {
        return super.getSentinelServersConfig();
    }

    @Override
    public void setSentinelServersConfig(SentinelServersConfig sentinelConnectionConfig) {
        super.setSentinelServersConfig(sentinelConnectionConfig);
    }

    @Override
    public MasterSlaveServersConfig getMasterSlaveServersConfig() {
        return super.getMasterSlaveServersConfig();
    }

    @Override
    public void setMasterSlaveServersConfig(MasterSlaveServersConfig masterSlaveConnectionConfig) {
        super.setMasterSlaveServersConfig(masterSlaveConnectionConfig);
    }

    @Override
    public ReplicatedServersConfig getReplicatedServersConfig() {
        return super.getReplicatedServersConfig();
    }

    @Override
    public void setReplicatedServersConfig(ReplicatedServersConfig replicatedServersConfig) {
        super.setReplicatedServersConfig(replicatedServersConfig);
    }
}