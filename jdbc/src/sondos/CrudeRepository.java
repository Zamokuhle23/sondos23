package sondos;

import java.util.*;

public interface CrudeRepository<T> {
	
	 List<T> findAll();
	 T findById(Long id);
	 void save(T entity);
	 void update(T entity);

}
