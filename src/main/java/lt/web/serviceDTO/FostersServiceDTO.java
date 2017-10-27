package lt.web.serviceDTO;

import lt.web.modelDTO.FostersDTO;
import lt.web.models.Fosters;
import org.springframework.stereotype.Service;

@Service
public class FostersServiceDTO {

    public FostersDTO convertFostersDTO(Fosters foster) {
        FostersDTO fostersDTO = new FostersDTO(foster.getFosterId(), foster.getName(), foster.getSurname(), foster.getPhone(), foster.getAddress());
        return fostersDTO;
    }
}
