package eu.telecomnancy.labfx.utils;

import java.util.ArrayList;

public class EvaluationController {
    private ArrayList<Evaluation> evaluations;
    private static EvaluationController instance;
    private EvaluationController() {
        this.evaluations = new ArrayList<>();
    }
    public static EvaluationController getInstance() {
        if (instance == null) {
            instance = new EvaluationController();
        }
        return instance;
    }
    public ArrayList<Evaluation> getEvaluations() {
        return evaluations;
    }
    public void setEvaluations(ArrayList<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
    public int getMaxId() {
        int max = 0;
        for (Evaluation evaluation : evaluations) {
            if (evaluation.getId() > max) {
                max = evaluation.getId();
            }
        }
        return max;
    }
    public void addEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }
    public void removeEvaluation(Evaluation evaluation) {
        evaluations.remove(evaluation);
    }
    public Evaluation get(int id) {
        for (Evaluation evaluation : evaluations) {
            if (evaluation.getId() == id) {
                return evaluation;
            }
        }
        return null;
    }

}
