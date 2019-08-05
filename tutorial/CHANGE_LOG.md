### 2.0.20190621-alpha
1. 代码生成器bug修复；
2. t_s_column_ui添加字段；
3. 修复postgresql下的登录问题。
4. 修复用户管理页面导入功能；
5. BasicService添加批量保存和批量更新接口方法；
6. 代码生成器生成的导入接口代码，添加提示“该请求路径必须添加到WebSecurityConfig的csrf忽略列表里”；
7. 修复新增和编辑页面错误提示不显示的bug。

### 2.0.20190617-alpha
1. 更新代码生成器生成的entity模板，添加excel导入导出注解；
2. 升级myexcel 2.5.0->2.5.1，升级element-ui 2.7.2->2.9.1；
3. 菜单管理页面优化；
4. 修复quartzJob form label过长换行bug；
5. 图标管理页面国际化bug修复；
6. 修复一些国际化细节bug。

### 2.0.20190613-alpha
1. 重写导入导出功能；
2. 删除无用页面代码；
3. 图标选择框bug修复；
4. 重命名表t_s_font_awesome的部分字段；
5. 一些细节。

### 2.0.20190521-alpha
1. 更新角色管理页面；
2. 解决角色已拥有页面权限，页面依然不显示的bug，需要重新保存一次权限。

### 2.0.20190517-alpha
1. 修复quartz定时任务bug；
2. 移除api模块的代码生成器，统一使用web模块的生成器，代码生成器位置允许选择common模块；
3. 省市区信息添加缓存；
4. 重写home页面；
5. 使用java8时间类型代替Date类型，并解决序列化问题；
6. 合并model、service、serviceImpl、repository、mapper到common模块；
7. 增加登录失败原因提示；
8. Spring Boot 1.5.20->1.5.21；
9. 动态计算iframe高度；
10. 其他bug修复。

### 2.0.20190506-alpha
1. 新增了很多功能；
2. 修复了很多bug；
3. 管理端页面重构，使用Vue + Element UI代替JQuery + EasyUI。

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
