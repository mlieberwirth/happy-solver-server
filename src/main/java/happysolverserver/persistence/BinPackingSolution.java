package happysolverserver.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

public class BinPackingSolution {

//	@Id
//	@GeneratedValue
//	private Long id;
//
//	@OneToMany(mappedBy = "model", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//	private List<BinPackingBin> bins = new ArrayList<>();
//
//	@ManyToOne
//	@JoinColumn(name = "model_id", nullable = false)
//	@NotNull
//	private BinPackingModel model;
}
