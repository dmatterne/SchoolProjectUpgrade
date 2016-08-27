package school;

import be.david.school.dbmodel.Scores;
import be.david.school.repository.ScoresRepository;
import be.david.school.repository_bean.ScoresRepositoryBean;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class ScoresPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllScores() {
        ScoresRepository sr = new ScoresRepositoryBean(entityManager());
        List<Scores> srs = sr.findAllScores();
        assertEquals(3, srs.size());
    }

}
