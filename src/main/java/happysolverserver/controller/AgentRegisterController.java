package happysolverserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happysolverserver.controller.resources.AgentRegister;
import happysolverserver.service.AgentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(AgentRegisterController.MAPPING)
public class AgentRegisterController {

	public static final String MAPPING = "/agents";

	public static final String REGISTER = "/register";

	@Autowired
	private AgentService agentService;

	@PostMapping(REGISTER)
	public void register(@RequestBody AgentRegister register) {
		log.debug("Register agent: " + register.getAgentName());
		agentService.registerAgent(register);
	}
}
