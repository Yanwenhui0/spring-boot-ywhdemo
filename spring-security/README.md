# spring security oauth2
- spring security server: 基于 jdbc 存储 客户端消息 和 token

### 授权码模式测试
1. 启动 server:8080 和 resource:8081
2. 访问 `http://localhost:8080/oauth/authorize?client_id=client&response_type=code` 获取授权码：?code=pUylel
3. 用授权码访问：    curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=1JuO6V' "http://client:secret@localhost:8080/oauth/token"

### 密码模式测试
1. 启动 server:8080 和 resource:8081
2. 访问 `curl --location --request POST 'http://client:secret@localhost:8080/oauth/token?username=yanwenhui&password=123456&scope=app&grant_type=password'`

#### 密码模式问题
访问报错：
```java
{
    "error": "unsupported_grant_type",
    "error_description": "Unsupported grant type: password"
}
```

**原因：** 密码模式需要在认证服务器中设置 中配置AuthenticationManager

```java
// 1.在 WebSecurityConfiguration 中重写 authenticationManagerBean() 初始胡 AuthenticationManager bean
@Bean
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}

// 2.在 AuthorizationServerConfiguration 中注入 AuthenticationManager 
// 并交给public void configure(AuthorizationServerEndpointsConfigurer endpoints) 使用
@Autowired
private AuthenticationManager authenticationManager;

@Override
public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
            .tokenStore(tokenStore())
            .authenticationManager(authenticationManager);
}
```
