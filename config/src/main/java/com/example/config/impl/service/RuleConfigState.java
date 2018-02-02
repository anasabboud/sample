package com.example.config.impl.service;

import com.example.config.impl.model.RuleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class RuleConfigState {

	private static final Logger LOG = LoggerFactory.getLogger(RuleConfigState.class);

	@Autowired
	private DataSource dataSource;
	
	private List<RuleConfig> ruleConfigs = new ArrayList<>();
	
	public void readStateFromDB() {
		LOG.info("read state from database");
		try {
			final Statement stmt = dataSource.getConnection().createStatement();
			final String sql = "SELECT KEY, PRIORITY, RULE, VALUE, DESCRIPTION, USERNAME FROM RULE_CONFIG";
			final ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				final RuleConfig rc = new RuleConfig(rs.getString("KEY"),
			               rs.getInt("PRIORITY"), 
			               rs.getString("RULE"),
			               rs.getString("VALUE"),
			               rs.getString("DESCRIPTION"),
			               rs.getString("USERNAME")
			               );
				ruleConfigs.add(rc);
			}
			rs.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		LOG.info("refreshed data state");
	}

	public void setRuleConfigs(final List<RuleConfig> ruleConfigs) {
		this.ruleConfigs = ruleConfigs;
	}
	
	public List<RuleConfig> getRuleConfigs() {
		return ruleConfigs;
	}

}
