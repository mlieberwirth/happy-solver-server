package happysolverserver.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
@Entity
public class BinPackingModel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 64)
	@NotBlank
	private String name;

	@Positive
	private Integer capacity;

	@OneToMany(mappedBy = "model", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<BinPackingItem> items = new ArrayList<>();
}
