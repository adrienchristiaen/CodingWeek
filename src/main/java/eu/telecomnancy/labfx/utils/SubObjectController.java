package eu.telecomnancy.labfx.utils;

public class SubObjectController {
    private int NbOfMaterial;
    private int NbOfService;
    private int NbOfRatings;
    private static SubObjectController instance;

    private SubObjectController() {
        this.NbOfMaterial = 0;
        this.NbOfService = 0;
        this.NbOfRatings = 0;
    }

    public static SubObjectController getInstance() {
        if (instance == null) {
            instance = new SubObjectController();
        }
        return instance;
    }

    public int getNbOfMaterial() {
        return NbOfMaterial;
    }

    public void setNbOfMaterial(int nbOfMaterial) {
        NbOfMaterial = nbOfMaterial;
    }

    public int getNbOfService() {
        return NbOfService;
    }

    public void setNbOfService(int nbOfService) {
        NbOfService = nbOfService;
    }

    public int getNbOfRatings() {
        return NbOfRatings;
    }

    public void setNbOfRatings(int nbOfRatings) {
        NbOfRatings = nbOfRatings;
    }

    public Integer getNewMaterialId() {
        NbOfMaterial++;
        return NbOfMaterial+NbOfService;
    }

    public Integer getNewServiceId() {
        NbOfService++;
        return NbOfService+NbOfMaterial;
    }

    public Integer getNewEvaluationId() {
        NbOfRatings++;
        return NbOfRatings;
    }
}
