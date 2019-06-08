package com.ljx.chapter17zuul.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class VerfFilter extends ZuulFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper ;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String userId = request.getParameter("userId");
        return StringUtils.hasText(userId);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String userId = request.getParameter("userId");
        String reqVerfCode = request.getParameter("reqVerfCode");
        String verfCode = (String) redisTemplate.opsForValue().get("user:"+userId);
        if(null == reqVerfCode || !verfCode.equals(reqVerfCode) ) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(40004);
            try {
                ctx.setResponseBody(objectMapper.writeValueAsString(ImmutableMap.of("code", "40004", "message", "verf error")));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
