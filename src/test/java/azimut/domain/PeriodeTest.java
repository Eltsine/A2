package azimut.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import azimut.web.rest.TestUtil;

public class PeriodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Periode.class);
        Periode periode1 = new Periode();
        periode1.setId(1L);
        Periode periode2 = new Periode();
        periode2.setId(periode1.getId());
        assertThat(periode1).isEqualTo(periode2);
        periode2.setId(2L);
        assertThat(periode1).isNotEqualTo(periode2);
        periode1.setId(null);
        assertThat(periode1).isNotEqualTo(periode2);
    }
}
