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

    @GetMapping("/loop")
    public Collection<Color> findAllByLoop(){
        return colorUseCase.findAllByLoop();
    }

    @GetMapping("/stream")
    public Collection<Color> findAllByStream(){
        return colorUseCase.findAllByStream();
    }

    @GetMapping("/future")
    public Collection<Color> findAllByFuture(){
        return colorUseCase.findAllByFuture();
    }


}
