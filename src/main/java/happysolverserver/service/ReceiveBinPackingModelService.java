package happysolverserver.service;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happysolverserver.controller.resources.BinPackingItemRest;
import happysolverserver.controller.resources.BinPackingModelRest;
import happysolverserver.persistence.BinPackingModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReceiveBinPackingModelService {

	@Autowired
	private BinPackingModelService modelService;

	public String receiveNewModel(BinPackingModelRest modelRest) {

		ValidationResult validationResult = validateModel(modelRest);
		if (!validationResult.isValid()) {
			return validationResult.getMessage();
		}

		BinPackingModel model = converteModel(modelRest);

		BinPackingModel savedModel = modelService.saveModel(model);
		return "Create new model for " + savedModel.getName() + " with id: " + savedModel.getId();
	}

	private BinPackingModel converteModel(BinPackingModelRest modelRest) {
		String json = JsonSerializer.toJson(modelRest);
		return JsonSerializer.fromJson(json, BinPackingModel.class);
	}

	private ValidationResult validateModel(BinPackingModelRest newModel) {

		boolean isNameBlank = isBlank(newModel.getName());
		if (isNameBlank) {
			String msg = "Model has no name.";
			log.debug(msg);
			return new ValidationResult(false, msg);
		}

		Integer capacity = newModel.getCapacity();
		if (capacity == null || capacity < 1) {
			String msg = "Model has no or non positiv capacity.";
			log.debug(msg);
			return new ValidationResult(false, msg);
		}

		List<BinPackingItemRest> items = newModel.getItems();
		if (isEmpty(items)) {
			String msg = "Model has no or empty items.";
			log.debug(msg);
			return new ValidationResult(false, msg);
		}

		boolean anyMatch = items.stream().anyMatch(item -> {
			Integer amount = item.getAmount();
			return amount == null || amount < 1;
		});
		if (anyMatch) {
			String msg = "At least one item has no or non positiv amount.";
			log.debug(msg);
			return new ValidationResult(false, msg);
		}
		return new ValidationResult(true, "Model is valid");
	}

	@Getter
	@AllArgsConstructor
	private static class ValidationResult {
		private final boolean isValid;
		private final String message;
	}
}
