package com.example.config;

import com.example.config.impl.model.RuleConfig;
import com.example.config.impl.service.RuleConfigState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.NativeEnvironmentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class CustomEnvRepo extends NativeEnvironmentRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CustomEnvRepo.class);

    @Autowired
    private RuleConfigState ruleConfigState;

    public CustomEnvRepo(final ConfigurableEnvironment environment) {
        super(environment);
    }

    @Override
    public Environment findOne(final String config, final String profile, final String label) {
        LOG.info("Service=" + config + " profile=" + profile + " label=" + label);
        final Environment environment = new Environment("MS");
        final Map<String, String> map;

        map = ruleConfigState.getRuleConfigs().stream()
                .filter(ruleConfig -> ruleConfig.getKey() != null && ruleConfig.getValue() != null)
                .collect(Collectors.toMap(RuleConfig::getKey, RuleConfig::getValue, (a, b) -> b));

        final PropertySource propertySource = new PropertySource("classpath:/application.properties", map);
        environment.add(propertySource);

        return environment;
    }
}
