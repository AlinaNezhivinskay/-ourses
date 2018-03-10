package com.senla.carservice.controller.services;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.senla.carservice.api.dao.IGarageDAO;
import com.senla.carservice.api.services.IGarageService;
import com.senla.carservice.connection.HibernateUtil;
import com.senla.carservice.csv.fileworker.CsvFileWorker;
import com.senla.carservice.dependencyinjection.coordinationrepository.CoordinationRepository;
import com.senla.carservice.model.beans.Garage;

public class GarageService implements IGarageService {
	private IGarageDAO garageDAO;
	private final HibernateUtil hibernateUtil = HibernateUtil.getInstance();

	public GarageService() {
		garageDAO = (IGarageDAO) CoordinationRepository.getInstance().getObject(IGarageDAO.class);
	}

	@Override
	public synchronized void addGarage(Garage garage) throws Exception {
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			garageDAO.create(session, garage);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public synchronized boolean removeGarage(Garage garage) throws Exception {
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = garageDAO.delete(session, garage);
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
	public synchronized boolean updateGarage(Garage garage, boolean isFree) throws Exception {
		garage.setIsFree(isFree);
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = garageDAO.update(session, garage);
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
	public int getFreeGarageNumber() throws Exception {
		int result = 0;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			result = garageDAO.getFreeGarageNum(session);
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
	public List<Garage> getFreeGarages() throws Exception {
		List<Garage> garages;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			garages = garageDAO.getFreeGarages(session);
			transaction.commit();
			return garages;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public List<Garage> getGarages() throws Exception {
		List<Garage> garages;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			garages = garageDAO.getAll(Garage.class, session, null);
			transaction.commit();
			return garages;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public Garage getGarageById(Long id) throws Exception {
		Garage garage;
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			garage = garageDAO.read(Garage.class, session, id);
			transaction.commit();
			return garage;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	@Override
	public boolean exportGarages(List<Garage> garages)
			throws IllegalAccessException, IOException, NoSuchFieldException {
		return CsvFileWorker.safeToFile(garages);
	}

	@Override
	public synchronized boolean importGarages() throws Exception {
		@SuppressWarnings("unchecked")
		List<Garage> garages = (List<Garage>) CsvFileWorker.readFromFileGarage();
		Session session = hibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			for (Garage garage : garages) {
				garageDAO.saveOrUpdate(session, garage);
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
