package sondos;

import java.util.List;

public interface StudentsRepository extends CrudeRepository<Student> {
	
	List<Student> findByAge(int age);
	

}
