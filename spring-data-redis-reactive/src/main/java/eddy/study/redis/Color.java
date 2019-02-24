package eddy.study.redis;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Color implements Serializable {

    private String name;
    private double red;
    private double blue;
    private double green;

}
