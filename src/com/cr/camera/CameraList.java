package com.cr.camera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CameraList {
    private List<Camera> cameras = new ArrayList<>();
    private Map<Camera, Boolean> rentalStatus = new HashMap<>();

    public void addCamera(Camera camera) {
        cameras.add(camera);
        rentalStatus.put(camera, false); // Initially set to "Available"
    }

    public void removeCamera(int index) {
        if (index >= 0 && index < cameras.size()) {
            Camera removedCamera = cameras.get(index);
            cameras.remove(index);
            rentalStatus.remove(removedCamera);
        }
    }

    public List<Camera> getCameras() {
        return cameras;
    }

    public void markAsRented(Camera camera) {
        rentalStatus.put(camera, true);
    }

    public void markAsAvailable(Camera camera) {
        rentalStatus.put(camera, false);
    }

    public boolean isRented(Camera camera) {
        Boolean status = rentalStatus.get(camera);
        return status != null && status; // Return true if status is not null and is true
    }
}

