package eddy.study.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ColorUseCase {

    Logger logger = LoggerFactory.getLogger(ColorUseCase.class);

    private final RedisTemplate redisTemplate;

    public ColorUseCase(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /*
    Key 조회
    List<byte[]> keys = redisTemplate.getConnectionFactory().getConnection()
            .keys("colors::v3::*".getBytes()).parallelStream().collect(Collectors.toList());
    */

    public List<Color> findAll(){

        //TODO: 코드 깔끔하게,, 리팩토링하자!
        List<byte[]> keys = colorList.stream()
                .map(k -> ("colors::v3::" + k).getBytes())
                .collect(Collectors.toList());

        return redisTemplate.getConnectionFactory()
                .getConnection()
                .mGet(keys.stream().toArray(byte[][]::new))
                .parallelStream()
                .map(colorByte -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        return mapper.readValue(new String(colorByte), Color.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());

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
