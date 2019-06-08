package com.ljx.chapter4.configuration;

import com.ljx.chapter4.aspect.AopServiceAspect;
import com.ljx.chapter4.service.AopService;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(
        useDefaultFilters = false
        ,basePackages = "com.ljx.chapter4.service"
//        ,excludeFilters = {@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes = {HelloService.class})}
        ,includeFilters = {@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes = {AopService.class})}
        )
@EnableAspectJAutoProxy
public class AopConfiguration {

    @Bean("aopServiceAspect")
    public AopServiceAspect buildAopServiceAspect() {
        return new AopServiceAspect();
    }
}
