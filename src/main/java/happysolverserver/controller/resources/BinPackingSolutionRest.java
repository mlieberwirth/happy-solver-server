package happysolverserver.controller.resources;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BinPackingSolutionRest {

	private Long modelId;

	private String modelName;

	private List<BinPackingBinRest> bins = new ArrayList<>();
}
