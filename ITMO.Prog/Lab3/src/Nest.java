package src;

public record Nest(boolean isWarm, boolean isBeautiful) implements LivingPlace {
    
	@Override
	public boolean isComfortable(Being being) {
			if (!(being instanceof Goose)) return false;
			return isWarm && isBeautiful;
	}
	
	@Override
	public String getName() {
			return "гнездо";
	}
} 