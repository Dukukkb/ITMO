package src;

public class Human extends Being {
    private static final String[] ROOF_HAPPY_PHRASES = {
        " радостно осматривает свой новый дом с крышей",
        " с удовольствием устраивается под надежной крышей",
        " наконец-то чувствует себя как дома под крышей",
        " довольно улыбается - теперь у него есть настоящий дом"
    };
    
    private static final String[] NEST_UNHAPPY_PHRASES = {
        " вздыхает - как же можно жить в гнезде? Он же не птица!",
        " качает головой - гнездо хорошее, но он все-таки человек",
        " благодарит гусей за заботу, но объясняет, что ему нужен дом",
        " смотрит на гнездо с улыбкой, но жить в нем не собирается"
    };
    
    public Human(String name) {
        super(name);
    }
    
    private String getRandomPhrase(String[] phrases) {
        return phrases[(int)(Math.random() * 4)];
    }
    
    @Override
    public void live() throws NoRoofException {
        try {
            if (place == null) {
                throw new NoRoofException("У " + name + " нет крыши над головой!");
            } else if (place instanceof Roof) {
                emotion = EmotionType.HAPPY;
                System.out.println(name + " " + emotion.getDescription() + " и" + getRandomPhrase(ROOF_HAPPY_PHRASES));
            } else if (place instanceof Nest) {
                emotion = EmotionType.UNHAPPY;
                System.out.println(name + " " + emotion.getDescription() + " и" + getRandomPhrase(NEST_UNHAPPY_PHRASES));
            } else {
                throw new NoRoofException("У " + name + " не подходящее жилье!");
            }
        } catch (NoRoofException e) {
            System.out.println(e.getMessage());
            emotion = EmotionType.WORRIED;
            System.out.println(name + " " + emotion.getDescription() + " и мечтает о доме");
        }
    }
} 