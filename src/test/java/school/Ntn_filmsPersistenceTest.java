package school;

import be.david.school.dbmodel.Ntn_films;
import be.david.school.repository.Ntn_filmsRepository;
import be.david.school.repository_bean.Ntn_filmsRepositoryBean;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Ntn_filmsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllNtn_films() {
        Ntn_filmsRepository nf = new Ntn_filmsRepositoryBean(entityManager());
        List<Ntn_films> nfs = nf.findAllNtn_films();
        assertEquals(2, nfs.size());
    }
}
