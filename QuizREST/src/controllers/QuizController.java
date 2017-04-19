package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.QuizDAO;
import entities.Question;
import entities.Quiz;

@RestController
public class QuizController {

	@Autowired
	private QuizDAO quizDAO;

	@RequestMapping(value = "ping", method = RequestMethod.GET)
	public String ping() {
		return "I LIKE WHAT YOU GOT!";
	}
	
	//Show a list of quizzes
	@RequestMapping(value = "quizzes", method = RequestMethod.GET)
	public List<Quiz> index() {
		return quizDAO.index();
	}
	
	//Get one quiz by ID
	@RequestMapping(value = "quizzes/{id}", method = RequestMethod.GET)
	public Quiz show(@PathVariable int id) {
		return quizDAO.show(id);
	}
	
	//create a new quiz
	@RequestMapping(value = "quizzes", method = RequestMethod.POST)
	public Quiz create(@RequestBody String jsonQuiz, HttpServletResponse res){
		res.setStatus(201);
		System.out.println(jsonQuiz);
		try{
			ObjectMapper mapper = new ObjectMapper();
			Quiz mappedQuiz=mapper.readValue(jsonQuiz, Quiz.class);
			return quizDAO.create(mappedQuiz);
			
		}catch(Exception e){
			System.out.println("WHOOPS! You goofed");
			e.printStackTrace();
		}
		return null;
	}
	
	//update an existing quiz
	@RequestMapping(value = "quizzes/{id}", method = RequestMethod.PUT)
	public Quiz update(@PathVariable int id, @RequestBody String jsonUpdateQuiz,
			HttpServletResponse res){
		res.setStatus(201);
		System.out.println(jsonUpdateQuiz);
		try{
			ObjectMapper mapper = new ObjectMapper();
			Quiz mappedQuiz=mapper.readValue(jsonUpdateQuiz, Quiz.class);
			return quizDAO.update(id, mappedQuiz);
			
		}catch(Exception e){
			System.out.println("WHOOPS! You goofed");
			e.printStackTrace();
		}
		return null;
	
	}
	
	//delete an existing quiz
	@RequestMapping(value="quizzes/{id}", method = RequestMethod.DELETE)
	public boolean destroy(@PathVariable int id){
		return quizDAO.destroy(id);
	}
	
	//gets all questions from a quiz
	@RequestMapping(value="quizzes/{id}/questions", method = RequestMethod.GET)
	public List<Question> indexOfQuestions(@PathVariable("id")int quizId){
		return quizDAO.indexOfQuestions(quizId);
	}
	
	//creates a new question for a quiz
	@RequestMapping(value="quizzes/{id}/questions", method = RequestMethod.POST)
	public Question createNewQuestion(@PathVariable("id") int quizId, @RequestBody String jsonQuestion,
			HttpServletResponse res){
		res.setStatus(201);
		try{
			ObjectMapper mapper = new ObjectMapper();
			Question mappedQuestion=mapper.readValue(jsonQuestion, Question.class);
			return quizDAO.createNewQuestion(quizId,mappedQuestion);
			
		}catch(Exception e){
			System.out.println("WHOOPS! You goofed");
			e.printStackTrace();
		}
		return null;
	}
	//Deletes a question from a quiz
	@RequestMapping(value="quizzes/{id}/questions/{questionId}", method = RequestMethod.DELETE)
	public boolean destroyQuestion(@PathVariable("id") int id, @PathVariable("questionId") int questionId){	
		return quizDAO.destroyQuestion(id, questionId);
	}
}
