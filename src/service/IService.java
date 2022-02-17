/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author wiemhjiri
 */
public interface IService<T> {
   boolean insert(T t);
   boolean delete(T t);
   
   boolean update(T t);
    List<T>read();
   T readById(int id);
    
}
