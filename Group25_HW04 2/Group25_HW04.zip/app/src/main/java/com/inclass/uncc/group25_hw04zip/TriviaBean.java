package com.inclass.uncc.group25_hw04zip;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rishi on 27/09/17.
 */

public class TriviaBean implements Serializable{
    int id;
    String question;
    String image;

    @Override
    public String toString() {
        return "TriviaBean{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", image='" + image + '\'' +
                ", answer=" + answer +
                ", choices='" + choices + '\'' +
                '}';
    }
    /*public TriviaBean createQuestion(JSONObject js) throws JSONException {
        TriviaBean question=new TriviaBean();
        question.setId(js.getInt("id"));
        question.setQuestion(js.optString("text"));

        question.setImage(js.optString("image"));
       return question;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    int answer;
   String choices;


}
