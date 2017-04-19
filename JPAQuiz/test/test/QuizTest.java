package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Answer;
import entities.Question;
import entities.Quiz;

public class QuizTest {
	EntityManagerFactory emf = null;
	EntityManager em = null;
	
	@Before
	public void setUp(){
		emf = Persistence.createEntityManagerFactory("Quiz");
		em = emf.createEntityManager();
	}
	
	@Test
	public void test(){
		boolean willPass = true;
		assertEquals(willPass, true);
	}
	
	@Test
	public void getQuiz(){
		Quiz quiz = em.find(Quiz.class, 1);
		assertEquals("updated quiz 1", quiz.getName());
		
	}
	
	@Test
	public void getQuesionFromQuestionId(){
		Question question = em.find(Question.class, 1);
		assertEquals("What is the smallest state in the US", question.getQuestionText());
	}
	
	
	@Test
	public void getAnswers(){
		Answer answer = em.find(Answer.class, 1);
		assertEquals("Deleware", answer.getAnswerText());
	}
	
	@After
	public void tearDown(){
		em.close();
		emf.close();
	}
}
