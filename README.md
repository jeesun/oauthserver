# oauthserver
## 接口
- POST localhost:8182/oauth/token?grant_type=password&username=&password=
需要basic oauth：username=clientIdPassword,password=secret。

- GET /oauth/check_token?token=
- POST /oauth/token?grant_type=refresh_token&refresh_token=

获取敏感数据，如用户信息，需要参数access_token，Spring Boot Oauth会自动检查access_token是否过期。接口可设置权限。

## 使用流程
### application.yml：
- jasypt.encryptor.password: 用于数据库连接新息的jasypt加密的密码。最好自定义更复杂的密码。
- spring.datasource: 配置数据库连接信息。**注意，此处的用户名和密码信息必须是加密过后的字符串，不能是明文。加密是通过测试类UtilTests的jasyptTest方法进行的。**