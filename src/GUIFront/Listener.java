/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIFront;

/**
 *
 * @author Houssem Charef
 * @param <T>
 */
public interface Listener<T> {

    public void onClickListener(T entity);
}
