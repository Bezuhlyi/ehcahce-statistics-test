import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Statistics;
import org.junit.Test;

/**
 * @author Vladyslav Bezuhlyi
 */
public class EhcahceStatisticsTest {

    @Test
    public void test() throws InterruptedException {
        Ehcache cache = CacheManager.getInstance().getCache("test-cache");
        cache.setStatisticsEnabled(true);
        cache.setStatisticsAccuracy(Statistics.STATISTICS_ACCURACY_GUARANTEED);

        /* put 100 elements */
        for (int i = 1; i <= 100; i++) {
            cache.put(new Element("key-"+i, "value-"+i));
        }

        /* hit 100 elements in cache 10000 times */
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100 ; j++) {
                cache.get("key-"+j);
            }
        }

        /* consider values of misses, onDiskMisses, inMemoryMisses */
        System.out.println("Statistics = " + cache.getStatistics().toString());
    }

}
