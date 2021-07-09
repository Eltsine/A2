package azimut.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ApprenantMapperTest {

    private ApprenantMapper apprenantMapper;

    @BeforeEach
    public void setUp() {
        apprenantMapper = new ApprenantMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(apprenantMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(apprenantMapper.fromId(null)).isNull();
    }
}
