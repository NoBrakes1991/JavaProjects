package starter.repos;

import org.springframework.data.repository.CrudRepository;
import starter.model.Tasks;

public interface DAO extends CrudRepository <Tasks, Long> {
}
