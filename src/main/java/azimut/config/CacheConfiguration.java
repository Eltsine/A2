package azimut.config;

import com.github.benmanes.caffeine.jcache.configuration.CaffeineConfiguration;
import java.util.OptionalLong;
import java.util.concurrent.TimeUnit;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Caffeine caffeine = jHipsterProperties.getCache().getCaffeine();

        CaffeineConfiguration<Object, Object> caffeineConfiguration = new CaffeineConfiguration<>();
        caffeineConfiguration.setMaximumSize(OptionalLong.of(caffeine.getMaxEntries()));
        caffeineConfiguration.setExpireAfterWrite(OptionalLong.of(TimeUnit.SECONDS.toNanos(caffeine.getTimeToLiveSeconds())));
        caffeineConfiguration.setStatisticsEnabled(true);
        jcacheConfiguration = caffeineConfiguration;
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, azimut.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, azimut.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, azimut.domain.User.class.getName());
            createCache(cm, azimut.domain.Authority.class.getName());
            createCache(cm, azimut.domain.User.class.getName() + ".authorities");
            createCache(cm, azimut.domain.AnneeScolaire.class.getName());
            createCache(cm, azimut.domain.AnneeScolaire.class.getName() + ".inscriptions");
            createCache(cm, azimut.domain.Periode.class.getName());
            createCache(cm, azimut.domain.Formation.class.getName());
            createCache(cm, azimut.domain.Formation.class.getName() + ".modules");
            createCache(cm, azimut.domain.Module.class.getName());
            createCache(cm, azimut.domain.Module.class.getName() + ".sessions");
            createCache(cm, azimut.domain.Rapport.class.getName());
            createCache(cm, azimut.domain.Session.class.getName());
            createCache(cm, azimut.domain.Salle.class.getName());
            createCache(cm, azimut.domain.Formateur.class.getName());
            createCache(cm, azimut.domain.Formateur.class.getName() + ".specialites");
            createCache(cm, azimut.domain.Formateur.class.getName() + ".personnes");
            createCache(cm, azimut.domain.Specialite.class.getName());
            createCache(cm, azimut.domain.Apprenant.class.getName());
            createCache(cm, azimut.domain.Etablissement.class.getName());
            createCache(cm, azimut.domain.Etablissement.class.getName() + ".inscriptions");
            createCache(cm, azimut.domain.Etablissement.class.getName() + ".formateurs");
            createCache(cm, azimut.domain.Inscription.class.getName());
            createCache(cm, azimut.domain.Inscription.class.getName() + ".formations");
            // jhipster-needle-caffeine-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
