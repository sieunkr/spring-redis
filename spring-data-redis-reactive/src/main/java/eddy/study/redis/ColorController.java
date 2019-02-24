package eddy.study.redis;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colors")
public class ColorController {

    private final ReactiveRedisOperations<String, Color> colorOps;

    public ColorController(ReactiveRedisOperations<String, Color> colorOps) {
        this.colorOps = colorOps;
    }

    @GetMapping("/get")
    public Flux<Color> findAllByGet(){

        //단일 key, 여러번 호출
        return colorOps.keys("colors::v3::*")
                .flatMap(colorOps.opsForValue()::get);

    }

    @GetMapping("/mget")
    public Mono<List<Color>> findAllByMultiGet(){

        return colorOps.opsForValue()
                .multiGet(colorList.stream().map(k -> "colors::v3::" + k).collect(Collectors.toList()));

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
