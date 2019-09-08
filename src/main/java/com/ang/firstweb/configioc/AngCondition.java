package com.ang.firstweb.configioc;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by ang on 2019/9/8.
 */
public class AngCondition implements Condition{
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if(context.getBeanFactory().containsBean("person2")){
            return true;
        }
        return false;
    }
}
