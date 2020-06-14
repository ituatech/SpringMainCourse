package com.it_uatech.lifecycle;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class LifeCycleConfigCondition extends AllNestedConditions {
    public LifeCycleConfigCondition() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
        System.out.println("LifeCycleConfigCondition was success");
    }

    @ConditionalOnProperty(name = "spring.shell.interactive.enabled", havingValue = "false")
    static class ShellDisabledCondition {
    }

    @ConditionalOnProperty(name = "life-cycle-demo-enabled", havingValue = "true")
    static class ShowLifeCirclePropertyEnabledCondition {
    }
}
