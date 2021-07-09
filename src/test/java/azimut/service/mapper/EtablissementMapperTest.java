package azimut.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EtablissementMapperTest {

    private EtablissementMapper etablissementMapper;

    @BeforeEach
    public void setUp() {
        etablissementMapper = new EtablissementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(etablissementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(etablissementMapper.fromId(null)).isNull();
    }
}
