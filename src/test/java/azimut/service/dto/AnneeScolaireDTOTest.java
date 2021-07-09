package azimut.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import azimut.web.rest.TestUtil;

public class AnneeScolaireDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnneeScolaireDTO.class);
        AnneeScolaireDTO anneeScolaireDTO1 = new AnneeScolaireDTO();
        anneeScolaireDTO1.setId(1L);
        AnneeScolaireDTO anneeScolaireDTO2 = new AnneeScolaireDTO();
        assertThat(anneeScolaireDTO1).isNotEqualTo(anneeScolaireDTO2);
        anneeScolaireDTO2.setId(anneeScolaireDTO1.getId());
        assertThat(anneeScolaireDTO1).isEqualTo(anneeScolaireDTO2);
        anneeScolaireDTO2.setId(2L);
        assertThat(anneeScolaireDTO1).isNotEqualTo(anneeScolaireDTO2);
        anneeScolaireDTO1.setId(null);
        assertThat(anneeScolaireDTO1).isNotEqualTo(anneeScolaireDTO2);
    }
}
