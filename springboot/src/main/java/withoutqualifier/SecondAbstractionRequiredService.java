package withoutqualifier;

import org.springframework.stereotype.Service;

@Service
class SecondAbstractionRequiredService {

    private final DifficultAbstraction difficultAbstraction;

    SecondAbstractionRequiredService(DifficultAbstraction secondDifficultAbstraction) {
        this.difficultAbstraction = secondDifficultAbstraction;
    }
}
