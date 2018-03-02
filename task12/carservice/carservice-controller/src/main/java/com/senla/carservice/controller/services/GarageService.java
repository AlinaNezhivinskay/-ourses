package com.senla.carservice.controller.services;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

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
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			garageDAO.create(session, garage);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		session.close();
	}

	@Override
	public synchronized boolean removeGarage(Garage garage) throws Exception {
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			result = garageDAO.delete(session, garage);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		session.close();
		return result;
	}

	@Override
	public synchronized boolean updateGarage(Garage garage, boolean isFree) throws Exception {
		garage.setIsFree(isFree);
		boolean result = false;
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			result = garageDAO.update(session, garage);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		session.close();
		return result;
	}

	@Override
	public int getFreeGarageNumber() throws Exception {
		int result = 0;
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			result = garageDAO.getFreeGarageNum(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		session.close();
		return result;
	}

	@Override
	public List<Garage> getFreeGarages() throws Exception {
		List<Garage> garages;
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			garages = garageDAO.getFreeGarages(session);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		session.close();
		return garages;
	}

	@Override
	public List<Garage> getGarages() throws Exception {
		List<Garage> garages;
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			garages = garageDAO.getAll(Garage.class, session, null);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		session.close();
		return garages;
	}

	@Override
	public Garage getGarageById(Long id) throws Exception {
		Garage garage;
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			garage = garageDAO.read(Garage.class, session, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		session.close();
		return garage;
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
		Session session = hibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			for (Garage garage : garages) {
				garageDAO.saveOrUpdate(session, garage);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		session.close();
		return true;
	}

}
