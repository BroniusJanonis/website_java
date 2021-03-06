package lt.web.service;

import lt.web.models.Fosters;

import java.util.List;

public interface IFosterService {
    List<Fosters> getFostersList();
    void updateFoster(Fosters fosters);
    Fosters saveFoster(Fosters foster);
}
