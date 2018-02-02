package com.example.config;

import com.example.config.impl.service.RuleConfigState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.NativeEnvironmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableConfigServer
public class Application implements CommandLineRunner {
	
	@Autowired
	private RuleConfigState ruleConfigState;
	
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(final String... arg0) throws Exception {
		ruleConfigState.readStateFromDB();
	}

	@Configuration
	@Profile("native")
	protected static class NativeRepositoryConfiguration {

		@Autowired
		private ConfigurableEnvironment environment;

		@Bean
		public EnvironmentRepository environmentRepository() {
			return new NativeEnvironmentRepository(this.environment);
		}

	}
}
