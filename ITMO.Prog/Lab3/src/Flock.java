package src;

import java.util.ArrayList;
import java.util.List;

public class Flock {
    private final List<Goose> geese = new ArrayList<>();
    
    public void addGoose(Goose goose) {
        geese.add(goose);
    }

    public List<Goose> getGeese() {
        return new ArrayList<>(geese);
    }
    
    public void careFor(Being being) {
        System.out.println("Стая заботится о " + being.getName());
    }
    
    public LivingPlace buildLivingPlace() throws NoRoofException {
       if (Math.random() < 0.3) {
        System.out.println("Стая не смогла построить жилье из-за плохой погоды");
        throw new NoRoofException("Строительство не удалось из-за плохой погоды");
       }
       
        if (Math.random() < 0.5) {
            boolean isWarm = Math.random() < 0.5;
            boolean isBeautiful = Math.random() < 0.5;

            String description = "Стая решила построить гнездо " +
                    (isWarm ? " теплое " : " прохладное ") +
                    (isBeautiful ? " красивое " : " простое ");
            System.out.println(description);
            return new Nest(isWarm, isBeautiful);
        } else {
            System.out.println("Стая решила построить дом");
            return new Roof();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Flock flock = (Flock) obj;
        return geese.equals(flock.geese);
    }
    
    @Override
    public int hashCode() {
        return geese.hashCode();
    }

    @Override
    public String toString() {
        return "Стая гусей: " + geese;
    }
} 