package eddy.study.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

@Service
public class ColorUseCase {

    Logger logger = LoggerFactory.getLogger(ColorUseCase.class);

    private final CacheComponent cacheComponent;

    public ColorUseCase(CacheComponent cacheComponent) {
        this.cacheComponent = cacheComponent;
    }

    public List<Color> findAllByLoop(){
        List<Color> responseList = new ArrayList<>();

        colorList.forEach(color -> {
            responseList.add(cacheComponent.findByName(color));
        });

        return responseList;
    }

    public List<Color> findAllByStream(){

        Map<String, Color> responseMap = new ConcurrentHashMap<>();

        ForkJoinPool forkjoinPool = new ForkJoinPool(10);
        try {
            forkjoinPool.submit(() -> {
                colorList.parallelStream().forEach(colorName ->
                        responseMap.put(colorName,cacheComponent.findByName(colorName)));
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(responseMap.values());
    }

    public List<Color> findAllByFuture(){

        //TODO:CompletableFuture 구현
        return null;
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
