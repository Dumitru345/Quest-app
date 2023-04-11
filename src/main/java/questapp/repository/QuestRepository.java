package questapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import questapp.entity.Quest;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {

}