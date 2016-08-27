package school;

import be.david.school.dbmodel.Flm_awards;
import be.david.school.repository.Flm_awardsRepository;
import be.david.school.repository_bean.Flm_awardsRepositoryBean;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Flm_awardsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllFlm_awards() {
        Flm_awardsRepository fr = new Flm_awardsRepositoryBean(entityManager());
        List<Flm_awards> frs = fr.findAllFlm_awards();
        assertEquals(3, frs.size());
    }
}
