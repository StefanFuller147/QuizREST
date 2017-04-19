package data;

import java.util.List;

import entities.Question;
import entities.Quiz;

public interface QuizDAO {
	//methods for CRUD quizzes
	public List<Quiz> index();
	public Quiz show(int id);
	public Quiz create(Quiz quiz);
	public Quiz update(int id, Quiz quiz);
	public boolean destroy(int id);
	
	//methods for CRUD questions
	public List<Question> indexOfQuestions(int quizId);
	public Question createNewQuestion(int quizId, Question question);
	public boolean destroyQuestion(int id, int questionId);

	
	
	//methods for CRUD answers
	
	

}
