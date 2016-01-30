package com.eigen.impl.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.eigen.iface.dao.ProfileDao;
import com.eigen.model.MProfile;

@Repository
public class ProfileDaoImpl implements ProfileDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public void doSave(MProfile o) {
		hibernateTemplate.save(o);
	}

	@Override
	@Transactional
	public void doUpdate(MProfile o) {
		hibernateTemplate.update(o);
	}

	@Override
	@Transactional
	public void doDelete_byRicName(String sRicName) {
		MProfile o = get_byRicName(sRicName);
		hibernateTemplate.delete(o);
	}

	@Override
	@Transactional
	public MProfile get_byRicName(String sRicName) {
		String hql = "from MProfile p"
				+ " where"
				+ " (lower(p.ric_name) = lower(:ric_name))";
		String[] names = {"ric_name"};
		Object[] values = {sRicName};
		@SuppressWarnings("unchecked")
		List<MProfile> ls = (List<MProfile>) hibernateTemplate.findByNamedParam(hql, names, values);
		return (ls.size() == 1) ? ls.get(0) : null;
	}

}
