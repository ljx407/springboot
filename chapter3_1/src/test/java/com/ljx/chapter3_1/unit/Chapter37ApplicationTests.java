package com.ljx.chapter3_1.unit;

import com.ljx.chapter3_1.configuration.Chapter3_7Configuration;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Chapter37ApplicationTests {
    AnnotationConfigApplicationContext applicationContext;

//    @Before
//    public void setUp() {
//        applicationContext = new AnnotationConfigApplicationContext(Chapter3_7Configuration.class);
//    }


    @Test
    public void contextLoad() {
        applicationContext = new AnnotationConfigApplicationContext(Chapter3_7Configuration.class);

        Assert.assertFalse(applicationContext.containsBean("testDevProfileDataSource"));
        Assert.assertTrue(applicationContext.containsBean("testProfileDataSource"));

    }

    @Test
    public void contextLoadWithEnviroment() {
        applicationContext = new AnnotationConfigApplicationContext();

        ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
        // on approach
//        MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
//        Map<String,Object> propertySource = new HashMap<>();
//        propertySource.put("spring.profiles.active","dev");
//        propertySources.addLast(new MapPropertySource("myprofile",propertySource));
//        applicationContext.setEnvironment(configurableEnvironment);

        // another approach
        configurableEnvironment.setActiveProfiles("dev");
        applicationContext.register(Chapter3_7Configuration.class);
        applicationContext.refresh();

        Assert.assertTrue(applicationContext.containsBean("testDevProfileDataSource"));
        Assert.assertTrue(applicationContext.containsBean("testProfileDataSource"));

        BasicDataSource testDevProfileDataSource = applicationContext.getBean("testDevProfileDataSource", BasicDataSource.class);
        Assert.assertEquals(testDevProfileDataSource.getUrl(),"jdbc:mysql://127.0.0.1:3306/testa?useSSL=false");
    }


}
