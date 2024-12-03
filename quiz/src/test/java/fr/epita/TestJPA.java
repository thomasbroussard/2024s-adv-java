package fr.epita;

import fr.epita.services.ChoiceDAO;
import fr.epita.services.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class TestJPA {

    @Bean
    public DataSource getDataSource(){
        fr.epita.services.Configuration conf = fr.epita.services.Configuration.getInstance();
        DriverManagerDataSource ds = new DriverManagerDataSource();
        String dbUrl = conf.getValue("db.url");
        String dbUser = conf.getValue("db.user");
        String dbPassword = conf.getValue("db.pwd");
        ds.setUrl(dbUrl);
        ds.setPassword(dbPassword);
        ds.setUsername(dbUser);
        return ds;
    }

    @Bean("test")
    public QuestionDAO getQuestionDAO(@Autowired DataSource ds){
        return new QuestionDAO(ds);
    }


    @Bean("test")
    public ChoiceDAO getChoiceDAO(@Autowired DataSource ds){
        return new ChoiceDAO(ds);
    }

    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        // Set the JPA provider to Hibernate
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Set the DataSource
        factoryBean.setDataSource(dataSource);

        // Specify the package containing your entity classes
        factoryBean.setPackagesToScan("fr.epita.quiz.datamodel");

        // Configure Hibernate properties
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        jpaProperties.setProperty("hibernate.show_sql", "true");
        factoryBean.setJpaProperties(jpaProperties);

        return factoryBean;
    }


}
