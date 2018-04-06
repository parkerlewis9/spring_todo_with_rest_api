package com.clara.taskdb.repository;

import com.clara.taskdb.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    /* Find all with urgent tasks first */
    List<Task> findAllByOrderByUrgentDesc();
    
    /* Custom query for updating a Task object, setting the completed value
    * Returns the number of rows modified,
    * So if the Task is found and updated, returns 1
    * If the Task with this id is not in the DB, no rows are updated, so returns 0 */
    @Transactional
    @Modifying
    @Query("Update Task t set t.completed = ?1 where t.id = ?2")
    int setTaskCompleted(boolean completed, long id);
    
}


