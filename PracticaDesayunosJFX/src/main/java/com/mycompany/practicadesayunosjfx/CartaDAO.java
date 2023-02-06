/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicadesayunosjfx;

import java.util.ArrayList;
import java.util.List;
import models.Carta;

/**
 *
 * @author LorenLynchMcrae
 */
public interface CartaDAO {
    
    void save(Carta c);
    void update(Carta c);
    Carta get(Integer id);
    void delete(Carta c);
    List<Carta> getAll();
    
}
