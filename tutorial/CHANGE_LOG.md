### 1.3.0-4 alpha(2019-02-20)
1. 支持PostgreSQL。
### 1.3.0-3 alpha（2019-01-24）
#### api
1. 移植web模块的代码生成器；
2. 代码生成默认位置改为test目录；
#### web
1. 使用代码生成器生成用户管理；
2. 代码生成器支持时间类型字段；
3. 代码生成默认位置改为test目录；
4. 修复java.lang.IllegalArgumentException: Request header is too large。

### 1.3.0-2（2019-01-20）
#### web
1. 升级Spring Boot 1.5.18.RELEASE到1.5.19.RELEASE；
2. 模板增加MyBatis Provider模板；
3. 新增角色管理（权限管理核心）；
4. 完善权限控制，支持到页面操作按钮；
5. 使用代码生成器生成“新闻管理”，并不修改一行代码。

### 1.3.0-1(2019-01-06)
#### web
1. 新闻管理的新增和修改页面使用layer代替easyui-window，以解决neditor图片上传弹框高度太高，造成确定按钮被遮挡的问题；
2. 修复订单管理页面搜索bug；
3. 操作结果使用toastr代替easyui messager；
4. 移除froala editor依赖，该富文本编辑器不被允许用在开源项目中；
5. 解决index_iframe页面侧边栏菜单项打不开的bug；
6. 移除toastr依赖，并在plug-in中添加toastr相关js和css；
7. 移除index_v1和index_iframe页面的footer，以保留更多的标签页高度，给easyui-window提供更多的高度空间。

### 1.3.0(2018-12-20)
1. 项目拆分成两个模块，api和web；api运行在8181端口，web运行在8182端口。
2. 代码生成器[http://localhost:8181/tables?easyui-list](http://localhost:8181/tables?easyui-list)和[http://localhost:8182/tables?easyui-list](http://localhost:8182/tables?easyui-list)
3. 大量更新；

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
