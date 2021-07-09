package azimut.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialiteMapperTest {

    private SpecialiteMapper specialiteMapper;

    @BeforeEach
    public void setUp() {
        specialiteMapper = new SpecialiteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(specialiteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(specialiteMapper.fromId(null)).isNull();
    }
}
