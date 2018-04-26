<p align="center">
<a href="http://www.oracle.com/technetwork/java/javase/overview/index.html"><img src="https://img.shields.io/badge/language-java%208.0-orange.svg"></a>
<a href="https://www.jetbrains.com/idea/"><img src="https://img.shields.io/badge/platform-jetbrains-green.svg"></a>
<a href="http://projects.spring.io/spring-boot/"><img src="https://img.shields.io/badge/SpringBoot-1.5.10-990066.svg"></a>
<img src="https://img.shields.io/badge/Database-MySQL%7CPostgreSQL-brightgreen.svg">
<img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg">
<img src="https://img.shields.io/badge/release-1.0.0-brightgreen.svg">

</p>

# oauthserver
## 简介
oauthserver是一个基于Spring Boot Oauth2的完整的独立的Oauth服务器。仅仅需要创建相关数据表，修改数据库的连接信息，你就可以得到一个Oauth服务器。

支持的关系型数据库：
- PostgreSQL
- MySQL

已实现的功能：
1. 集成Spring Boot Oauth2，实现Oauth服务；
2. token保存到关系型数据库；
3. 日志记录保存到文件，并按日归档；
4. 数据库连接信息加密；
5. 集成Druid数据库连接池。

## 更新日志
- v1.0.3  
bug修复。
- v1.0.1  
获取token时，username允许传用户名、手机号或者邮箱。  
- v1.0.0  
完成基础Oauth服务。

## 使用流程
### 1. 建表
- PostgreSQL
请执行`src/main/resources/schema-pg.sql`，完成数据表的创建和测试数据的导入。
- MySQL
请执行`src/main/resources/schema-mysql.sql`，完成数据表的创建和测试数据的导入。
### 2. 修改数据库连接信息
在application.yml中，配置着数据库的连接信息。其中，配置项username和password是要经过jasypt加密的，不能直接填明文。加密密钥由`jasypt.encryptor.password`配置。你需要使用test目录下的UtilTests工具得到加密字符串。
- PostgreSQL
```
# PostgreSQL连接信息
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://127.0.0.1:5432/thymelte?useUnicode=true&amp;characterEncoding=UTF-8
    username: ENC(hTpbG9fq+7P3SntmXuNtDxbtWDqRuPV+)
    password: ENC(abdq6LyOspryFQHCqzEMTxRozyJVjIA4)
```

- MySQL
```
# MySQL连接信息
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: ENC(YiYjVwTulDGN//YaB3KbuA==)
    password: ENC(9oaIJkFgGSDFaHH3OXY63RHWQ+amDmiJ)
```
### 3. 运行
现在，一切已准备就绪。运行项目，当程序成功启动时，即表明你已配置成功。
### 4. 测试
在建表时，我已经向表添加了测试数据。以下请求参数的值，均是测试数据，在数据表中可以找得到。请根据需求到数据表中修改对应的值。    
在表`oauth_client_details`表中，已有一条测试数据。列`client_id`和`client_secret`的值，分别对应Basic Oauth的请求参数`username`和`password`的值。而列`access_token_validity`和列`refresh_token_validity`，分别代表access_token和refresh_token的有效期时间，以秒为单位。测试数据7200和5184000，分别代表2个小时和2个月（60天）。这是一个比较合理的有效期时间的设置，可以参考。

**token相关的接口，都需要进行Basic Oauth认证。**
> 1、根据用户名和密码获取access_token
>> POST [http://localhost:8182/oauth/token?grant_type=password&username=jeesun&password=1234567890c](http://localhost:8182/oauth/token?grant_type=password&username=jeesun&password=1234567890c)

**成功示例**  
status=200，返回的json数据：
``` json
{
    "access_token": "ca582cd1-be6c-4a5a-82ec-10af7a8e06eb",
    "token_type": "bearer",
    "refresh_token": "c24a6143-97c8-4642-88b9-d5c5b902b487",
    "expires_in": 3824,
    "scope": "read write trust"
}
```
**失败示例**  
1. 用户名错误  
status=400，返回的json数据：
``` json
{
    "error": "invalid_grant",
    "error_description": "用户名错误"
}
```
2. 密码错误  
status=400，返回的json数据：
``` json
{
    "error": "invalid_grant",
    "error_description": "密码错误"
}
```
3. 账号被封enabled=false  
status=400，返回的json数据：
``` json
{
    "error": "invalid_grant",
    "error_description": "您已被封号"
}
```

> 2、检查access_token
>> GET [http://localhost:8182/oauth/check_token?token=ca582cd1-be6c-4a5a-82ec-10af7a8e06eb](http://localhost:8182/oauth/check_token?token=ca582cd1-be6c-4a5a-82ec-10af7a8e06eb)

**成功示例**  
即使用户被封enabled=false，access_token未过期仍然可用。  
status=200，返回的json数据：
``` json
{
    "aud": [
        "oauth2-resource"
    ],
    "exp": 1524507296,
    "user_name": "jeesun",
    "authorities": [
        "ROLE_ADMIN",
        "ROLE_USER"
    ],
    "client_id": "clientIdPassword",
    "scope": [
        "read",
        "write",
        "trust"
    ]
}
```
**失败示例**  
access_token已过期  
status=400，返回的json数据：
``` json

{
    "error": "invalid_token",
    "error_description": "Token was not recognised"
}
```

> 3、根据refresh_token获取新的access_token
>> POST [http://localhost:8182/oauth/token?grant_type=refresh_token&refresh_token=c24a6143-97c8-4642-88b9-d5c5b902b487](http://localhost:8182/oauth/token?grant_type=refresh_token&refresh_token=c24a6143-97c8-4642-88b9-d5c5b902b487)

**成功示例**  
status=200，返回的json数据：
``` json
{
    "access_token": "690ecd7d-f2b7-4faa-ac45-5b7a319478e8",
    "token_type": "bearer",
    "refresh_token": "c24a6143-97c8-4642-88b9-d5c5b902b487",
    "expires_in": 7199,
    "scope": "read write trust"
}
```

**失败示例**  
用户被封enabled=false  
status=401，返回的json数据：
``` json
{
    "error": "unauthorized",
    "error_description": "用户已失效"
}
```
## app实践指南
app获取到token信息后，需要保存token信息和请求时间。在传access_token之前，需要检查access_token是否过期。为了减少后台压力，检查access_token是否过期应该是在app本地完成。通过token的key`expires_in`（剩余有效期）的值，以及本地记录的请求时间，和当前时间做对比，可以很方便地判断出access_token是否过期。如果过期了，需要通过refresh_token获取新的access_token。因为access_token的有效期只有2个小时，这个验证是必须的。    
refresh_token同理。