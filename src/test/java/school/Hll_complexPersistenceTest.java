package school;

import be.david.school.dbmodel.Hll_complex;
import be.david.school.repository.Hll_complexRepository;
import be.david.school.repository_bean.Hll_complexRepositoryBean;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Hll_complexPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllHll_complex() {
        Hll_complexRepository hr = new Hll_complexRepositoryBean(entityManager());
        List<Hll_complex> hrs = hr.findAllHll_complex();
        assertEquals(3, hrs.size());
    }
}
