package happysolverserver.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import happysolverserver.service.AgentService;

@Component
public class UpdateAgentsScheduler {

	@Autowired
	private AgentService agentService;

	@Scheduled(fixedDelayString = "${scheduler.update-agent-interval-millis}")
	public void run() {
		agentService.handleOutdatedAgents();
	}
}
