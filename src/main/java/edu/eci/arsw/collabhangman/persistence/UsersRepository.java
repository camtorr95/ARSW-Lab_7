/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.collabhangman.persistence;

import edu.eci.arsw.collabhangman.model.game.entities.User;
import java.util.List;
import java.util.Set;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author hcadavid
 */
public interface UsersRepository extends MongoRepository<User, Integer> {

    @Query("{ '_id' : ?0 }")
    public User getUserByID(Integer id) throws PersistenceException;

    @Query("{scores: {$elemMatch: {value: {$gte: ?0}}}}")
    public List<User> findByScoreGreaterThan(Integer Score) throws PersistenceException;

    @Query("{}")
    public Set<User> getAllUsers();
}
