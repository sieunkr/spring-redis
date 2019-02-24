package eddy.study.redis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/colors")
public class ColorController {

    private final ColorUseCase colorUseCase;

    public ColorController(ColorUseCase colorUseCase) {
        this.colorUseCase = colorUseCase;
    }

    @GetMapping
    public Collection<Color> findAllByTemplate(){
        return colorUseCase.findAll();
    }

}
