package br.com.fiap.global.repository;

import br.com.fiap.global.model.EconomiaEnergetica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EconomiaEnergeticaRepository extends JpaRepository<EconomiaEnergetica, Long> {
}
