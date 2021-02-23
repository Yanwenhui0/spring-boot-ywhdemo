# spring-admin-example
> spring-admin-example, 此项目集成了 spring-boot-admin/nacos/spring-security/spring-boot-email

## Modules
- [spring-admin-example-dependencies](https://github.com/Yanwenhui0/spring-boot-ywhdemo/tree/master/spring-admin-example/spring-admin-example-dependencies) : unite dependencies for spring-admin-example
- [spring-admin-example-client](https://github.com/Yanwenhui0/spring-boot-ywhdemo/tree/master/spring-admin-example/spring-admin-example-client) : admin client
- [spring-admin-example-server](https://github.com/Yanwenhui0/spring-boot-ywhdemo/tree/master/spring-admin-example/spring-admin-example-server) : admin server

## Focus
#### 集成 nacos 后 spring-boot-admin 获取节点信息的 url 相关参数皆从 nacos 中获取。

##### 例如 context-path，需要将 context-path 告诉 nacos：
```yaml
server:
  port: 8081
  servlet:
    context-path: /admin-client

spring:
  application:
    name: spring-boot-admin-client
  cloud:
    nacos:
      discovery:
        group: spring-boot-admin
        metadata:
          management:
            context-path: ${server.servlet.context-path}/actuator
      server-addr: 39.100.31.158:8848
      username: nacos
      password: nacos
```

##### 例如集成 spring-security 后，访问 url 需要先登录，登录的用户名和密码也是从 nacos 中获取，需要将 用户名和密码 告诉 nacos：
```yaml
server:
  port: 8080

spring:
  application:
    name: spring-boot-admin-server
  cloud:
    nacos:
      discovery:
        group: spring-boot-admin
        metadata:
          user.name: "yanwenhui"
          user.password: "ywh169747"
      server-addr: 39.100.31.158:8848
      username: nacos
      password: nacos
  security:
    user:
      name: yanwenhui
      password: ywh169747
```

#### 集成 spring-boot-admin 和 spring-boot-email : admin 的邮件发送者( from ) 和 email 配置的发送者保持一致且不可省略:

```yaml
server:
  port: 8080

spring:
  application:
    name: spring-boot-admin-server
  cloud:
    nacos:
      discovery:
        group: spring-boot-admin
        metadata:
          user.name: "yanwenhui"
          user.password: "ywh169747"
      server-addr: 39.100.31.158:8848
      username: nacos
      password: nacos
  security:
    user:
      name: yanwenhui
      password: ywh169747
  mail:
    host: smtp.qq.com
    port: 587
    username: 1697479326@qq.com
    password: jkqccrytruxoejcg
  boot:
    admin:
      notify:
        mail:
          to: ywh1697479326@qq.com
          from: 1697479326@qq.com
```

#### 注册 nacos 报错

com.alibaba.nacos.api.exception.NacosException: failed to req API:/api//nacos/v1/ns/instance after all servers([127.0.0.1:8848]) tried:

**2021-02-23：由于阿里云服务器过期了，在本地搭建了nacos-1.4.1版本，导致注册nacos报错，错误原因：版本不一致导致，nacos-1.4.1 -> spring cloud alibaba 2.2.5 -> spring boot 2.3.2**

**本地 nacos 为集群启动模式，在集群配置文件中不能写 127.0.0.1:8848，否则会注册到 nacos 报错**

## pom.xml 文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-boot-ywhdemo</artifactId>
        <groupId>com.springboot</groupId>
        <version>1.0.0-SNAPSHOT</version>
        <relativePath>../pom.xml</relativePath>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-admin-example</artifactId>
    <packaging>pom</packaging>
    <modules>
        <module>spring-admin-example-server</module>
        <module>spring-admin-example-client</module>
        <module>spring-admin-example-dependencies</module>
    </modules>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.springboot</groupId>
                <artifactId>spring-admin-example-dependencies</artifactId>
                <version>1.0.0-SNAPSHOT</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

</project>
```
