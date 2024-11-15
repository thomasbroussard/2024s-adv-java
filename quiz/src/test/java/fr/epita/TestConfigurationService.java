package fr.epita;

import fr.epita.services.Configuration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestConfigurationService {


    @Test
    public void testConfigurationLoading(){
        Exception recordedException = null;
        Configuration conf = Configuration.getInstance();
        Assertions.assertThat(conf).isNotNull();
    }

    @Test
    public void testConfigurationPropertyResolutionKeyNotExisting(){
        Exception recordedException = null;
        Configuration conf = Configuration.getInstance();
        String value = conf.getValue("abcd.efG");
        Assertions.assertThat(value).isNull();
    }
    @Test
    public void testConfigurationPropertyResolutionKeyExists(){
        Exception recordedException = null;
        Configuration conf = Configuration.getInstance();
        String value = conf.getValue("db.url");
        Assertions.assertThat(value).isNotNull();
        Assertions.assertThat(value).isEqualTo("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
    }
}
