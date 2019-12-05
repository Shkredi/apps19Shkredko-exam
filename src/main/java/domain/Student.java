package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private List<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    public JsonObject toJsonObject() {
        JsonObject jObj = super.toJsonObject();
        List<Json> exams = new ArrayList<>();
        for (Tuple<String, Integer> exam: this.exams) {
            JsonPair course = new JsonPair("course", new JsonString(exam.key));
            JsonPair mark = new JsonPair("mark", new JsonNumber(exam.value));
            boolean passing = true;
            if (exam.value < 3){
                passing = false;
            }
            JsonPair passed = new JsonPair("passed", new JsonBoolean(passing));
            JsonObject jExam = new JsonObject(course, mark, passed);
            exams.add(jExam);
        }
        jObj.add(new JsonArray(exams);
        return jObj;
    }
}
