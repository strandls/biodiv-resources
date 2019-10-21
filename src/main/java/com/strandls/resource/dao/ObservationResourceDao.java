/**
 * 
 */
package com.strandls.resource.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
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

}
