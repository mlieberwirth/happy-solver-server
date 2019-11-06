package happysolverserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happysolverserver.persistence.BinPackingModel;
import happysolverserver.repository.BinPackingModelRepository;

@Service
public class BinPackingModelService {

	@Autowired
	private BinPackingModelRepository modelRepository;

	public List<BinPackingModel> findAll() {
		return modelRepository.findAll();
	}

	public BinPackingModel findById(Long id) {
		return modelRepository.findById(id).orElse(null);
	}

	public BinPackingModel saveModel(BinPackingModel model) {
		return modelRepository.saveAndFlush(model);
	}
}
