package com.domain.java.first;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-7-23
 */
public class QuizCard {

    QuizCard(String question, String answer) {

        this.question = question;
        this.answer = answer;
    }

    private String question;

    private String answer;

    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {

        this.question = question;
    }

    public String getAnswer() {

        return answer;
    }

    public void setAnswer(String answer) {

        this.answer = answer;
    }
}
