package com.senla.carservice.controller.services;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.carservice.api.dao.IAuditDAO;
import com.senla.carservice.api.services.IAuditService;
import com.senla.carservice.connection.HibernateUtil;
import com.senla.carservice.dao.AuditDAO;
import com.senla.carservice.model.beans.Audit;

public class AuditService implements IAuditService {
	private static Logger log = Logger.getLogger(AuditService.class.getName());
	private IAuditDAO auditDAO;
	private final HibernateUtil hibernateUtil = HibernateUtil.getInstance();

	public AuditService() {
		auditDAO = new AuditDAO();
	}

	@Override
	public void addAudit(Audit audit) {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			auditDAO.create(session, audit);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("Exception", e);
		}
	}

	@Override
	public boolean removeAudit(Audit audit) {
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = auditDAO.delete(session, audit);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error("Exception", e);
		}
		return result;
	}

}
