package eu.telecomnancy.labfx.MaterialService;

import eu.telecomnancy.labfx.utils.JsonHandler.JsonItemReader;
import eu.telecomnancy.labfx.utils.JsonHandler.JsonItemWritter;

import java.util.ArrayList;

public class MaterialController implements MaterialServiceController{
    private ArrayList<Material> materials = null;
    private  static MaterialController instance = null;

    private MaterialController() {
        this.materials = read("/eu/telecomnancy/labfx/data/item.json");
    }

    public static MaterialController getInstance() {
        if (instance == null) {
            instance = new MaterialController();
        }
        return instance;
    }


    public void add(Material material) {
        this.materials.add(material);
    }

    @Override
    public MaterialService get(int id) {
        for (Material material : this.materials) {
            if (material.getId() == id) {
                return material;
            }
        }
        return null;
    }


    public ArrayList<Material> read(String resourcePath) {
        ArrayList<MaterialService> materialsOrServices = JsonItemReader.read(resourcePath);
        ArrayList<Material> materials1 = new ArrayList<Material>();
        for (MaterialService materialOrService : materialsOrServices) {
            if (materialOrService instanceof Material) {
                materials1.add((Material) materialOrService);
            }
        }
        return materials1;
    }

    public void saveItems(String resourcePath, ArrayList<Service> services) {
        JsonItemWritter.write(resourcePath, services, this.materials);
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public int getMaxId() {
        int maxId = 0;
        for (Material material : this.materials) {
            if (material.getId() > maxId) {
                maxId = material.getId();
            }
        }
        return maxId;
    }
}
