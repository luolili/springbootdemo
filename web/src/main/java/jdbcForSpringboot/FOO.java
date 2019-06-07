package jdbcForSpringboot;

import lombok.Builder;
import lombok.Data;

@Data//getter and setter
@Builder//constructor
public class FOO {

    private Long id;
    private String bar;
}
