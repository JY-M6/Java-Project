package com.hmall.gateway.routers;

import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicRouteLoader {

    private final NacosConfigManager nacosConfigManager;
    private final RouteDefinitionWriter writer;
    private final Set<String> routeIds = new HashSet<>();

    private static final String dataId = "gateway-routes.json";
    private static final String group = "DEFAULT_GROUP";

    @PostConstruct
    public void initRouteConfigListener() throws NacosException {
        //1. 项目启动时，拉取一次配置，并且添加配置监听器
        String configInfo = nacosConfigManager.getConfigService()
                .getConfigAndSignListener(dataId, group, 5000, new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }

                    @Override
                    public void receiveConfigInfo(String s) {
                        //2. 配置更新时，解析配置，更新路由
                    }
                });
        //3.第一次读取到配置，也需要更新到路由表
        updateRouteConfig(configInfo);
    }

    public void updateRouteConfig(String configInfo) {
        log.debug("监听到路由配置信息：{}", configInfo);
        //1. 解析配置，转为路由对象
        List<RouteDefinition> routeDefinitions = JSONUtil.toList(configInfo, RouteDefinition.class);
        //2. 删除旧的路由表
        for (String routeId : routeIds){
            writer.delete(Mono.just(routeId)).subscribe();
        }
        for (RouteDefinition routeDefinition : routeDefinitions) {
            //3.1 更新路由表
            writer.save(Mono.just(routeDefinition)).subscribe();
            //3.2 记录路由ID，方便后续删除
            routeIds.add(routeDefinition.getId());
        }
    }
}
