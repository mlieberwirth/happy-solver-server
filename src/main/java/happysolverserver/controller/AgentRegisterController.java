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
@RequestMapping("/agents")
public class AgentRegisterController {

	@Autowired
	private AgentService agentService;

	@PostMapping("/register")
	public void register(@RequestBody AgentRegister register) {
		log.debug("Register agent: " + register.getAgentName());
		agentService.registerAgent(register);
	}
}
