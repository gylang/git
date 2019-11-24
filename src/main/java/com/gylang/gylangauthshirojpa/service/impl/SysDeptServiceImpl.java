package com.gylang.gylangauthshirojpa.service.impl;

import com.gylang.gylangauthshirojpa.VO.PageResult;
import com.gylang.gylangauthshirojpa.domian.SysDept;
import com.gylang.gylangauthshirojpa.domian.SysRole;
import com.gylang.gylangauthshirojpa.form.PageForm;
import com.gylang.gylangauthshirojpa.repository.SysDeptRepository;
import com.gylang.gylangauthshirojpa.service.SysDeptService;
import com.gylang.gylangauthshirojpa.service.SysRoleService;
import com.gylang.gylangauthshirojpa.utils.dySql.Criteria;
import com.gylang.gylangauthshirojpa.utils.dySql.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gylang,
 * @data 2019/11/19 22:09,
 * @DESC
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptRepository sysDeptRepository;


    @Override
    public SysDept findById(Long id) {
        return sysDeptRepository.findById(id).orElse(null);
    }

    @Override
    public List<SysDept> findByIdIn(List<Long> ids) {

        return sysDeptRepository.findByIdIn(ids);
    }

    @Override
    public SysDept save(SysDept sysDept) {
        return sysDeptRepository.save(sysDept);
    }

    @Override
    public PageResult findPage(PageForm pageForm) {

        Criteria<SysDept> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("id", pageForm.getParamValue("id"), true));
        criteria.add(Restrictions.eq("id", pageForm.getParamValue("id"), true));
        criteria.add(Restrictions.like("name", pageForm.getParamValue("name"), true));
        criteria.add(Restrictions.like("remark", pageForm.getParamValue("remark"), true));
        Page<SysDept> sysDeptPage = sysDeptRepository.findAll(criteria, pageForm.getPage());
        return new PageResult<SysDept>().setAndGet(sysDeptPage);
    }

    @Override
    public boolean delete(SysDept sysDept) {

        sysDeptRepository.delete(sysDept);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(List<SysDept> t) {

        sysDeptRepository.deleteInBatch(t);
        return true;
    }

    @Override
    public List<SysDept> findTree() {
        List<SysDept> sysDepts = new ArrayList<>();
        List<SysDept> depts = sysDeptRepository.findAll();
        for (SysDept dept : depts) {
            if (dept.getParentId() == null || dept.getParentId() == 0) {
                dept.setLevel(0);
                sysDepts.add(dept);
            }
        }
        findChildren(sysDepts, depts);
        return sysDepts;
    }

    private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
        for (SysDept sysDept : sysDepts) {
            List<SysDept> children = new ArrayList<>();
            for (SysDept dept : depts) {
                if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
                    dept.setParentName(dept.getName());
                    dept.setLevel(sysDept.getLevel() + 1);
                    children.add(dept);
                }
            }
            sysDept.setChildren(children);
            findChildren(children, depts);
        }
    }
}
