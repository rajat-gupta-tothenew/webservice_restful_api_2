package org.ttn.sprintrest2.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

class Emp{
    int id;
    String name;
    int s_id;

    public Emp(int id, String name,int s_id) {
        this.id = id;
        this.name = name;
        this.s_id = s_id;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class EmpSal{
    int id;
    int salary;

    public EmpSal(int id, int salary) {
        this.id = id;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
public class Test {
    public static void main(String[] args) {
        List<Emp> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        int count =0;
        list1.add(new Emp(1,"raj",count));

        count +=1;
        list1.add(new Emp(2,"ram",count));

    list2.add(100);
    list2.add(200);

    for(Emp e :list1){
        if(e.getId()==1){
            System.out.println(list2.get(e.getS_id()));
        }

    }
//        list2.add(new EmpSal(3,30000));
//        list2.add(new EmpSal(4,20000));



//
//        //salary
//        Optional<EmpSal> op =  list2.stream().filter(e->e.getId() == 2).findFirst();
//
//        //id
//         list1.stream().filter(e->e.getId() == op.get().getId()).map(e->op.get().getSalary()).forEach(System.out::println);



    }
}
