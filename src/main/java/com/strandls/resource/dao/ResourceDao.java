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
	public List<Resource> findByObservationId(Long obvId) {

		String qry = "from Resource R where R.id in(" + "select resourceId from ObservationResource obv "
				+ "where obv.observationId = :obvId) order by rating DESC";

		Session session = sessionFactory.openSession();
		Query<Resource> query = session.createQuery(qry);
		query.setParameter("obvId", obvId);

		List<Resource> list = query.getResultList();
		return list;
	}

}
