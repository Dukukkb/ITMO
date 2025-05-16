package src;

public record Roof() implements LivingPlace {
    
	@Override
	public boolean isComfortable(Being being) {
			return being instanceof Human;
	}
	
	@Override
	public String getName() {
			return "крыша";
	}
}
