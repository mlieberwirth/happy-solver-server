package happysolverserver.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happysolverserver.controller.resources.BinPackingModelRest;
import happysolverserver.controller.resources.BinPackingSolutionRest;
import happysolverserver.persistence.BinPackingModel;
import happysolverserver.service.BinPackingModelService;
import happysolverserver.service.ReceiveBinPackingModelService;
import happysolverserver.service.ReceiveBinPackingSolutionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(BinPackingController.MAPPING)
public class BinPackingController {

	public static final String MAPPING = "/binpacking";
	public static final String SOLUTIONS = "/solutions";
	public static final String MODELS = "/models";
	public static final String RECEIVE = "/receive";

	@Autowired
	@Qualifier("rest-thread-pool")
	private ThreadPoolTaskScheduler threadPool;

	@Autowired
	private BinPackingModelService modelService;

	@Autowired
	private ReceiveBinPackingModelService receiveModelService;

	@Autowired
	private ReceiveBinPackingSolutionService receiveSolutionService;

	@PostMapping(SOLUTIONS + RECEIVE)
	public CompletableFuture<String> receiveSolution(@RequestBody BinPackingSolutionRest solution) {
		log.info("Receive solution.");
		return CompletableFuture.supplyAsync(() -> receiveSolutionService.receiveNewSolution(solution), threadPool);
	}

	@GetMapping(MODELS)
	public CompletableFuture<List<BinPackingModel>> getAllModels() {
		log.info("Get all models");
		return CompletableFuture.supplyAsync(modelService::findAll, threadPool);
	}

	@GetMapping(MODELS + "/{id}")
	public CompletableFuture<BinPackingModel> getModel(@PathVariable("id") Long id) {
		log.info("Get model " + id);
		return CompletableFuture.supplyAsync(() -> modelService.findById(id), threadPool);
	}

	@PostMapping(MODELS + RECEIVE)
	public CompletableFuture<String> receiveModel(@RequestBody BinPackingModelRest model) {
		log.info("Receive model.");
		return CompletableFuture.supplyAsync(() -> receiveModelService.receiveNewModel(model), threadPool);
	}
}
