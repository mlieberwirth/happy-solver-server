package happysolverserver.service;

import static java.util.stream.Collectors.toSet;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happysolverserver.config.ServerConfiguration;
import happysolverserver.controller.resources.AgentRegister;

@Service
public class AgentService {

	@Autowired
	private ServerConfiguration configuration;

	private final AgentStorage agentStorage = new AgentStorage();

	public void registerAgent(AgentRegister register) {
		agentStorage.registerAgent(register);
	}

	public void handleOutdatedAgents() {

		Set<AgentRegister> outdatedAgents = getOutdatedAgents();
		// TODO abort optimization run
		agentStorage.removeAgents(outdatedAgents);
	}

	private Set<AgentRegister> getOutdatedAgents() {
		return agentStorage.getRegisteredAgents().stream() //
				.filter(agent -> isOutdated(agent)) //
				.collect(toSet());
	}

	private boolean isOutdated(AgentRegister agentRegister) {
		Instant registeredTime = agentStorage.getRegisteredTime(agentRegister);
		long millisBetween = ChronoUnit.MILLIS.between(Instant.now(), registeredTime);
		return millisBetween > configuration.getDisableAgentMillis();
	}
}
