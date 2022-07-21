package com.resultservice.result;

import java.util.List;

public interface CustomResultRepo {
	List<Result> getResultByCategory(String category);

	List<Result> getAllResultsByUserID(String id);

	void deleteResultByUserID(int userId);

}
