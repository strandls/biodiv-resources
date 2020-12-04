/**
 * 
 */
package com.strandls.resource.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import com.strandls.resource.pojo.Resource;
import com.strandls.resource.util.AbstractDAO;

/**
 * @author Abhishek Rudra
 *
 */
public class ResourceDao extends AbstractDAO<Resource, Long> {

	private static final Logger logger = LoggerFactory.getLogger(ResourceDao.class);

	/**
	 * @param sessionFactory
	 */
	@Inject
	protected ResourceDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Resource findById(Long id) {
		Session session = sessionFactory.openSession();
		Resource entity = null;
		try {
			entity = session.get(Resource.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<Resource> findByObjectTypeObjectId(String objectType, List<Long> objectIds) {

		String qry = "from Resource R where R.context = :objectType and R.id in(:objectIds) order by rating DESC";
		List<Resource> result = null;
		Session session = sessionFactory.openSession();
		try {
			Query<Resource> query = session.createQuery(qry);
			query.setParameter("objectType", objectType);
			query.setParameter("objectIds", objectIds);

			result = query.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}

		return result;
	}

}
