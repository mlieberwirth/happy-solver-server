package happysolverserver.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
@Entity
public class BinPackingItem {

	@Id
	@GeneratedValue
	private Long id;

	@Positive
	private Integer amount;

	@ManyToOne
	@JoinColumn(name = "model_id", nullable = false)
	@NotNull
	private BinPackingModel model;
}
