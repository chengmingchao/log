# 全局业务日志
### 实现目标：
记录每个业务的id、操作类型（新增或者修改或者删除）、业务类型（具体是哪个业务）、操作前内容、操作后内容、操作人、操作时间

### 实现方式
使用aop + 自定义注解

### 实现步骤
* 自定义注解（包含：业务类型、使用到的mapper、业务参数类型、日志类型）
* 定义切面
    * 校验入参中是否能够匹配到注解中定义的业务参数类型，如果有则放入 操作后的JSONObject中，如果找不到则抛出异常。
    * 定义日志实体类与日志mapper（此实体类与日志mapper还不能通用，如何实现通用，待思考.......）
* 如果目标方法发生异常，则不保存日志
### 技术
* AOP
* ThreadLocal
* 反射
* 自定义注解
    
    
