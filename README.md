## 💻 项目经历 | Project Experience

---

### 🛒 惠民商城 (高可用 B2C 电商平台)
> **📅 周期：** 2026.02 - 2026.05 &nbsp;&nbsp;|&nbsp;&nbsp; **👨‍💻 角色：** 后端开发
> 
> **💡 简介：** 基于 Spring Cloud 微服务架构构建的现代化电商平台。系统按业务边界划分为商品、订单、购物车、支付、用户等核心微服务，真实模拟并解决高并发与海量数据检索场景下的技术难点。

**🛠 技术栈：** <kbd>Spring Cloud Alibaba</kbd> <kbd>Nacos</kbd> <kbd>Gateway</kbd> <kbd>MyBatis-Plus</kbd> <kbd>MySQL</kbd> <kbd>Redis Cluster</kbd> <kbd>Redisson</kbd> <kbd>RabbitMQ</kbd> <kbd>Elasticsearch</kbd> <kbd>Sentinel</kbd> <kbd>Seata</kbd> <kbd>Docker</kbd>

**✨ 架构与技术亮点：**
* **微服务容错与高可用**：基于 Nacos 实现服务注册与统一配置中心；集成 Sentinel 配置自定义流控规则与熔断降级策略，保障核心交易链路在突发流量下的稳定运行。
* **海量数据检索优化**：引入 Elasticsearch 替代传统数据库模糊查询，通过自定义分词器实现海量商品的全文检索、多条件聚合过滤及高亮显示，大幅降低响应延迟。
* **高可用缓存与防超卖机制**：主导搭建 Redis 分片集群，设计缓存主动更新与兜底淘汰策略；引入 Redisson 分布式锁，彻底解决并发场景下的缓存击穿及高并发库存扣减超卖问题。
* **异步削峰与分布式事务**：使用 RabbitMQ 改造订单生成链路，实现订单超时自动取消、库存异步释放等复杂业务解耦；集成 Seata (AT模式) 保证跨服务（订单生成与库存扣减）的数据最终一致性。

---

### 🤖 智能售货机运营管理平台 (IoT 物联网调度系统)
> **📅 周期：** 2025.07 - 2025.10 &nbsp;&nbsp;|&nbsp;&nbsp; **👨‍💻 角色：** 后端开发
> 
> **💡 简介：** 面向多终端（Web 管理端、App 运营端、设备机端）的物联网售货机协同系统。全面涵盖设备点位管理、投放/撤机工单调度、商品库存监控与大屏销售数据分析。

**🛠 技术栈：** <kbd>Spring Boot</kbd> <kbd>EMQX (MQTT)</kbd> <kbd>Redis</kbd> <kbd>MyBatis-Plus</kbd> <kbd>MySQL</kbd> <kbd>Vue3</kbd> <kbd>Uniapp</kbd> <kbd>Docker</kbd>

**✨ 架构与技术亮点：**
* **物联网双向通信**：基于 EMQX 消息服务器，利用轻量级 MQTT 协议实现云端与售货机主板的实时远程指令下发与状态数据上报。
* **复杂设备模型构建**：设计并实现后端 RESTful API，完成售货机设备模型搭建及“区域-点位-设备-货道”的多级复杂关系映射。
* **工单全生命周期流转**：开发设备投放、补货、维修等工单的完整状态机流转逻辑（待办/接受/执行/完成），配合 App 端提供高效的现场运维支持。
* **缓存加速与性能调优**：引入 Redis 对热点数据（如商品详情、字典配置）进行缓存，有效缓解数据库读压力，使核心业务接口响应时间锐减 **60%** 以上。

---

### 📚 TT智能学习辅助系统 (企业级后台管理)
> **📅 周期：** 2025.03 - 2025.06 &nbsp;&nbsp;|&nbsp;&nbsp; **👨‍💻 角色：** 全栈开发
> 
> **💡 简介：** 符合现代企业级开发规范的前后端分离后台管理系统，提供高标准的用户管理、权限校验、日志监控与可视化数据统计功能。

**🛠 技术栈：** <kbd>Spring Boot</kbd> <kbd>MyBatis</kbd> <kbd>Vue3</kbd> <kbd>ElementPlus</kbd> <kbd>JWT</kbd> <kbd>AOP</kbd> <kbd>ThreadLocal</kbd> <kbd>Aliyun OSS</kbd>

**✨ 架构与技术亮点：**
* **工程化与规范化设计**：采用 Maven 多模块工程进行分层解耦，剥离公共工具类与实体 POJO；统一封装全局响应结果对象 (Result)，规范化前后端接口通信。
* **无状态安全鉴权中心**：基于 JWT 实现无状态登录鉴权，结合 Spring 拦截器完成请求拦截；巧妙运用 ThreadLocal 存储线程级别的用户 ID，实现 Service 层安全的上下文变量透传。
* **面向切面与异常接管**：通过自定义注解结合 Spring AOP，实现核心业务接口的无侵入式操作日志记录；构建全局异常处理器 (GlobalExceptionHandler)，大幅减少冗余代码。
* **云端存储与动态交互**：后端对接 Aliyun OSS 实现静态资源的云端持久化存储；前端运用 Vue3 组合式 API 与 ElementPlus，实现动态表格展示、多条件分页及复杂表单的数据校验。
