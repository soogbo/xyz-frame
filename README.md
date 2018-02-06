# xyz-frame
# this is my springboot frame demo

使用springboot架构搭建测试demo：

版本：

	20171214：添加mapper po类自动生成工具，junit测试demo，自定义线程池demo，定时任务controller demo
	
编码规范：
	建议：
	1.阿里Java开发规范
	2.java doc规范
	3.mapper查询结果使用Po Vo接收，不使用Map
-----------------
　A) Service/DAO 层方法命名规约

　　　　1） 获取单个对象的方法用 get 做前缀。

　　　　2） 获取多个对象的方法用 list 做前缀。

　　　　3） 获取统计值的方法用 count 做前缀。

　　　　4） 插入的方法用 save（推荐）或 insert 做前缀。

　　　　5） 删除的方法用 remove（推荐）或 delete 做前缀。

　　　　6） 修改的方法用 update 做前缀。

-----------------
	
	规范：
	1.所有类添加注释/** */
	2.所有方法添加注释/** */(实现自定义接口不添加)
	3.包名规范，xyz.frame.*
	4.实体类统一规则：
		1.pojo包下：po vo bo dto enum constants等
		2.实体类名在po包下，类名不需要加Po结尾，其他pojo类需要添加VoBoDto等
		3.Po：表对应实体类，
		4.Vo：web展示/业务使用类，
		5.Bo：接收web参数类
	5.任务job根据demo编写，TestScheduleServiceImpl.executeJob()，每个job只有一个入口executeJob()；
	6.测试代码放在 src/test/java xyz.frame.main包下

已有功能：
1.统一异常管理
2.controller aspect管理
3.rocketmq集成demo
4.druid连接池配置
5.集成quartz并数据库配置job
6.u_user demo
7.mybatis集成通用mapper and pagehelp
8.（20171214）线程池demo
9.（20171214）单元测试案例demo
10.（20171214）mybatis自动生成mapper和entity
11.静态资源访问
12.job demo
13.swarggerUi集成demo
14.ftp连接demo
15.param_class 配置
16.httpclient
17.jdkproxy demo
18.大鱼sms发送demo
19.微信挑一挑tool
20.

-------------------
计划添加功能：
1.redis连接
2.mongo连接
3.mysql主从配置
4.
5.web页面demo
6.websock demo
7.
8.
9.上传下载demo
10.
11.
12.bootstrap web
13.日志管理
14.缓存添加 ehcache redis
15.java 邮件发送
16.集成webmagic
17.
18.分环境自动编译配置文件config
19.mvc-config配置(拦截器，视图解析器等)
20.