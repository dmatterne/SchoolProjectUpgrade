package school;

import be.david.school.dbmodel.Logins;
import be.david.school.repository.LoginsRepository;
import be.david.school.repository_bean.LoginsRepositoryBean;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class LoginsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllLogins() {
        LoginsRepository lr = new LoginsRepositoryBean(entityManager());
        List<Logins> lrs = lr.findAllLogins();
        assertEquals(1, lrs.size());
    }
}
