### v.1.2.4.beta2(2018-09-13)
- 默认使用MySQL数据库连接配置，更新MySQL连接配置以支持utf8mb4编码；
- 新增BaseController，解决接口的时间类型传输问题；
- 默认开启mybatis二级缓存；
- 新增字典表t_dict_type和t_dict_type_group，新增字典工具类DictUtil；
- 更新MySQL建表语句。

### v1.2.4.beta(2018-08-18)
- 代码生成器bug修复；
- 重新生成实体类。

### v1.2.4.alpha(2018-08-11)
- 代码生成器配置文件`code-gen.properties`；
- 修复mysql建表语句bug；
- 添加对Oracle的支持。

### v1.2.3(2018-08-05)
- 内置代码生成器；
- 移动config目录到common目录下；
- 缓存UserDetails到Ehcache，若`spring.cache.type=redis`，则自动缓存UserDetails到Redis；
- clients信息存入内存，提高查询效率；
- 开启druid监控，访问[http://localhost:8182/druid](http://localhost:8182/druid)，用户名simon，密码19961120。

### v1.2.2(2018-08-01)[下载](https://codeload.github.com/jeesun/oauthserver/zip/v1.2.2)
- 合并users和user_info表,authorities表使用user_id代替username字段；
- 集成阿里大鱼，需要安装阿里大鱼jar，安装方法：运行src/main/resources/jars/install.bat；
- 集成Mybatis，以及Mybatis三大插件：通用Mapper，Mybatis-Plus，PageHelper；
- 集成swagger2，并配置非全局、无需重复输入的header参数（token），访问[http://localhost:8182/swagger-ui.html](http://localhost:8182/swagger-ui.html)；
- 集成Redis缓存，默认不开启；
- 自定义登录页面和授权页面，并修复授权码模式与密码模式共存问题；
- 更棒的接口示例HelloWorldController，强烈建议您阅读该Controller代码。

### v2.0.0.alpha(2018-07-16)[下载](https://codeload.github.com/jeesun/oauthserver/zip/v2.0.0.alpha)
- 升级Spring Boot版本从1.5.14.RELEASE到2.0.3.RELEASE。
