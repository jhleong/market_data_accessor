package com.eigen.impl.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.eigen.iface.dao.HistDataDao;
import com.eigen.model.MHistData;

@Repository
public class HistDataDaoImpl implements HistDataDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public void doSave(MHistData o) {
		hibernateTemplate.save(o);
	}

	@Override
	@Transactional
	public void doDelete_byProfileId(long nProfile_id) {
		List<MHistData> ls = getHistData_all_byProfileId(nProfile_id);
		hibernateTemplate.deleteAll(ls);
	}

	@Override
	@Transactional
	public List<MHistData> getHistData_all_byProfileId(long nProfile_id) {
		String hql = "from MHistData d"
				+ " where"
				+ " (d.profile_id = :profile_id)";
		String[] names = {"profile_id"};
		Object[] values = {nProfile_id};
		@SuppressWarnings("unchecked")
		List<MHistData> ls = (List<MHistData>) hibernateTemplate.findByNamedParam(hql, names, values);
		return ls;
	}

	@Override
	@Transactional
	public List<MHistData> getHistData_byProfileId_byType_byDate(long nProfile_id, String sType, Date dtFrom, Date dtTo) {
		//
		Calendar cFrom = Calendar.getInstance();
		cFrom.setTime(dtFrom);
		Calendar cTo = Calendar.getInstance();
		cTo.setTime(dtTo);
		//
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MHistData.class)
				.add(Restrictions.eq("profile_id", nProfile_id))
				.add(Restrictions.ge("ts", cFrom))
				.add(Restrictions.le("ts", cTo))
				.addOrder(Order.asc("ts"));
		//
		@SuppressWarnings("unchecked")
		List<MHistData> ls = (List<MHistData>) criteria.list();
		return ls;
	}

}
