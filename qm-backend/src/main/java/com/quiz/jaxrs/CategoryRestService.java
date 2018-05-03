package com.quiz.jaxrs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.quiz.dto.AnswerDto;
import com.quiz.dto.CategoryDto;
import com.quiz.dto.QuestionDto;
import com.quiz.dto.ReturnDTO;
import com.quiz.service.IQuizSerice;

@CrossOrigin(origins = "*")
@Component
@Path("/api/category")
public class CategoryRestService {

	private static final Logger logger = LogManager.getLogger(CategoryRestService.class);

	@Autowired
	IQuizSerice quizSerice;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@Context HttpServletRequest request, CategoryDto categoryDto) {
		logger.debug("save REST method begins.");
		ReturnDTO response = new ReturnDTO();
		try {
			quizSerice.save(categoryDto);

			response.setStatus(true);
			response.setMessage("Kayit basarili.");
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Kategori kaydinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("save REST method completed.");
		return Response.ok(response).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").build();
	}
	
	@POST
	@Path("/saveQuestion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveQuestion(@Context HttpServletRequest request, QuestionDto questionDto) {
		logger.debug("saveQuestion REST method begins.");
		ReturnDTO response = new ReturnDTO();
		try {
			quizSerice.saveQuestion(questionDto);

			response.setStatus(true);
			response.setMessage("Kayit basarili.");
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Kategori kaydinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("saveQuestion REST method completed.");
		return Response.ok(response).build();
	}
	
	@POST
	@Path("/saveAnswer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveAnswer(@Context HttpServletRequest request, AnswerDto answerDto) {
		logger.debug("saveAnswer REST method begins.");
		ReturnDTO response = new ReturnDTO();
		try {
			quizSerice.saveAnswer(answerDto);

			response.setStatus(true);
			response.setMessage("Kayit basarili.");
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Kategori kaydinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("saveAnswer REST method completed.");
		return Response.ok(response).build();
	}
	
	@POST
	@Path("/saveAnswers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveAnswers(@Context HttpServletRequest request, List<AnswerDto> answerDtos) {
		logger.debug("saveAnswer REST method begins.");
		ReturnDTO response = new ReturnDTO();
		try {
			quizSerice.saveAnswers(answerDtos);

			response.setStatus(true);
			response.setMessage("Kayit basarili.");
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage("Kategori kaydinda bir hata olustu. Hata:" + e.getMessage());
		}

		logger.debug("saveAnswer REST method completed.");
		return Response.ok(response).build();
	}

	@GET
	@Path("/getCategories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategories() {
		logger.debug("getCategories REST method begins.");
		ReturnDTO response = new ReturnDTO();

		try {
			List<CategoryDto> categories = quizSerice.getCategories();

			response.setStatus(true);
			response.setResult(categories);
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage(e.getMessage());
		}

		// GenericEntity<TSEDetailDto> entity = new
		// GenericEntity<TSEDetailDto>(detailDto, TSEDetailDto.class) {
		// };

		logger.debug("getCategories REST method completed.");
		return Response.ok(response).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").allow("OPTIONS").build();
	}

	@GET
	@Path("/getQuestionsByCategoryId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionsByCategoryId(@Context HttpServletRequest request, @PathParam("id") Integer id) {
		logger.debug("getQuestionsByCategoryId REST method begins. id:" + id);
		ReturnDTO response = new ReturnDTO();

		try {
			List<QuestionDto> questionDtos = quizSerice.getQuestionsByCategoryId(id);
 
			response.setStatus(true);
			response.setResult(questionDtos);
		} catch (Exception e) {
			logger.error(e, e);
			response.setStatus(false);
			response.setMessage(e.getMessage());
		}

		logger.debug("getQuestionsByCategoryId REST method completed.");
		return Response.ok(response).build();
	}

}
