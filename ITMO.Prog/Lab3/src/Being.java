package src;

public abstract class Being {
	protected String name;
	protected LivingPlace place;
	protected EmotionType emotion;
	
	public Being(String name) {
			this.name = name;
	}
	
	public abstract void live() throws NoRoofException;

	public void setLivingPlace(LivingPlace place) {
		this.place = place;
	}
	
	public LivingPlace getLivingPlace() {
			return place;
	}
	
	public String getName() {
			return name;
	}
	
	public EmotionType getEmotion() {
		return emotion;
	}

	public void setEmotion(EmotionType emotion) {
		this.emotion = emotion;
	}


	@Override
	public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			Being being = (Being) obj;
			return name.equals(being.name);
	}
	
	@Override
	public int hashCode() {
			return name.hashCode();
	}
	
	@Override
	public String toString() {
			return name;
	}
}

