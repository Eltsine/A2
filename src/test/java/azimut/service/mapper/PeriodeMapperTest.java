package azimut.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PeriodeMapperTest {

    private PeriodeMapper periodeMapper;

    @BeforeEach
    public void setUp() {
        periodeMapper = new PeriodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(periodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(periodeMapper.fromId(null)).isNull();
    }
}
