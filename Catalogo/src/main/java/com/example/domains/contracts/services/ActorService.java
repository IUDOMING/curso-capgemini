package com.example.domains.contracts.services;

<<<<<<< HEAD
import java.sql.Timestamp;
import java.util.List;

import com.example.domains.core.contracts.services.ProjectionDomainService;
import com.example.domains.entities.Actor;

public interface ActorService extends ProjectionDomainService<Actor, Integer> {

	List<Actor> novedades(Timestamp fecha);

=======
import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.entities.Actor;

public interface ActorService extends DomainService<Actor, Integer>{
	
>>>>>>> 862e2d75bdfc08585e5d4099527348875a982978
}
