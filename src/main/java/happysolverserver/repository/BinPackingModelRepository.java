package happysolverserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import happysolverserver.persistence.BinPackingModel;

@Repository
public interface BinPackingModelRepository extends JpaRepository<BinPackingModel, Long> {

}
