package com.example.config.impl.model;

/**
 * @author wunderl
 *
 */
public class RuleConfig {

	// SELECT KEY, PRIORITY, RULE, VALUE, DESCRIPTION, USERNAME FROM RULE_CONFIG
	
	private String key;
	private int prio = 0;
	private String rule;
	private String value;
	private String description;
	private String userName;
	
	public RuleConfig(String key, int prio, String rule, String value, String description, String userName) {
		this.key = key;
		this.prio = prio;
		this.rule = rule;
		this.value = value;
		this.description = description;
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getRule() {
		return rule;
	}
	
	public int getPrio() {
		return prio;
	}
	
	public String getKey() {
		return key;
	}
	
}
