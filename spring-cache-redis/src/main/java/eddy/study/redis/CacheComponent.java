package eddy.study.redis;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheComponent {

    @Cacheable(value = "colors::v1", key = "#name")
    public Color findByName(String name){

        javafx.scene.paint.Color color = javafx.scene.paint.Color.web(name);

        return new Color(name, color.getRed(), color.getBlue(), color.getGreen());
    }

}
