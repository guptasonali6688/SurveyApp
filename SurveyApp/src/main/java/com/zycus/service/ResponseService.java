package com.zycus.service;

import javax.servlet.http.HttpServletRequest;

import com.zycus.dto.ResponseDTO;
import com.zycus.entity.Response;

public interface ResponseService {

	void saveResponse(ResponseDTO responseDTO, HttpServletRequest request);

	Iterable<Response> getResponse(int user_id, int survey_id);
}
