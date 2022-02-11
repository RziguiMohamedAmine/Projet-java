/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author Houssem Charef
 */
public interface IService<T> {

    Boolean insert(T t);

    Boolean update(T t);

    Boolean delete(T t);

    List<T> getAll();

    T getOne(int id);
}
