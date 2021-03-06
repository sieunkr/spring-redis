package eddy.study.redis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {



    }

    private final List<String> colorList = Arrays.asList(
            "ALICEBLUE","ANTIQUEWHITE","AQUA","AZURE",
            "BEIGE","BISQUE","BLACK","BLANCHEDALMOND",
            "BLUE", "BLUEVIOLET","BROWN","BURLYWOOD",
            "CADETBLUE","CHARTREUSE","CHOCOLATE","CORAL",
            "CORNFLOWERBLUE","CORNSILK","CRIMSON","CYAN",
            "DARKBLUE","DARKCYAN","DARKGOLDENROD","DARKGRAY",
            "DARKGREEN","DARKGREY","DARKKHAKI","DARKMAGENTA",
            "DARKOLIVEGREEN","DARKORANGE","DARKORCHID","DARKRED",
            "DARKSALMON","DARKSEAGREEN","DARKSLATEBLUE","DARKSLATEGRAY",
            "DARKSLATEGREY","DARKTURQUOISE","DARKVIOLET","DEEPPINK",
            "DEEPSKYBLUE","DIMGRAY","DIMGREY","DODGERBLUE",
            "FIREBRICK","FLORALWHITE","FORESTGREEN","FUCHSIA",
            "GAINSBORO","GHOSTWHITE","GOLD","GOLDENROD",
            "GRAY","GREEN","GREENYELLOW","GREY",
            "HONEYDEW","HOTPINK","INDIANRED","INDIGO",
            "IVORY","KHAKI","LAVENDER","LAVENDERBLUSH",
            "LAWNGREEN","LEMONCHIFFON","LIGHTBLUE","LIGHTCORAL",
            "LIGHTCYAN","LIGHTGOLDENRODYELLOW","LIGHTGRAY","LIGHTGREEN",
            "LIGHTGREY","LIGHTPINK","LIGHTSALMON","LIGHTSEAGREEN",
            "LIGHTSKYBLUE","LIGHTSLATEGRAY","LIGHTSLATEGREY","LIGHTSTEELBLUE",
            "LIGHTYELLOW","LIME","LIMEGREEN","LINEN",
            "MAGENTA","MAROON","MEDIUMAQUAMARINE","MEDIUMBLUE",
            "MEDIUMORCHID","MEDIUMPURPLE","MEDIUMSEAGREEN","MEDIUMSLATEBLUE",
            "MEDIUMSPRINGGREEN","MEDIUMTURQUOISE","MEDIUMVIOLETRED","MIDNIGHTBLUE",
            "MINTCREAM","MISTYROSE","MOCCASIN","NAVAJOWHITE",
            "NAVY","OLDLACE","OLIVE","OLIVEDRAB");
}
