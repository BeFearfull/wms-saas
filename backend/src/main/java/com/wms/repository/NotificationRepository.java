package com.wms.repository;

import com.wms.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.user.id = ?1 ORDER BY n.createdAt DESC")
    Page<Notification> findByUserId(Long userId, Pageable pageable);
    @Query("SELECT n FROM Notification n WHERE n.user.id = ?1 AND n.isRead = false ORDER BY n.createdAt DESC")
    List<Notification> findUnreadByUserId(Long userId);
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.id = ?1 AND n.isRead = false")
    long countUnreadByUserId(Long userId);
}
