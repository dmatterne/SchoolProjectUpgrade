package school;

import be.david.school.dbmodel.Discounts;
import be.david.school.repository.DiscountsRepository;
import be.david.school.repository_bean.DiscountsRepositoryBean;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by David on 7/08/2016.
 */
public class DiscountsPersistenceTest extends DataSetPersistenceTest {
    @Rule

    public ExpectedException expector = ExpectedException.none();
//

    private EntityManager em;

    @Test
    public void FindAllDiscounts() {
        DiscountsRepository dsc = new DiscountsRepositoryBean(entityManager());
        List<Discounts> dscs = dsc.findAllDiscounts();
        assertEquals(3, dscs.size());
    }
}


