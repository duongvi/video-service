package org.magnum.mobilecloud.video.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {
	
	public Collection<Video> findByName(String name);
	
	//public Video findById(long id);
	
	public Collection<Video> findByDurationLessThan(long duration);
}
