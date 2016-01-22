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
	public void doDelete_bySymbol(String sSymbol) {
		List<MHistData> ls = getHistData_all_bySymbol(sSymbol);
		hibernateTemplate.deleteAll(ls);
	}

	@Override
	@Transactional
	public List<MHistData> getHistData_all_bySymbol(String sSymbol) {
		String hql = "from MHistData d"
				+ " where"
				+ " (d.symbol = :symbol)";
		String[] names = {"symbol"};
		Object[] values = {sSymbol};
		@SuppressWarnings("unchecked")
		List<MHistData> ls = (List<MHistData>) hibernateTemplate.findByNamedParam(hql, names, values);
		return ls;
	}

	@Override
	@Transactional
	public List<MHistData> getHistData(String sSymbol, Date dtFrom, Date dtTo) {
		String hql = "from MHistData d"
				+ " where"
				+ " (d.symbol = :symbol)"
				+ " and (date(d.timestamp) >= date(:frdate))"
				+ " and (date(d.timestamp) <= date(:todate))"
				+ " order by d.symbol, d.timestamp desc";
		String[] names = {"symbol", "frdate", "todate"};
		Object[] values = {sSymbol, dtFrom, dtTo};
		@SuppressWarnings("unchecked")
		List<MHistData> ls = (List<MHistData>) hibernateTemplate.findByNamedParam(hql, names, values);
		return ls;
	}

}
