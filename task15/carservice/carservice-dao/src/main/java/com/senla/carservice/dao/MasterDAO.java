package com.senla.carservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.senla.carservice.api.dao.IMasterDAO;
import com.senla.carservice.model.beans.Master;

public class MasterDAO extends AbstractDAO<Master> implements IMasterDAO {

	@Override
	public int getFreeMasterNum(Session session) {
		int freeMasterNum = 0;
		Criteria criteria = session.createCriteria(Master.class);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("isFree", true));
		List result = criteria.list();
		if (result != null) {
			freeMasterNum = (int) result.get(0);
		}

		return freeMasterNum;
	}

	@Override
	public List<Master> getFreeMasters(Session session) {
		Criteria criteria = session.createCriteria(Master.class);
		criteria.add(Restrictions.eq("isFree", true));
		return criteria.list();
	}

}
