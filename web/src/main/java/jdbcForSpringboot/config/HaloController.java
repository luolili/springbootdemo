package jdbcForSpringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class HaloController {
    @Autowired
    DataSource dataSource;

    @GetMapping("/halo")
    public String halo() {
        return "g";
    }
}
