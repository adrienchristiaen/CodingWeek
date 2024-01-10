package eu.telecomnancy.labfx.MaterialService;

import eu.telecomnancy.labfx.utils.JsonHandler.JsonItemReader;
import eu.telecomnancy.labfx.utils.JsonHandler.JsonItemWritter;

import java.util.ArrayList;

public class ServiceController implements MaterialServiceController{
    private ArrayList<Service> services = null;
    private static ServiceController instance = null;

    private ServiceController() {
        this.services = read("/eu/telecomnancy/labfx/data/item.json");
    }

    public static ServiceController getInstance() {
        if (instance == null) {
            instance = new ServiceController();
        }
        return instance;
    }


    public void add(Service service) {
        this.services.add(service);
    }

    public void remove(Service service) {
        this.services.remove(service);
    }

    @Override
    public MaterialService get(int id) {
        for (Service service : this.services) {
            if (service.getId() == id) {
                return service;
            }
        }
        return null;
    }

    public ArrayList<Service> read(String resourcePath) {
        ArrayList<MaterialService> materialsOrServices = JsonItemReader.read(resourcePath);
        ArrayList<Service> services1 = new ArrayList<Service>();
        for (MaterialService materialOrService : materialsOrServices) {
            if (materialOrService instanceof Service) {
                services1.add((Service) materialOrService);
            }
        }
        return services1;
    }


    public void saveItems(ArrayList<Material> materials) {
        JsonItemWritter.write("/eu/telecomnancy/labfx/data/item.json", this.services, materials);

    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public int getMaxId() {
        int maxId = 0;
        for (Service service : this.services) {
            if (service.getId() > maxId) {
                maxId = service.getId();
            }
        }
        return maxId;
    }

    public Service getServiceById(int id) {
        for (Service service : this.services) {
            if (service.getId() == id) {
                return service;
            }
        }
        return null;
    }
}
