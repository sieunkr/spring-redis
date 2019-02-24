package eddy.study.redis;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("colors::v3:")   //TODO: Key 규칙 깔끔하지 않음
public class Color implements Serializable {

    @Id
    private String name;
    private double red;
    private double blue;
    private double green;

}
