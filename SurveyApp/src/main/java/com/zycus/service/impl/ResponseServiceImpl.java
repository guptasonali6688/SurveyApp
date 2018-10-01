package com.zycus.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.dto.ResponseDTO;
import com.zycus.entity.Response;
import com.zycus.entity.User;
import com.zycus.repository.ResponseRepository;
import com.zycus.repository.ShareSurveyRepository;
import com.zycus.service.ResponseService;

@Service
@Transactional
public class ResponseServiceImpl implements ResponseService {

	@Autowired
	ResponseRepository responseRepository;

	@Autowired
	private ShareSurveyRepository shareSurveyRepository;

	public void saveResponse(ResponseDTO responseDTO, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		shareSurveyRepository.setResponseStatus(responseDTO.getSurvey().getUser().getId(), user.getId(),
				responseDTO.getSurvey().getId());

		for (Response res : responseDTO.getResponses()) {
			System.out.println(res);
			responseRepository.save(res);
		}
	}

	public Iterable<Response> getResponse(int user_id, int survey_id) {
		return responseRepository.getResponseSurvey(user_id, survey_id);
	}
}
