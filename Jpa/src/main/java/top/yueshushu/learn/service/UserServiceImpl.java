package top.yueshushu.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.repository.UserCrudRepository;
import top.yueshushu.learn.repository.UserJpaRepository;
import top.yueshushu.learn.repository.UserPagingAndSortingRepository;
import top.yueshushu.learn.repository.UserSpecificationRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName:UserServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private UserPagingAndSortingRepository userPagingAndSortingRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserSpecificationRepository userSpecificationRepository;

    @Override
    public void addUser(User user) {
        userCrudRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userCrudRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userCrudRepository.deleteById(id);
    }

    @Override
    public void batchAddUser(List<User> userList) {
        userCrudRepository.saveAll(userList);
    }

    @Override
    public User findById(Integer id) {
        Optional<User> optional= userCrudRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        Iterable<User> iterator= userCrudRepository.findAll();
        List<User> userList=new ArrayList<>();
        iterator.forEach(n->userList.add(n));
        return userList;

    }

    @Override
    public List<User> findAllByIds(List<Integer> ids) {
        Iterable<User> iterator= userCrudRepository.findAllById(ids);
        List<User> userList=new ArrayList<>();
        iterator.forEach(n->userList.add(n));
        return userList;
    }

    @Override
    public Long count() {
        return userCrudRepository.count();
    }

    @Override
    public List<User> findAllOrderBySexAndAge() {
        Sort.Order sort1= Sort.Order.desc("sex");
        Sort.Order sort2 = Sort.Order.asc("age");
        Sort sort=Sort.by(sort1,sort2);
        Iterable<User> userIterable=userPagingAndSortingRepository.findAll(sort);
        List<User> userList=new ArrayList<>();
        userIterable.forEach(n->userList.add(n));
        return userList;
    }

    @Override
    public Page<User> pageAll() {
        Sort sort=Sort.by(Sort.Direction.DESC,"id");
        // 默认从0开始的
        Pageable pageable= PageRequest.of(2-1, 4, sort);
        Page<User> userPage=userPagingAndSortingRepository.findAll(pageable);
        return userPage;
    }

    @Override
    public List<User> jpaFindAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public List<User> findByExample(Example example) {
        return userJpaRepository.findAll(example);
    }

    @Override
    public List<User> findByName(String name) {
        return userJpaRepository.findByName(name);
    }


    @Override
    public List<User> findBySexAndAge(String sex, Integer age) {
        return userJpaRepository.findBySexAndAge(sex,age);
    }

    @Override
    public List<User> findBySexOrderByAge(String sex) {
        return userJpaRepository.findBySexOrderByAgeDesc(sex);
    }

    @Override
    public  List<Map<String,Object>> findQueryByName(String name) {
        return userJpaRepository.findQueryByName(name);
    }

    @Override
    public List<User> jpaFindAllSql(String name) {
        return userJpaRepository.findAllSql(name);
    }

    @Override
    public List<User> findByNameSexAndDesc(User user) {
        //1. 根据条件创建 Specification 对象信息
        Specification<User> specification=new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //1. 用于接收封装的查询对象
                List<Predicate> predicateList = new ArrayList<>();
                if(user!=null){
                    //1.如果name 不为空的话，对name 进行精确匹配
                    if(!StringUtils.isEmpty(user.getName())){
                        Predicate namePredicate = criteriaBuilder.equal(root.get("name"), user.getName());
                        predicateList.add(namePredicate);
                    }
                    //2.如果sex 不为空的话,也是精确匹配
                    if(!StringUtils.isEmpty(user.getSex())){
                        Predicate sexPredicate=criteriaBuilder.equal(root.get("sex"),user.getSex());
                        predicateList.add(sexPredicate);
                    }
                    //3.如果age不为空的话，就是 < 匹配
                    if(!StringUtils.isEmpty(user.getAge())){
                        Predicate agePreDicate=criteriaBuilder.lt(root.get("age"),user.getAge());
                        predicateList.add(agePreDicate);
                    }
                    //4. 如果description 不为空的话，进行模糊匹配
                    if(!StringUtils.isEmpty(user.getDescription())){
                        Predicate descPredicate=criteriaBuilder.like(root.get("description"),"%"+user.getDescription()
                        +"%");
                        predicateList.add(descPredicate);
                    }
                }
                return criteriaBuilder.and(predicateList.toArray(
                        new Predicate[predicateList.size()]
                ));

            }
        };

        return userSpecificationRepository.findAll(specification);

    }

    @Override
    public List<User> findAllQueryByName(String name) {

        return userJpaRepository.findAllQueryByName(name);
    }
}
