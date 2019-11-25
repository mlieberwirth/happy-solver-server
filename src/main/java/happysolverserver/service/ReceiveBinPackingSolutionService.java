package happysolverserver.service;

import org.springframework.stereotype.Service;

import happysolverserver.controller.resources.BinPackingSolutionRest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReceiveBinPackingSolutionService {

	public String receiveNewSolution(BinPackingSolutionRest packingSolutionRest) {

		// TODO validate

		// TODO saveS

		String msg = "Receive Solution.";
		log.debug(msg);
		return msg;
	}

}
