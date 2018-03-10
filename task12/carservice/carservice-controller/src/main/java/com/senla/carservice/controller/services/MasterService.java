package com.senla.carservice.controller.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.carservice.api.dao.IMasterDAO;
import com.senla.carservice.api.services.IMasterService;
import com.senla.carservice.connection.HibernateUtil;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.dao.MasterDAO;
import com.senla.carservice.model.beans.Master;
import com.senla.carservice.model.sortfields.master.SortMasterFields;

public class MasterService implements IMasterService {
	private IMasterDAO masterDAO;
	private final HibernateUtil hibernateUtil = HibernateUtil.getInstance();

	public MasterService() {
		this.masterDAO = new MasterDAO();
	}

	@Override
	public synchronized void addMaster(Master master) throws Exception {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			masterDAO.create(session, master);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public synchronized boolean removeMaster(Master master) throws Exception {
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = masterDAO.delete(session, master);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public synchronized boolean updateMaster(Master master, boolean isFree) throws Exception {
		master.setIsFree(isFree);
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = masterDAO.update(session, master);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public List<Master> getMasters() throws Exception {
		List<Master> masters;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			masters = masterDAO.getAll(Master.class, session, null);
			transaction.commit();
			return masters;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public int getFreeMastersNumber(Date date) throws Exception {
		int result = 0;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = masterDAO.getFreeMasterNum(session);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public List<Master> sort(SortMasterFields field) throws Exception {
		List<Master> masters;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			masters = masterDAO.getAll(Master.class, session, field.name());
			transaction.commit();
			return masters;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}

	}

	@Override
	public Master getMasterById(Long id) throws Exception {
		Master master;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			master = masterDAO.read(Master.class, session, id);
			transaction.commit();
			return master;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public List<Master> getFreeMasters() throws Exception {
		List<Master> masters;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			masters = masterDAO.getFreeMasters(session);
			transaction.commit();
			return masters;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public boolean exportMasters(List<Master> masters)
			throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(masters);
	}

	@Override
	public synchronized boolean importMasters() throws Exception {
		@SuppressWarnings("unchecked")
		List<Master> masters = (List<Master>) CsvFileWorker.readFromFileMaster();
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			for (Master master : masters) {
				masterDAO.saveOrUpdate(session, master);
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

}
