package com.bridgeit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.LabelDAO;
import com.bridgeit.model.Label;


@Service
@Transactional
public class LabelServiceImpl implements ILabelService {

	@Autowired
	private LabelDAO labelDAO;
	
	
	
	@Override
	public boolean addLabel(Label label) {
		
	System.out.println("LIST of NOTE"	+label.getNoteList());
	
		boolean check=labelDAO.addlabel(label);
		
		return true;
	}

}
