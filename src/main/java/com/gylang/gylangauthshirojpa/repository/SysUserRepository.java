package com.gylang.gylangauthshirojpa.repository;

        import com.gylang.gylangauthshirojpa.domian.SysUser;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

        import java.util.Collection;
        import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/16 10:00,
 * @DESC
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {

    SysUser findByName(String name);

    List<SysUser> findByIdIn(Collection<Long> sysUserIdList);

}
