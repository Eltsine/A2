package azimut.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import azimut.web.rest.TestUtil;

public class SalleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalleDTO.class);
        SalleDTO salleDTO1 = new SalleDTO();
        salleDTO1.setId(1L);
        SalleDTO salleDTO2 = new SalleDTO();
        assertThat(salleDTO1).isNotEqualTo(salleDTO2);
        salleDTO2.setId(salleDTO1.getId());
        assertThat(salleDTO1).isEqualTo(salleDTO2);
        salleDTO2.setId(2L);
        assertThat(salleDTO1).isNotEqualTo(salleDTO2);
        salleDTO1.setId(null);
        assertThat(salleDTO1).isNotEqualTo(salleDTO2);
    }
}
