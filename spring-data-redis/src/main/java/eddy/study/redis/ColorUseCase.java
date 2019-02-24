package eddy.study.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ColorUseCase {

    Logger logger = LoggerFactory.getLogger(ColorUseCase.class);

    private final ColorRepository colorRepository;

    public ColorUseCase(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> findAll(){
        return (List<Color>)colorRepository.findAll();
    }


}
