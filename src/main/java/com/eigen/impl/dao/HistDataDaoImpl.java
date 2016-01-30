package com.eigen.impl.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.eigen.iface.dao.HistDataDao;
import com.eigen.model.MHistData;

@Repository
public class HistDataDaoImpl implements HistDataDao {
	
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
	public List<MHistData> getHistData_byProfileId_byDate(long nProfile_id, Date dtFrom, Date dtTo) {
		String hql = "from MHistData d"
				+ " where"
				+ " (d.profile_id = :profile_id)"
				+ " and (date(d.dt) >= date(:frdate))"
				+ " and (date(d.dt) <= date(:todate))"
				+ " order by d.dt desc";
		String[] names = {"profile_id", "frdate", "todate"};
		Object[] values = {nProfile_id, dtFrom, dtTo};
		@SuppressWarnings("unchecked")
		List<MHistData> ls = (List<MHistData>) hibernateTemplate.findByNamedParam(hql, names, values);
		return ls;
	}

}
