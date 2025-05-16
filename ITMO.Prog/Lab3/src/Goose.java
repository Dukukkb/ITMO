package src;

public class Goose extends Being {
	
	public Goose(String name) {
			super(name);
	}
	
	@Override
	public void live() throws NoRoofException {
		try {
			if (place == null) {
				throw new NoRoofException("У " + name + " нет гнезда!");
			} else if (place instanceof Nest nest) {
				if (place.isComfortable(this)) {
					emotion = EmotionType.HAPPY;
					System.out.println(name + " " + emotion.getDescription() + " и живет в " + 
							(nest.isWarm() ? "теплом" : "прохладном") + " и " +
							(nest.isBeautiful() ? "красивом" : "простом") + " гнезде");
				} else {
					emotion = EmotionType.UNHAPPY;
					System.out.println(name + " " + emotion.getDescription() + " своим гнездом");
				}
			} else if (place instanceof Roof) {
				emotion = EmotionType.UNHAPPY;
				System.out.println(name + " " + emotion.getDescription() + " - не может жить в доме ");
			} else { 
				throw new NoRoofException("У " + name + " не подходящее жилье!");
			}
		} catch (NoRoofException e) {
			System.out.println(e.getMessage());
		}
	}
}
