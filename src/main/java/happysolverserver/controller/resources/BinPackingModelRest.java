
package happysolverserver.controller.resources;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BinPackingModelRest {

	private Long id;

	private String name;

	private Integer capacity;

	private List<BinPackingItemRest> items = new ArrayList<>();
}
