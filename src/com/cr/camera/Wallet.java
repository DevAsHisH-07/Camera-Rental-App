package com.cr.camera;

public class Wallet {
	CameraList cameraList=new CameraList();
    private double balance = 10000.0d;

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean canRent(Camera camera) {
        return balance >= camera.getRentPerDay();
    }

    public boolean rent(Camera camera) {
    	if (balance >= camera.getRentPerDay() && !cameraList.isRented(camera)) {
            balance -= camera.getRentPerDay();
            cameraList.markAsRented(camera);
            return true;
        }
        return false;
    }
}
