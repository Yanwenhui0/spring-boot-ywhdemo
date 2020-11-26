# Redisson more

> 动态配置 redisson 多数据源

例如：
```yaml
configs:
  com:
    yanwenhui:
      redis:
        redisson:
          dynamic:
            enable: true
          oneRedisson:
            singleServerConfigExt:
              address: redis://192.168.0.110:6379
              #              password: root123
              database: 1
          twoRedisson:
            singleServerConfigExt:
              address: redis://192.168.0.110:6379
              #              password: root123
              database: 2
```