package src;

import java.util.*;

public class Main {
	public static void main(String[] args) {
			try {
					Goose martin = new Goose("Мартин");
					Goose marta = new Goose("Марта");
					Human nils = new Human("Нильс");
					
					Flock flock = new Flock();
					flock.addGoose(martin);
					flock.addGoose(marta);
					
					System.out.println("Домашние гуси - это гуси, и " + martin.getName() + " с " + marta.getName() + " отлично зажили в новом доме");
					
					System.out.println(nils.getName() + " тоже поначалу пришлось трудно");
					
					System.out.println("Гуси решили позаботиться о " + nils.getName());
					
					LivingPlace place = flock.buildLivingPlace();
					
					List<Being> characters = new ArrayList<>();
					for (Goose goose : flock.getGeese()) {
							characters.add(goose);
					}
					characters.add(nils);
					for (Being character : characters) {
						character.setLivingPlace(place);
						character.live();
					}

			} catch (Exception e) {
					System.out.println(e.getMessage());
			}
	}
}
