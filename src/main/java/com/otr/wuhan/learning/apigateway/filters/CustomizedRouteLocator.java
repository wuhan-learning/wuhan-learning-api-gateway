package com.otr.wuhan.learning.apigateway.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;

import static com.google.common.collect.Maps.newLinkedHashMap;

@Component
public class CustomizedRouteLocator extends DiscoveryClientRouteLocator {

    @Autowired
    public CustomizedRouteLocator(ServerProperties serverProperties,
                                  DiscoveryClient discovery,
                                  ZuulProperties zuulProperties,
                                  ServiceRouteMapper serviceRouteMapper) {
        super(serverProperties.getServlet().getServletPrefix(), discovery, zuulProperties, serviceRouteMapper);
    }

    @Override
    protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = newLinkedHashMap(super.locateRoutes());

        ZuulProperties.ZuulRoute route = new ZuulProperties.ZuulRoute("code_writed", "/code/**",
                "mst-order-service", null, true, null, Collections.emptySet());
        routesMap.put(route.getPath(), route);
        return routesMap;
    }
}
