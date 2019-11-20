# logid-parent
练习自定义spring-boot-starter，主要功能是给日志增加logId  

使用方法:  
1.下载源码  
2.对logid-parent父工程进行maven打包构建install（也可以自行上传到maven私服）  
3.在需要加logid的服务里，引用刚构建到本地的maven依赖  
4.在自己服务的log4j.xml配置里，在格式化日志内容的地方，加上"%{logId}"  
5.在接口入口，消费者入口，定时任务开始的方法上面，写上注解@LogId  
6.然后打印的日志里，在第4步里加的位置处就会多出一个logId了  
7.如果流程中有异步线程，需要使用本项目里提供的带logid的线程池（MdcThreadPoolTaskExecutor）去初始化，当然你也可以自行修改自己需要的线程池  
