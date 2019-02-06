package com.bridgeit.dao;

import java.util.List;

import com.bridgeit.model.Label;

public interface LabelDAO {

	boolean addlabel(Label label);

	List<Label> getLabels(int id);

}
