package cn.csbit.core.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * 处理多表的crud和多次crud 必须放在事务中执行
 * @author zh
 *
 */
@Repository
public class BaseDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Serializable save(Object entity){
		return this.sessionFactory.getCurrentSession().save(entity);
	}
	
	public void batchSave(Collection<? extends BaseEntity> entitys){
		for(Object object: entitys){
			this.sessionFactory.getCurrentSession().save(object);
		}
	}
	
	public  void delete(Object objet){
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(objet);
		session.flush();
		//session.clear();
	}
	
	public  void update(Object objet){
		Session session = this.sessionFactory.getCurrentSession();
		session.update(objet);
		session.flush();
	}
	
	public void saveOrUpdate(Object object){
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(object);
		session.flush();
	}
	
	public void execHql(String hql, Object... objects){
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		for(int i=0; i<objects.length; i++){
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
		session.flush();
		session.clear();
	}
	
	public <T> T chekExist(Class<T> t, Criterion... expressions){
		List<T> lists = queryForList(t, expressions);
		if(lists.size()>0) return lists.get(0);
		else return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getById(Class<T> t, Serializable id){
		return (T) this.sessionFactory.getCurrentSession().get(t, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> t){
		return this.sessionFactory.getCurrentSession().createCriteria(t).list();
	}
	
	@SuppressWarnings("unchecked")
	 public <T> T queryForUnique(String hql, Object... objects ){
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		 int i=0;
		 for(Object object:objects){
			 query.setParameter(i++, object);
		 }
		 return (T)query.uniqueResult();
	 }
	
	@SuppressWarnings("unchecked")
	 public <T> T queryForUnique(Class<T> t, Criterion... expressions){
		Criteria query = this.sessionFactory.getCurrentSession().createCriteria(t);
		 for(Criterion expression: expressions){
			 query.add(expression);
		 }
		 return (T)query.uniqueResult();
	 }
	
	@SuppressWarnings("unchecked")
	 public <T> List<T> queryForList(String hql, Object... objects){
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		 int i=0;
		 for(Object object:objects){
			 query.setParameter(i++, object);
		 }
		 return query.list();
	}
	
	public <T> T getFirst(Class<T> t, Order order, Criterion... expressions){
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(t);
		for(Criterion expression: expressions){
			criteria.add(expression);
		}
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		if(order!=null) criteria.addOrder(order);
		@SuppressWarnings("unchecked")
		List<T> ts = criteria.list();
		if(ts.size()>0) return ts.get(0);
		else return null;
	}
	
	public <T> T getFirst(Class<T> t, Criterion... expressions){
		return this.getFirst(t, null, expressions);
	}
	
	/**
	 * 
	 * @param object 查询条件不含object中的null字段,0字段,主键字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getListByExample(T object){
		return this.sessionFactory.getCurrentSession().createCriteria(object.getClass()).add(Example.create(object).excludeNone()).list();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getUniqueByExample(T object){
		return (T)this.sessionFactory.getCurrentSession().createCriteria(object.getClass()).add(Example.create(object).excludeNone()).uniqueResult();
	}
	
	public <T> Long getTotal(Class<T> t, Criterion... criterions){
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(t);
		for(Criterion criterion : criterions){
			criteria.add(criterion);
		}
		criteria.setProjection(Projections.rowCount());
		return (Long)criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(Class<T> t, Order order, Criterion... criterions){
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(t);
		for(Criterion criterion : criterions){
			criteria.add(criterion);
		}
		if(order!=null) criteria.addOrder(order);
		return criteria.list();
	}
	
	public <T> List<T> queryForList(Class<T> t, Criterion... criterions){
		return this.queryForList(t, null, criterions);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(Class<T> t, int start, int max, Order order, Criterion... criterions){
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(t);
		for(Criterion criterion : criterions){
			criteria.add(criterion);
		}
		if(order!=null) criteria.addOrder(order);
		criteria.setFirstResult(start);
		criteria.setMaxResults(max);
		return criteria.list();
	}
	
	public <T> List<T> queryForList(Class<T> t, int start, int max, Criterion... criterions){
		return this.queryForList(t, start, max, null, criterions);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getTotal(Class<T> t, int start, int max, Criterion... criterions){
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(t);
		for(Criterion criterion : criterions){
			criteria.add(criterion);
		}
		criteria.setProjection(Projections.rowCount());
		return criteria.list();
	}
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
}
