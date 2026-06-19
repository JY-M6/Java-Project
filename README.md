# 👨‍💻 高世隆的 Java 开发项目集

<p align="center">
  <img src="https://img.shields.io/badge/%E9%87%8D%E5%BA%86%E9%82%AE%E7%94%B5%E5%A4%A7%E5%AD%A6-%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6%E4%B8%8E%E6%8A%80%E6%9C%AF-blue?style=flat-square&logo=graduation-cap" alt="University">
  <img src="https://img.shields.io/badge/Java%E8%AF%BE%E7%A8%8B%E7%BB%A9%E7%82%B9-4.0%20%2F%204.0-emerald?style=flat-square&logo=java" alt="Java GPA">
  <img src="https://img.shields.io/badge/C%E8%AF%AD%E8%A8%80%E8%AF%BE%E7%A8%8B%E7%BB%A9%E7%82%B9-3.7%20%2F%204.0-emerald?style=flat-square" alt="C GPA">
</p>

<p align="center">
  <a href="mailto:3014489365@qq.com"><img src="https://img.shields.io/badge/Email-3014489365%40qq.com-red?style=flat-square&logo=gmail" alt="Email"></a>
  <a href="tel:15723155100"><img src="https://img.shields.io/badge/Phone-15723155100-blueviolet?style=flat-square&logo=skype" alt="Phone"></a>
  <a href="https://github.com/JY-M6"><img src="https://img.shields.io/badge/GitHub-JY--M6-black?style=flat-square&logo=github" alt="GitHub"></a>
</p>

---

## 🏆 个人荣誉 & 资质
* 🥇 **华为精英软件挑战赛** 西南赛区 **64强**
* 🎓 **学术绩点**：Java 课程绩点 **4.0** | C语言课程绩点 **3.7**
* 🇨🇳 **政治面貌**：共青团员

---

## 🛠 专业技能 (Skills)

| 领域 | 技能详情与应用实践 |
| :--- | :--- |
| **☕ Java 核心** | 深入掌握 Java 语言，熟悉 **JUC并发编程** (线程池、CAS、锁机制)，能在复杂业务中运用多线程提升性能；了解 JVM 内存模型与 GC 调优思路。 |
| **🏗 企业级框架** | 熟练运用 **Spring Boot**、**MyBatis-Plus** 等主流框架；掌握常见设计模式（工厂、策略等），能重构多渠道登录/支付聚合等复杂业务。 |
| **☁ 微服务与中间件** | 熟悉 **Spring Cloud Alibaba** 生态 (Nacos, Gateway, OpenFeign)；掌握 **Redis** 核心原理与集群搭建，解决缓存雪崩/击穿问题；熟练使用 **RabbitMQ** 异步解耦，了解 **Elasticsearch** 分布式搜索。 |
| **🌐 全栈与工程化** | 了解 **Vue3** 前端技术及 **ElementPlus** 组件库，具备独立完成前后端联调的能力；熟练使用 **Git**，熟悉 **Linux** 环境及 **Docker** 容器化部署。 |
| **🤖 AI 意图导向研发** | 熟练驾驭 **Codex**、**Antigravity** 等多 Agent 协同编程平台，深度践行 **Vibe Coding (意图导向编程)** 范式。具备极强的 Prompt 提示词工程与需求拆解能力，应用 AI 进行重构与边界用例 (Test Case) 推演，提升全栈研发效能 **50%** 以上。 |

---

## 💻 核心项目经历 (Projects)

### 🛒 惠民商城 (高并发 B2C 电商平台)
> 📅 **时间：** 2026.02 - 2026.05 | 👨‍💻 **角色：** 后端开发

#### 💡 项目简介
基于 Spring Cloud 微服务架构构建的高并发 B2C 电商平台。系统全面覆盖商品、订单、支付等核心链路，重点攻克了大促秒杀场景下的高并发写、海量数据检索及分布式一致性等技术难点。

#### 🛠 技术栈
![](https://img.shields.io/badge/Spring_Cloud_Alibaba-green?style=flat-square) ![](https://img.shields.io/badge/Nacos-blue?style=flat-square) ![](https://img.shields.io/badge/Gateway-lightgrey?style=flat-square) ![](https://img.shields.io/badge/MyBatis--Plus-blue?style=flat-square) ![](https://img.shields.io/badge/MySQL-orange?style=flat-square) ![](https://img.shields.io/badge/Redis_Cluster-red?style=flat-square) ![](https://img.shields.io/badge/Redisson-darkred?style=flat-square) ![](https://img.shields.io/badge/RabbitMQ-orange?style=flat-square) ![](https://img.shields.io/badge/Elasticsearch-yellow?style=flat-square) ![](https://img.shields.io/badge/Sentinel-red?style=flat-square) ![](https://img.shields.io/badge/Seata-blue?style=flat-square) ![](https://img.shields.io/badge/Docker-blueviolet?style=flat-square)

#### 🚀 核心职责与成果
1. **微服务架构与高可用治理**：基于 Nacos 与 Spring Cloud 构建微服务矩阵；针对大促峰值流量，集成 Sentinel 配置自定义流控与熔断降级规则，成功抵御**上万 QPS** 瞬时并发，保障核心交易链路在极端情况下的高可用性。
2. **多级缓存与热点 Key 处理**：主导搭建 Redis 分片集群应对海量并发读写。针对秒杀场景下的“热点 Key”问题，设计 **Caffeine 本地缓存 + Redis 集群** 的多级缓存架构，并结合逻辑过期机制与 Redisson 分布式锁，彻底解决缓存穿透与击穿问题，使缓存整体命中率提升至 **90%** 以上，核心接口响应延迟降低 **70%**。
3. **异步削峰与消息幂等性实现**：引入 RabbitMQ 对订单超时取消、库存异步扣减等重操作进行异步解耦，将下单接口平均响应时间从 **1.2s** 压降至 **200ms** 以内。为应对网络抖动导致的消息重复投递，基于 **Redis 分布式锁 + 数据库防重表（唯一索引）** 严格保证消息消费的幂等性，实现海量消息吞吐下的零重复消费。
4. **分布式事务与海量检索**：运用 Seata（AT模式）保障跨微服务的数据最终一致性，消除脏写隐患；引入 Elasticsearch 替代 MySQL 模糊查询，结合自定义分词器实现百万级商品的复杂条件聚合过滤与高亮显示，将检索接口的 **P99 延迟稳定控制在 80ms 以内**。

---

### 👁️ AURA VISION - 智能视觉对话助手 (MVP)
> 📅 **时间：** 2026.01 - 2026.03 | 👨‍💻 **角色：** 独立开发者

#### 💡 项目简介
基于多模态大模型的轻量级实时音视频对话应用。项目全流程采用 **AI Vibe Coding（意图导向编程）** 范式，实现从架构设计到全栈开发的高效闭环，支持自然语言与上传图像的实时多轮语义交互。

#### 🛠 技术栈
![](https://img.shields.io/badge/Spring_Boot-green?style=flat-square) ![](https://img.shields.io/badge/WebFlux-blue?style=flat-square) ![](https://img.shields.io/badge/Vue3-green?style=flat-square) ![](https://img.shields.io/badge/Vite-blueviolet?style=flat-square) ![](https://img.shields.io/badge/WebRTC-lightgrey?style=flat-square) ![](https://img.shields.io/badge/SSE-yellow?style=flat-square) ![](https://img.shields.io/badge/Redis-red?style=flat-square) ![](https://img.shields.io/badge/Trae-blue?style=flat-square) ![](https://img.shields.io/badge/Codex-black?style=flat-square)

#### 🚀 核心职责与成果
1. **多模态集成与流式响应**：端到端对接大模型 Vision API，深度解析图像与语义。后端基于 **WebFlux 响应式框架** 结合 **SSE (Server-Sent Events)** 实现打字机式流式文本输出，将**首包响应延迟稳定控制在 200ms 以内**。
2. **带宽优化与会话隔离**：针对高频图像帧上传导致的带宽瓶颈，前端自研动态裁剪与高压缩比抓取逻辑，配合后端 **Redis 动态滑动窗口机制**，不仅消除串键隐患，还将大模型 Token 开销降低 **35%**，**带宽消耗节省 98%**。
3. **智能语音感知与高可用防抖**：集成 Web Speech API 实现本地 STT（语音转文本）零成本方案。在 `sendMessage` 生命周期切入点设计麦克风自动启停与 **2 秒滑动防抖机制**，彻底解决环境噪音干扰及多会话切换时的消息并发重复问题。

---

### 🤖 智能售货机运营管理平台 (IoT 物联网调度系统)
> 📅 **时间：** 2025.07 - 2025.10 | 👨+💼 **角色：** 后端开发

#### 💡 项目简介
面向线下真实零售场景的物联网智能售货机运营平台。系统打破常规单一 Web 架构，实现了云端后台、运营端 App 及售货机终端的三端闭环联动，全面解决设备点位管理、工单实时调度及库存监控等核心业务需求。

#### 🛠 技术栈
![](https://img.shields.io/badge/Spring_Boot-green?style=flat-square) ![](https://img.shields.io/badge/MyBatis--Plus-blue?style=flat-square) ![](https://img.shields.io/badge/MySQL-orange?style=flat-square) ![](https://img.shields.io/badge/Redis-red?style=flat-square) ![](https://img.shields.io/badge/EMQX_(MQTT)-blueviolet?style=flat-square) ![](https://img.shields.io/badge/%E9%98%BF%E9%87%8C%E4%BA%91%E7%9F%AD%E4%BF%A1-orange?style=flat-square) ![](https://img.shields.io/badge/Vue3-green?style=flat-square) ![](https://img.shields.io/badge/Uniapp-blue?style=flat-square) ![](https://img.shields.io/badge/Docker-blueviolet?style=flat-square)

#### 🚀 核心职责与成果
1. **复杂资产模型构建**：深入分析线下实体商业逻辑，设计并实现后端 RESTful API，完成售货机设备模型搭建及“**区域 - 点位 - 设备 - 货道**”的多级复杂关联关系映射。
2. **物联网双向通信**：针对云端与实体设备高频交互的痛点，搭建 EMQX 消息服务器，利用轻量级 **MQTT 协议** 替代传统 HTTP 轮询，实现云端指令（如策略下发）与售货机主板状态数据的实时、低延迟双向通信。
3. **工单全生命周期调度**：负责设备投放、补货、维修等工单业务流开发。基于状态机模式设计了“**待办-接受-执行-完成**”的严格流转逻辑，并集成阿里云短信服务进行派单提醒，配合 App 端实现高效的现场运维支持。
4. **缓存架构与性能调优**：针对高频访问的只读数据，引入 Redis 对热点数据（如商品详情、字典配置数据）进行前置缓存，有效缓解了 MySQL 数据库的读压力，实测使核心业务接口响应时间**锐减 60% 以上**。

---

### 📚 TT智能学习辅助系统 (企业级后台管理)
> 📅 **时间：** 2025.03 - 2025.06 | 👨‍💻 **角色：** 全栈开发

#### 💡 项目简介
符合现代企业级规范的前后端分离中后台系统。采用 Maven 多模块工程化构建，为企业级复杂业务提供标准化的权限管控、日志审计与文件存储解决方案。

#### 🛠 技术栈
![](https://img.shields.io/badge/Spring_Boot-green?style=flat-square) ![](https://img.shields.io/badge/MyBatis-blue?style=flat-square) ![](https://img.shields.io/badge/Vue3-green?style=flat-square) ![](https://img.shields.io/badge/ElementPlus-blueviolet?style=flat-square) ![](https://img.shields.io/badge/JWT-lightgrey?style=flat-square) ![](https://img.shields.io/badge/AOP-orange?style=flat-square) ![](https://img.shields.io/badge/ThreadLocal-blue?style=flat-square) ![](https://img.shields.io/badge/Aliyun_OSS-orange?style=flat-square)

#### 🚀 核心职责与成果
1. **无状态鉴权与并发上下文安全**：摒弃传统 Session，基于 **JWT** 实现轻量级无状态登录鉴权。在 Spring 拦截器中解析 Token 后，巧妙利用 **ThreadLocal** 隔离并存储当前线程的用户上下文。特别在 `afterCompletion` 阶段显式调用 `remove()` 方法，彻底杜绝了线程池复用导致的内存泄漏 (OOM) 及数据串键隐患。
2. **无侵入式监控与异步日志调优**：为实现全链路操作审计，通过自定义注解 (`@Log`) 结合 **Spring AOP** 面向切面编程，动态拦截核心方法并提取入参、耗时等指标。为了避免日志入库阻塞主业务流程，引入异步线程池 (`@Async`) 执行日志落盘，实现对核心业务的零侵入与零延迟干扰。
3. **工程化治理与云原生集成**：采用 **Maven 多模块架构**（按层与按业务双向拆分）重构单体代码，统一封装全局响应对象 (`Result`) 与 `GlobalExceptionHandler` 全局异常处理器，消除了系统内 **40% 以上** 的冗余 `try-catch` 块。对接阿里云 OSS 实现资料文件的云端直传与持久化，极大减轻了应用服务器的带宽与存储压力。
