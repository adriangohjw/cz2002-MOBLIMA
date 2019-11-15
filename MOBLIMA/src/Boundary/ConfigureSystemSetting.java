package Boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.*;
import Model.*;

public class ConfigureSystemSetting {
	private Scanner input = new Scanner(System.in);
	private HolidaysController holCtrl = new HolidaysController();
	private PriceController priceCtrl = new PriceController();
	
	public void main() {
		boolean exit  = false;
		int choice;
		while (!exit){
			System.out.println("Configure System Settings: \n" +
					"1.  Add Holiday \n" +
					"2.  Delete Holiday \n" +
					"3.  Update Movie Type Price \n" +
					"4.  Update Cinema Type Price \n" +
					"5.  Update Student Price \n" +
					"6.  Update Senior Citizen Price \n" +
					"7.  Update Standard Price \n" +
					"8.  Update Weekend Price \n" +
					"9.  Update Holiday Price \n" +
					"10. Return to Main Menu");
			choice = InputController.getIntFromUser();
			switch (choice) {
				case 1:
					CreateHoliday();
					break;
				case 2:
					DeleteHoliday();
					break;
				case 3:
					UpdateMovieTypePrice();
					break;
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
					UpdatePriceType(choice);
					break;
				case 10:
					exit = true;
					break;
			}
		}

	}
	
	public void	CreateHoliday() {
		System.out.println("Enter holiday date: ");
		LocalDate holiday = InputController.getDateFromUser();
		if (holCtrl.isHoliday(holiday)) {
			System.out.println("Holiday already exists!");
			return;
		}
		holCtrl.create(holiday);
	}
	
	public void DeleteHoliday() {
		System.out.println("Enter holiday date: ");
		ArrayList<Holiday> holList = holCtrl.read();
		holList.forEach(Holiday -> printHol(Holiday));
		LocalDate holiday = InputController.getDateFromUser();
		if (!holCtrl.isHoliday(holiday)) {
			System.out.println("Holiday does not exist!");
			return;
		}
		holCtrl.delete(holiday);
	}
	
	public void UpdateMovieTypePrice() {
		System.out.println("Choose Movie Type: \n" +
						   "1. 2D\n" +
						   "2. 3D\n" +
						   "3. Blockbuster\n" +
						   "Option: ");
		int choice = InputController.getIntFromUser();
		System.out.println("Enter new price: ");
		double newPrice = InputController.getDoubleFromUser();
		switch(choice) {
			case 1:
				priceCtrl.changePriceChanger(MovieType.TWO_D, newPrice);
				break;
			case 2:
				priceCtrl.changePriceChanger(MovieType.THREE_D, newPrice);
				break;
			case 3:
				priceCtrl.changePriceChanger(MovieType.BLOCKBUSTER, newPrice);
				break;
			default:
				System.out.println("invalid choice!\n" +
								   "Returning to menu...");
		}
	}
	
	public void UpdateCinemaTypePrice() {
		System.out.println("Choose Cinema Type: \n" +
						   "1. Standard\n" +
						   "2. Premium\n" +
						   "Option: ");
		int choice = InputController.getIntFromUser();
		System.out.println("Enter new price: ");
		double newPrice = InputController.getDoubleFromUser();
		switch(choice) {
			case 1:
				priceCtrl.changePriceChanger(CinemaType.STANDARD, newPrice);
				break;
			case 2:
				priceCtrl.changePriceChanger(CinemaType.PREMIUM, newPrice);
				break;
			default:
				System.out.println("invalid choice!\n" +
								   "Returning to menu...");
		}
	}
	
	public void UpdatePriceType(int choice) {
		System.out.println("Enter new price: ");
		double newPrice = InputController.getDoubleFromUser();
		switch(choice) {
		case 5:
			priceCtrl.changePriceChanger(PriceType.STUDENT, newPrice);
			break;
		case 6:
			priceCtrl.changePriceChanger(PriceType.SENIOR_CITIZEN, newPrice);
			break;
		case 7:
			priceCtrl.changePriceChanger(PriceType.NORMAL, newPrice);
			break;
		case 8:
			priceCtrl.changePriceChanger(PriceType.WEEKEND, newPrice);
			break;
		case 9:
			priceCtrl.changePriceChanger(PriceType.HOLIDAY, newPrice);
			break;
		}
	}
	
	public void printHol(Holiday holiday) {
		System.out.println(holiday.getHolidayDateToString());
	}
}
