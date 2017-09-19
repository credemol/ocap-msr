package ocap.msr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ocap.msr.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

	Team findByName(@Param("name") String name);
	
	@Query("from Team t where t.name IN :names")
	List<Team> findByNames(@Param("names") String... names);
}
