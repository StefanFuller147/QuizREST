package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Question;
import entities.Quiz;

@Transactional
public class QuizDAOImpl implements QuizDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Quiz> index() {
		String query = "Select q from Quiz q";
		return em.createQuery(query, Quiz.class).getResultList();
	}

	@Override
	public Quiz show(int id) {
		return em.find(Quiz.class, id);
	}

	@Override
	public Quiz create(Quiz quiz) {
		em.persist(quiz);
		em.flush();

		return quiz;
	}

	@Override
	public Quiz update(int id, Quiz quiz) {
		Quiz managed = em.find(Quiz.class, id);
		managed.setName(quiz.getName());
		em.flush();
		return managed;
	}

	@Override
	public boolean destroy(int id) {
		Quiz managed = em.find(Quiz.class, id);
		em.remove(managed);
		em.flush();
		if (em.find(Quiz.class, id) == null) {
			return true;
		} else if (em.find(Quiz.class, id) != null) {
			return false;

		}
		return false;
	}

	@Override
	public List<Question> indexOfQuestions(int quizId) {
		String query = "Select q from Question q JOIN FETCH q.answers WHERE q.quiz.id = :id";
		return em.createQuery(query, Question.class).setParameter("id", quizId).getResultList();
	}

	@Override
	public Question createNewQuestion(int quizId, Question question) {
		Quiz q = em.find(Quiz.class, quizId);
		question.setQuiz(q);
		
		em.persist(question);
		em.flush();
		
		return question;
	}

	@Override
	public boolean destroyQuestion(int id, int questionId) {
		Quiz q = em.find(Quiz.class, id);
		Question managed = em.find(Question.class, questionId);
		q.getQuestion().remove(managed);
		em.persist(q);
		em.remove(managed);
		em.flush();
		if (em.find(Question.class, questionId) == null) {
			return true;
		} else if (em.find(Question.class, questionId) != null) {
			return false;

		}
		return false;
	}

}
