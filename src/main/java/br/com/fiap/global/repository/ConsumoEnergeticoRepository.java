package br.com.fiap.global.repository;

import br.com.fiap.global.model.ConsumoEnergetico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumoEnergeticoRepository extends JpaRepository<ConsumoEnergetico, Long> {
    List<ConsumoEnergetico> findByPosteId(Long posteId);
}
