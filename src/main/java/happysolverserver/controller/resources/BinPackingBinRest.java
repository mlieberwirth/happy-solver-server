package happysolverserver.controller.resources;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BinPackingBinRest {

	private List<BinPackingItemRest> items = new ArrayList<>();
}
