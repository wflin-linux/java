package com.wflin.Respostory;

import com.wflin.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRespostpy extends JpaRepository<Users, Integer> {
    /**
     * @param email 邮箱
     * @return 有值返回 true，没有值返回 false
     */

    Users findByEmail(String email);

    // 删除
    @Transactional
    @Modifying
    @Query(value="update Users u set u.view=:view where u.id =:nn")
    //返回的是 id
    int findByUserIdAndVew(@Param("nn") Integer id, @Param("view") Integer view);

    Users findByname(String name);

}
