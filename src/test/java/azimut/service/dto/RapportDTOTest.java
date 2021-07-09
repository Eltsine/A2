package azimut.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import azimut.web.rest.TestUtil;

public class RapportDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RapportDTO.class);
        RapportDTO rapportDTO1 = new RapportDTO();
        rapportDTO1.setId(1L);
        RapportDTO rapportDTO2 = new RapportDTO();
        assertThat(rapportDTO1).isNotEqualTo(rapportDTO2);
        rapportDTO2.setId(rapportDTO1.getId());
        assertThat(rapportDTO1).isEqualTo(rapportDTO2);
        rapportDTO2.setId(2L);
        assertThat(rapportDTO1).isNotEqualTo(rapportDTO2);
        rapportDTO1.setId(null);
        assertThat(rapportDTO1).isNotEqualTo(rapportDTO2);
    }
}
