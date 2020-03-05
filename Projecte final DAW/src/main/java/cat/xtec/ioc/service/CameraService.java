

package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.Cameres;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface CameraService {
   public String Guardar(Cameres cm);
   public String Eliminar(Cameres cm);
   public Cameres Llegir(int codi,int codiusuari);
   public List<Cameres> Llistar(int codi); 
}
