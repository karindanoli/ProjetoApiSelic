package br.com.ibm.repositories;

import br.com.ibm.entities.SelicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelicRepository extends JpaRepository<SelicEntity,Long> {
    //interface repository é por default

}
