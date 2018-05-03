package com.quiz.jaxrs;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.dto.Parameter;
import com.quiz.dto.ReturnDTO;
import com.quiz.service.IUserService;

@Component
@Path("/api")
public class QuizRestService {
	private static final Logger logger = LogManager.getLogger(QuizRestService.class);

	@Autowired
	IUserService userService;

	@GET
	@Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnDTO checkStatus() {
		logger.trace("getStatus method begins.");

		ReturnDTO returnDTO = new ReturnDTO();
		returnDTO.setMessage("200");
		returnDTO.setStatus(true);
		return returnDTO;
	}

	@POST
	@Path("/postTest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnDTO postTest(@Context HttpServletRequest request) {
		logger.debug("postTest REST method begins.");
		ObjectMapper mapper = new ObjectMapper();
		ReturnDTO response = new ReturnDTO();
		try {
			String jsonData = IOUtils.toString(request.getInputStream());
			logger.debug("Request:\n" + jsonData);
			JSONObject jsonObject = new JSONObject(jsonData);
			Parameter requestobj = mapper.readValue(jsonObject.toString(), Parameter.class);
			logger.debug("Response:\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
		} catch (Exception e) {
			logger.error(e, e);
		}

		logger.debug("postTest REST method completed.");
		return response;
	}

	@GET
	@Path("/getTest")
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnDTO getTest() {
		logger.debug("getTest REST method begins.");
		ReturnDTO returnDTO = new ReturnDTO();
		returnDTO.setMessage("200");
		returnDTO.setStatus(true);
		logger.debug("getTest REST method completed.");
		return returnDTO;
	}

}
