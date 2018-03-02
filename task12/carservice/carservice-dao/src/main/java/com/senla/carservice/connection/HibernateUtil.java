package com.senla.carservice.connection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	private SessionFactory sessionFactory = null;
	private static HibernateUtil instance;

	public static HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
		}
		return instance;
	}

	private HibernateUtil() {
		build();
	}

	private void build() {
		Configuration config = new Configuration();
		config.configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());

		sessionFactory = config.buildSessionFactory(builder.build());
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			build();
		}
		return sessionFactory;
	}
}
