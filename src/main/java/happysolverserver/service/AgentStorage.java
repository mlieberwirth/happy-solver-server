package happysolverserver.service;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import happysolverserver.controller.resources.AgentRegister;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgentStorage {

	// last registered time
	private final Map<AgentRegister, Instant> agentRegisterTimeMap = new LinkedHashMap<>();
	// if a agent run a optimization we store the model here
	private final Map<AgentRegister, Long> assignedModelMap = new LinkedHashMap<>();

	public Set<AgentRegister> getRegisteredAgents() {
		return Collections.unmodifiableSet(agentRegisterTimeMap.keySet());
	}

	public Instant getRegisteredTime(AgentRegister agent) {
		return agentRegisterTimeMap.get(agent);
	}

	public void registerAgent(AgentRegister agent) {
		log.debug("Register agent: " + agent.getAgentName());
		agentRegisterTimeMap.put(agent, Instant.now());
	}

	public void assignModel(AgentRegister agent, Long modelId) {
		assignedModelMap.put(agent, modelId);
	}

	public Long getAssignedModel(AgentRegister agent) {
		return assignedModelMap.get(agent);
	}

	public void removeAgents(Set<AgentRegister> outdatedAgents) {
		outdatedAgents.stream().forEach(agent -> {
			log.debug("Remove agent: " + agent.getAgentName());
			agentRegisterTimeMap.remove(agent);
			assignedModelMap.remove(agent);
		});
	}
}
