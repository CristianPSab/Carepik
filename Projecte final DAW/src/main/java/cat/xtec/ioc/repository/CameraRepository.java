/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.repository;

import cat.xtec.ioc.domain.Cameres;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CameraRepository {

    public String Guardar(Cameres cm);
    public String Eliminar(Cameres cm);
    public Cameres Llegir(int codi,int codiusuari);
    public List<Cameres> Llistar(int codi);

    
}
