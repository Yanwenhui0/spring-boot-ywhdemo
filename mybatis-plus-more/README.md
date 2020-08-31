# mybatis-plus-more
> mybatis-plus-more(`mybatis-plus`多数据源配置)，此项目主要演示了如何进行`mybatis-plus`的多数据源配置。

## 需要了解的 mybatis 重点类

- `DataSource`：数据源
- `MybatisProperties`：mybatis 配置字段
- `MybatisAutoConfiguration`：mybatis 自动配置类
- `SqlSessionFactory`：SqlSessionFactory是个单个数据库映射关系经过编译后的内存镜像
- `SqlSessionTemplate`：SqlSessionTemplate 是 MyBatis-Spring 的核心，这个类负责管理 MyBatis 的 SqlSession，调用 MyBatis 的 SQL 方法，翻译异常


## pom.xml 文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.springboot</groupId>
        <artifactId>spring-boot-ywhdemo</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <artifactId>mybatis-plus-more</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <name>mybatis-plus-more</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!-- mybatis-plus-boot-starter -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>

        <!-- druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>

        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <mainClass>com.springboot.mybatisplusmore.MybatisPlusMoreApplication</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```