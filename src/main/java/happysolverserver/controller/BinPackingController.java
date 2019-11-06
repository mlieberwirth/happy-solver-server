package happysolverserver.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happysolverserver.persistence.BinPackingModel;
import happysolverserver.service.BinPackingModelService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/binpacking")
public class BinPackingController {

	@Autowired
	@Qualifier("rest-thread-pool") // own pool for graceful shutdown
	private ThreadPoolTaskScheduler threadPool;

	@Autowired
	private BinPackingModelService modelService;

	@GetMapping("/models")
	public CompletableFuture<List<BinPackingModel>> getAllModels() {
		log.info("Get all models");
		return CompletableFuture.supplyAsync(modelService::findAll, threadPool);
	}

	@GetMapping("/models/{id}")
	public CompletableFuture<BinPackingModel> getModel(@PathVariable("id") Long id) {
		log.info("Get model " + id);
		return CompletableFuture.supplyAsync(() -> modelService.findById(id), threadPool);
	}
}
