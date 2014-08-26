package cn.csbit.core.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
public abstract class SuperDAO<T> {
	protected static final Logger log = LoggerFactory.getLogger(SuperDAO.class);

	@Autowired
	private SessionFactory sessionFactory;
	protected Class<T> entityClass;

	
	@SuppressWarnings("unchecked")
	private Class<T> getEntity(){
		if(this.entityClass==null){
			this.entityClass = (Class<T>) ((ParameterizedType) getClass()
		             .getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return this.entityClass;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return ((List<T>)sessionFactory.getCurrentSession().createCriteria(this.getEntity())
				.list());
	}

	public Serializable save(T entity) {
		return sessionFactory.getCurrentSession().save(entity);
	}
	
	public void update(T entity){
		this.getSession().update(entity);
		this.getSession().flush();
		this.getSession().clear();
	}

	public void saveOrUpdate(T entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		Session session = this.getSession();
		session.delete(entity);
		session.flush();
	}

	@SuppressWarnings("unchecked")
	 public T getById(Serializable id) {
		 return (T)sessionFactory.getCurrentSession().get(this.getEntity(), id);
	 }
	 
	@SuppressWarnings("unchecked")
	 public List<T> getByProperties(String hql, Object... objects){
		 Query query = getSession().createQuery(hql);
		 int i=0;
		 for(Object object:objects){
			 query.setParameter(i++, object);
		 }
		 return query.list();
	 }
	 
	@SuppressWarnings("unchecked")
	 public T getUnique(String hql, Object... objects){
		 Query query = getSession().createQuery(hql);
		 int i=0;
		 for(Object object:objects){
			 query.setParameter(i++, object);
		 }
		 return (T)query.uniqueResult();
	 }
	 
	 public Criteria getCriteria(){
		 return this.getSession().createCriteria(this.getEntity());
	 }

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
