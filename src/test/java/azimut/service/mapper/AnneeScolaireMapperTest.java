package azimut.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnneeScolaireMapperTest {

    private AnneeScolaireMapper anneeScolaireMapper;

    @BeforeEach
    public void setUp() {
        anneeScolaireMapper = new AnneeScolaireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(anneeScolaireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(anneeScolaireMapper.fromId(null)).isNull();
    }
}
