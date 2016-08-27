package school;

import be.david.school.dbmodel.Cmp_features;
import be.david.school.repository.Cmp_FeaturesRepository;
import be.david.school.repository_bean.Cmp_FeaturesRepositoryBean;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class Cmp_featuresPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllCmp_features() {
        Cmp_FeaturesRepository cmf = new Cmp_FeaturesRepositoryBean(entityManager());
        List<Cmp_features> cmfs = cmf.findAllCmpFeatures();
        assertEquals(2, cmfs.size());
    }

    @Test
    public void FindAllCmp_featuresLanguages() {
        Cmp_FeaturesRepository cmf = new Cmp_FeaturesRepositoryBean(entityManager());
        List<Cmp_features> cmfs = cmf.findAllCmpLanguages();
        assertEquals(2, cmfs.size());
    }
}


