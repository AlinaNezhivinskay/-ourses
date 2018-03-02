package com.senla.carservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.senla.carservice.api.dao.IGarageDAO;
import com.senla.carservice.model.beans.Garage;

public class GarageDAO extends AbstractDAO<Garage> implements IGarageDAO {

	@Override
	public int getFreeGarageNum(Session session) {
		int freeGarageNum = 0;
		Criteria criteria = session.createCriteria(Garage.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("isFree", true));
		List result = criteria.list();
		if (result != null) {
			freeGarageNum = (int) result.get(0);
		}

		return freeGarageNum;
	}

	@Override
	public List<Garage> getFreeGarages(Session session) {
		Criteria criteria = session.createCriteria(Garage.class);
		criteria.add(Restrictions.eq("isFree", true));
		return criteria.list();
	}
}
