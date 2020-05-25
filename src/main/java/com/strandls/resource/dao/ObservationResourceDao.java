/**
 * 
 */
package com.strandls.resource.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import com.strandls.resource.pojo.ObservationResource;
import com.strandls.resource.util.AbstractDAO;

/**
 * @author Abhishek Rudra
 *
 */
public class ObservationResourceDao extends AbstractDAO<ObservationResource, Long> {

	private final Logger logger = LoggerFactory.getLogger(ObservationResourceDao.class);

	/**
	 * @param sessionFactory
	 */
	@Inject
	protected ObservationResourceDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public ObservationResource findById(Long id) {
		Session session = sessionFactory.openSession();
		ObservationResource entity = null;
		try {
			entity = session.get(ObservationResource.class, id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public ObservationResource findByPair(Long observationId, Long resourceId) {
		Session session = sessionFactory.openSession();
		String qry = "from ObservationResource where observationId = :observationId and resourceId = :resourceId";
		ObservationResource result = null;
		try {
			Query<ObservationResource> query = session.createQuery(qry);
			query.setParameter("observationId", observationId);
			query.setParameter("resourceId", resourceId);
			result = query.getSingleResult();

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return result;
	}

}
