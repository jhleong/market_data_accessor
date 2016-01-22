package com.eigen.impl.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.eigen.iface.dao.RtProfileDao;
import com.eigen.model.MRtProfile;

@Repository
public class RtProfileDaoImpl implements RtProfileDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public void doSave(MRtProfile o) {
		hibernateTemplate.save(o);
	}

	@Override
	@Transactional
	public void doDelete_byRicname(String sRicname) {
		MRtProfile o = get_byRicname(sRicname);
		hibernateTemplate.delete(o);
	}

	@Override
	@Transactional
	public MRtProfile get_byRicname(String sRicname) {
		String hql = "from MRtProfile p"
				+ " where"
				+ " (p.ricname = :ricname)";
		String[] names = {"ricname"};
		Object[] values = {sRicname};
		MRtProfile o = (MRtProfile) hibernateTemplate.findByNamedParam(hql, names, values);
		return o;
	}

}
