package azimut.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import azimut.web.rest.TestUtil;

public class FormateurDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormateurDTO.class);
        FormateurDTO formateurDTO1 = new FormateurDTO();
        formateurDTO1.setId(1L);
        FormateurDTO formateurDTO2 = new FormateurDTO();
        assertThat(formateurDTO1).isNotEqualTo(formateurDTO2);
        formateurDTO2.setId(formateurDTO1.getId());
        assertThat(formateurDTO1).isEqualTo(formateurDTO2);
        formateurDTO2.setId(2L);
        assertThat(formateurDTO1).isNotEqualTo(formateurDTO2);
        formateurDTO1.setId(null);
        assertThat(formateurDTO1).isNotEqualTo(formateurDTO2);
    }
}
