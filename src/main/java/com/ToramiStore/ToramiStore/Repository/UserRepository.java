package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
