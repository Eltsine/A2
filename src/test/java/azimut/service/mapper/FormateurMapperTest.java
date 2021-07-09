package azimut.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FormateurMapperTest {

    private FormateurMapper formateurMapper;

    @BeforeEach
    public void setUp() {
        formateurMapper = new FormateurMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(formateurMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(formateurMapper.fromId(null)).isNull();
    }
}
