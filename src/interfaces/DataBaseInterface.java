package interfaces;

import java.util.List;

public interface DataBaseInterface<T>
{
	void Insert(T entity);
	List<T> GetAll();
	T Delete(T entity);
	void Update(T entity);
	List<T> GetById(int id);	
}