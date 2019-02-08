package com.bridgeit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.Label;
import com.bridgeit.model.Note;

@Repository
public class LabelDaoImpl implements LabelDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public boolean addlabel(Label label) {
		// TODO Auto-generated method stub
		System.out.println("dao" + label);
		getCurrentSession().save(label);

		return true;
	}

	@Override
	public List<Label> getLabels(int id) {
		// TODO Auto-generated method stub
		List<Label> list = getCurrentSession().createCriteria(Label.class).createCriteria("noteList")
				.add(Restrictions.eq("id", id)).list();
		System.out.println(list);
		return list;
	}

}
