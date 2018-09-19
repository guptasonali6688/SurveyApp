	package com.zycus.DAO;

	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.EntityTransaction;
	import javax.persistence.PersistenceContext;
	import javax.persistence.Query;

	import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


	//import com.zycus.util.HibernateUtil;

	//@Component("GenericDAO")
	@Repository //alternative for Component
	@Transactional
	public class GenericRepository //Create,Read,Update,Delete
	{
		
		@PersistenceContext //for injecting the Entitymanager, @Autowired doesn't work
		private EntityManager entityManager; 
		
		
		
		public void add(Object object)
		{
			
			
			
			entityManager.persist(object);
			
			
			
		}
		
		public void update(Object object)
		{
			
			
			
			entityManager.merge(object);
			
			
			
		}
		
		
		public void remove(Class clazz,Object id)
		{
			
			
			Object object= (entityManager.find(clazz, id));
			entityManager.remove(object);
			
			
			
		}
		public <E> E fetchById(Class<E> clazz,Object id){
			
			
					//get method generates select query
				E e=entityManager.find(clazz, id);
					return e;
				
		}

		
		
		public <E> List<E> fetchall(Class<E> clazz)
		{
			
			
			
			return entityManager.createQuery("select obj from "+clazz.getName()+" as obj").getResultList();
			
			
		}


	}



