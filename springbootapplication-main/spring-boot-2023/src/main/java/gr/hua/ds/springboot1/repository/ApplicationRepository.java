package gr.hua.ds.springboot1.repository;

import gr.hua.ds.springboot1.entity.Application;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "applications")
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
