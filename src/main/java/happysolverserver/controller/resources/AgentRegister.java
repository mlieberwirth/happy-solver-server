package happysolverserver.controller.resources;

import java.time.Instant;

import lombok.Data;

@Data
public class AgentRegister {

	private final String agentName;

	private final Instant agentStartInstant;
}
