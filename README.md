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

已有功能：
01.统一异常管理
02.controller aspect管理
02.rocketmq集成demo
03.druid连接池配置
04.集成quartz并数据库配置job
05.u_user demo
06.mybatis集成通用mapper and pagehelp
07.（20171214）线程池demo
08.（20171214）单元测试案例demo
09.（20171214）mybatis自动生成mapper和entity
10.静态资源访问
11.
12.

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
10.job demo
11.httpclient
12.bootstrap web
13.日志管理
14.缓存添加 ehcache redis
15.java 邮件发送
16.集成webmagic
17.param_class 配置
18.分环境自动编译配置文件
19.mvc-config配置(拦截器，视图解析器等)
20.