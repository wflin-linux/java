package com.wflin.Respostory;

import com.wflin.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author wflin
 */
public interface BlogRespostpy extends JpaRepository<Blog,Integer> {

    // 删除
    @Transactional
    @Modifying
    @Query(value="update Blog b set b.view=:view where b.id =:nn")
    //返回的是 id
    int findByBlogIdAndVew(@Param("nn") Integer id,@Param("view") Integer view);

    // 条件查询
    @Modifying
    @Query(value = "select b from Blog b where b.title like ?1 " + "or b.author like ?1" +"or b.content like ?1"+" or b.tag like ?1")
    List findBlogsByParam(String param);

}
