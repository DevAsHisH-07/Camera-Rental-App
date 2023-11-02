package com.cr.camera;

import java.util.List;
import java.util.Scanner;

public class CameraRentalApp {
	 CameraList cameraList = new CameraList();
	//private double walletBalance = 0.0;
	private Scanner sc = new Scanner(System.in);
	 final static String userName = "admin"; 
	final static String password = "admin123";
	Wallet wallet = new Wallet();

	 public CameraRentalApp() {
	        // Add some pre-added cameras to the list
	        cameraList.addCamera(new Camera("Canon", "EOS 5D Mark IV", 2500.0));
	        cameraList.addCamera(new Camera("Nikon", "D850", 3000.0));
	        cameraList.addCamera(new Camera("Sony", "Alpha A7 III", 2000.0));
	    }
	
	
	public void login() {
		System.out.println("Welcome to Camera Rental App");
		System.out.println("Developer: Devashish\n");
		while(true) {
			System.out.print("Enter username: ");
			String enteredUsername = sc.nextLine();

			System.out.print("Enter password: ");
			String enteredPassword = sc.nextLine();

			if (enteredUsername.equals(userName) && enteredPassword.equals(password)) {
				System.out.println("Login Successful.\n");
				showWelcomeScreen();
				break;
			} else {
				System.out.println("Login failed. Invalid username or password.\n");
			}
		}
	}

	public void showWelcomeScreen() {
		

		while (true) {
			System.out.println("Choose an option:");
			System.out.println("1. My Camera");
			System.out.println("2. Rent a Camera");
			System.out.println("3. View All Camera");
			System.out.println("4. My Wallet");
			System.out.println("5. Exit");

			int choice = getIntInput();

			switch (choice) {
			case 1:
				manageMyCamera();
				break;
			case 2:
				rentCamera();
				break;
			case 3:
				listCameras();
				break;
			case 4:
				viewWallet();
				break;
			case 5:
				System.out.println("Exiting Camera Rental App. Goodbye!");
				System.exit(0);
			default:
				System.out.println("Invalid option. Please choose a valid option.\n");
			}
		}
	}
	private void manageMyCamera() {
		while(true) {
			System.out.println("My Camera Options:");
			System.out.println("1. Add Camera");
			System.out.println("2. Remove Camera");
			System.out.println("3. View My Cameras");
			System.out.println("4. Return to Previous Menu");

			int choice = getIntInput();

			switch (choice) {
			case 1:
				addCamera();
				break;
			case 2:
				removeCamera();
				break;
			case 3:
				listCameras();
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid option in My Camera.");
			}
		}
	}

	private void addCamera() {
		System.out.println("Enter camera details:");

//		System.out.println("Camera ID: ");
//		int camera_id=sc.nextInt();

		System.out.println("Brand: ");
		String brand = sc.next()+sc.nextLine();

		System.out.print("Model: ");
		String model = sc.next()+sc.nextLine();

		System.out.print("Rent per Day: ");
		double rentPerDay = getDoubleInput();

		Camera newCamera = new Camera(brand, model, rentPerDay);
		cameraList.addCamera(newCamera);

		System.out.println("Camera added to My Cameras.");
	}

	private void removeCamera() {
		listCameras();
		System.out.println("Enter the Camera ID of the camera you want to remove from My Cameras:");

		int cameraId = getIntInput();
		cameraList.removeCamera(cameraId-1);

		System.out.println("Camera removed from My Cameras.");
	}

	private void availableCameras() {
		List<Camera> myCameras = cameraList.getCameras();

		if (myCameras.isEmpty()) {
			System.out.println("No cameras in My Cameras.");
		} else {
			System.out.println("Cameras Available for Rent:");
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.printf("%-10s %-20s %-20s %-20s %-10s%n","Camera ID", "Brand", "Model", "Rent per Day (Rs.)", "Status");
			System.out.println("--------------------------------------------------------------------------------------------");

			for (int i = 0; i < myCameras.size(); i++) {
				Camera camera = myCameras.get(i);
				String status = cameraList.isRented(camera) ? "Rented" : "Available";
				if (!cameraList.isRented(camera)) { // Check if the camera is not rented
					System.out.printf("%-10s %-20s %-20s %-20.2f %-10s%n",i+1, camera.getBrand(), camera.getModel(), camera.getRentPerDay(), status);
		        }
			}
		}
	}


	private void listCameras() {
		List<Camera> cameras = cameraList.getCameras();

		if (cameras.isEmpty()) {
			System.out.println("No Data Present at This Moment.");
		} else {
			System.out.println("Cameras Available for Rent:");
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.printf("%-10s %-20s %-20s %-20s %-10s%n","Camera ID", "Brand", "Model", "Rent per Day (Rs.)", "Status");
			System.out.println("--------------------------------------------------------------------------------------------");

			for (int i = 0; i < cameras.size(); i++) {
				Camera camera = cameras.get(i);
				String status = cameraList.isRented(camera) ? "Rented" : "Available";
				System.out.printf("%-10s %-20s %-20s %-20.2f %-10s%n",i+1, camera.getBrand(), camera.getModel(), camera.getRentPerDay(), status);
			}
		}
	}

	private void rentCamera() {
	    List<Camera> cameras = cameraList.getCameras();

	    if (cameras.isEmpty()) {
	        System.out.println("No cameras available for rent.");
	        return;
	    }

	    availableCameras();

	    System.out.println("Enter the camera id of the camera you want to rent:");
	    int cameraId = getIntInput();

	    if (cameraId < 0 || cameraId > cameras.size()) {
	        System.out.println("Invalid id. Please enter a valid camera index.");
	        return;
	    }

	    Camera selectedCamera = cameras.get(cameraId-1);

	    if (wallet.rent(selectedCamera)) {
	        System.out.println("You have rented the following camera: " + selectedCamera);
	        cameraList.markAsRented(selectedCamera);
	    } else {
	        System.out.println("Insufficient wallet balance or the camera is already rented. Please deposit funds or choose another camera.");
	    }
	}


	private void viewWallet() {
		System.out.println("Wallet Balance: Rs." + wallet.getBalance());
		System.out.println("Choose an option:");
		System.out.println("1. Deposit Funds");
		System.out.println("2. Back to Main Menu");

		int choice = getIntInput();

		switch (choice) {
		case 1:
			System.out.println("Enter the amount to deposit:");
			double amount = getDoubleInput();
			wallet.deposit(amount);
			System.out.println("Funds deposited successfully.");
			System.out.println("Updated Wallet Balance: Rs." + wallet.getBalance());
			break;
		case 2:
			return;
		default:
			System.out.println("Invalid option. Returning to the main menu.");
		}
	}

	private int getIntInput() {
		while (true) {
			try {
				System.out.println("Enter the input: ");
				int input=sc.nextInt();
				return input;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid integer.");
			}
		}
	}

	private double getDoubleInput() {
		while (true) {
			try {
				double amount=sc.nextDouble();
				return amount;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}
}
