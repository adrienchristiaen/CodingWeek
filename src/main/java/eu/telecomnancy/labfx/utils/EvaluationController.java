package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;

import java.util.ArrayList;
import java.util.Collections;

public class EvaluationController {
    private ArrayList<Evaluation> evaluations;
    private static EvaluationController instance;
    private EvaluationController() {
        for (User user : UserController.getInstance().getUsers()) {
            if (user.getEvaluations() != null) {
                this.evaluations.addAll(user.getEvaluations());
            }
        }
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

    public ArrayList<Evaluation> getEvaluationsByUserSorted (User user) {
        ArrayList<Evaluation> evaluationsByUser = new ArrayList<>();
        for (Evaluation evaluation : evaluations) {
            if (evaluation.getIdUser() == user.getId()) {
                evaluationsByUser.add(evaluation);
            }
        }
        Collections.sort(evaluationsByUser, (o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()));
        return evaluationsByUser;
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
