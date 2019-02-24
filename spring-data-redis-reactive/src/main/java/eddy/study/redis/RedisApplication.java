package eddy.study.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RedisApplication {

    private final ReactiveRedisConnectionFactory factory;
    private final ReactiveRedisOperations<String, Color> colorOps;

    public RedisApplication(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Color> colorOps) {
        this.factory = factory;
        this.colorOps = colorOps;
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @PostConstruct
    public void loadData() {
        factory.getReactiveConnection().serverCommands().info().thenMany(
                Flux.fromIterable(colorList)
                        .map(name -> new Color(
                                name,
                                javafx.scene.paint.Color.web(name).getRed(),
                                javafx.scene.paint.Color.web(name).getBlue(),
                                javafx.scene.paint.Color.web(name).getGreen()))
                        .flatMap(color -> colorOps.opsForValue().set("colors::v2::"+color.getName(), color)))
                .subscribe(
                        null,
                        error -> System.out.println("에러"),
                        () -> System.out.println("초기 데이터 저장 완료"));
    }

    private List<String> colorList = Arrays.asList(
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
