package cn.csbit.core.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用于处理单一表的crud
 * @author zh
 *
 */
@Service
public class BaseService {
	@Autowired
	private BaseDAO rootDAO;
	
	private static BaseService baseService = null;
	
	public static BaseService getBaseService(){
		return baseService;
	}
	
	public BaseService(){
		baseService = this;
	}
	
	@Transactional
	public Serializable add(Object entity){
		return this.rootDAO.save(entity);
	}
	
	@Transactional
	public void addBatch(Collection<Object> entList){
		for(Object object:entList){
			this.rootDAO.save(object);
		}
	}
	
	@Transactional
	public  void delete(Object objet){
		this.rootDAO.delete(objet);
	}
	
	@Transactional
	public void execBatchHql(String... hqls){
		for(String hql: hqls){
			this.rootDAO.execHql(hql);
		}
	}
	
	@Transactional
	public void execHql(String hql, Object... objects){
		this.rootDAO.execHql(hql, objects);
	}
	
	@Transactional(readOnly=true)
	public <T> List<T> getAll(Class<T> t){
		return this.rootDAO.getAll(t);
	}
	
	@Transactional(readOnly=true)
	public <T> T getById(Class<T> t, Serializable id){
		return (T) this.rootDAO.getById(t, id);
	}
	
	/**
	 * 
	 * @param start 起始位置 从1开始
	 * @param max 最多获取的行数
	 * @return
	 */
	@Transactional(readOnly=true)
	public <T> List<T> getBySize(Class<T> t, int start, int max, Criterion... criterions){
		return this.rootDAO.queryForList(t, start-1, max, null, criterions);
	}
	
	@Transactional(readOnly=true)
	public <T> List<T> getBySize(Class<T> t, int start, int max, Order order, Criterion... criterions){
		return this.rootDAO.queryForList(t, start-1, max, order, criterions);
	}
	
	@Transactional(readOnly=true)
	public <T> T getFirst(Class<T> t, Criterion... expressions){
		return rootDAO.getFirst(t, null, expressions);
	}
	
	@Transactional(readOnly=true)
	public <T> T getFirst(Class<T> t, Order order, Criterion... expressions){
		return rootDAO.getFirst(t, order, expressions);
	}
	
	/**
	 * 
	 * @param object 查询条件不含object中的null字段,0字段,主键字段
	 * @return
	 */
	@Transactional(readOnly=true)
	public <T> List<T> getListByExample(T object){
		return this.rootDAO.getListByExample(object);
	}
	
	/**
	 * 分页查询
	 * @param t
	 * @param pageSize
	 * @param pageNo 页数从1开始
	 * @param criterions
	 * @return
	 */
	@Transactional(readOnly=true)
	public <T> Page<T> getPage(Class<T> t, int pageNo, int pageSize, Criterion... criterions){
		Page<T> page = new Page<T>();
		page.setCount(this.rootDAO.getTotal(t, criterions));
		page.setLists(this.rootDAO.queryForList(t, (pageNo-1)*pageSize, pageSize, null, criterions));
		return page;
	}
	
	@Transactional(readOnly=true)
	public <T> Page<T> getPage(Class<T> t, int pageNo, int pageSize, Order order, Criterion... criterions){
		Page<T> page = new Page<T>();
		page.setCount(this.rootDAO.getTotal(t, criterions));
		page.setLists(this.rootDAO.queryForList(t, (pageNo-1)*pageSize, pageSize, order, criterions));
		return page;
	}
	@Transactional(readOnly=true)
	public <T> T getUniqueByExample(T object){
		return (T)this.rootDAO.getUniqueByExample(object);
	}
	
	@Transactional(readOnly=true)
	 public <T> List<T> queryForList(Class<T> t, Criterion... expressions){
		return rootDAO.queryForList(t, null, expressions);
	 }

	
	@Transactional(readOnly=true)
	 public <T> List<T> queryForList(Class<T> t, Order order, Criterion... expressions){
		return rootDAO.queryForList(t, order, expressions);
	 }
	
	@Transactional(readOnly=true)
	 public <T> List<T> queryForList(String hql, Object... objects){
		return rootDAO.queryForList(hql, objects);
	}
	
	@Transactional(readOnly=true)
	 public <T> T queryForUnique(Class<T> t, Criterion... expressions){
		return rootDAO.queryForUnique(t, expressions);
	 }
	
	@Transactional(readOnly=true)
	 public <T> T queryForUnique(String hql, Object... objects ){
		return rootDAO.queryForUnique(hql, objects);
	 }
	
	@Transactional
	public void saveOrUpdate(Object object){
		this.rootDAO.saveOrUpdate(object);
	}
	
	@Transactional
	public  void update(Object objet){
		this.rootDAO.update(objet);
	}
}
