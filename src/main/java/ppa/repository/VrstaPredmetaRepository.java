package ppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ppa.model.VrstaPredmeta;

//This will be AUTO IMPLEMENTED by Spring into a Bean called VrstaPredmetaRepository
@Repository
public interface VrstaPredmetaRepository extends JpaRepository<VrstaPredmeta, Long> {

}
