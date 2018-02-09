package ppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ppa.model.VrstaPostupka;

//This will be AUTO IMPLEMENTED by Spring into a Bean called VrstaPostupkaRepository
@Repository
public interface VrstaPostupkaRepository extends JpaRepository<VrstaPostupka, Long> {

}
