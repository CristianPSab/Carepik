
package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.Cameres;
import cat.xtec.ioc.repository.CameraRepository;
import cat.xtec.ioc.service.CameraService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraServiceImplementation implements CameraService{
     @Autowired
    private CameraRepository cameraRepository;
    
    

    @Override
    public String Guardar(Cameres cm) {
        return cameraRepository.Guardar(cm);
    }

    @Override
    public String Eliminar(Cameres cm) {
        return cameraRepository.Eliminar(cm);
    }

    @Override
    public Cameres Llegir(int codi,int codiusuari) {
        return cameraRepository.Llegir(codi,codiusuari);
    }

    @Override
    public List<Cameres> Llistar(int codi) {
        return cameraRepository.Llistar(codi);
    }
}
