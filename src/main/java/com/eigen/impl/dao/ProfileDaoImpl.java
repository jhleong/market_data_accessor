package com.eigen.impl.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.eigen.constant.DataProvider;
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
	public void doDelete_bySymbol(DataProvider dataProvider, String sSymbol) {
		MProfile o = get_bySymbol(dataProvider, sSymbol);
		hibernateTemplate.delete(o);
	}

	@Override
	@Transactional
	public MProfile get_bySymbol(DataProvider dataProvider, String sSymbol) {
		int data_provider_enum = dataProvider.getId();
		String hql = "from MProfile p"
				+ " where"
				+ " (lower(p.symbol) = lower(:symbol)) and "
				+ " p.data_provider =  (:data_provider)";
		
		String[] names = {"symbol", "data_provider"};
		Object[] values = {sSymbol, data_provider_enum};
		@SuppressWarnings("unchecked")
		List<MProfile> ls = (List<MProfile>) hibernateTemplate.findByNamedParam(hql, names, values);
		return (ls.size() == 1) ? ls.get(0) : null;
	}

}
