package azimut.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RapportMapperTest {

    private RapportMapper rapportMapper;

    @BeforeEach
    public void setUp() {
        rapportMapper = new RapportMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rapportMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rapportMapper.fromId(null)).isNull();
    }
}
