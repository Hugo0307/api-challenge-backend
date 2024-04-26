package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.external.gateway.CallExternalClientGateway;
import com.java.spring.apichallengebackend.usecase.LoadSagaDataUsecase;
import com.java.spring.apichallengebackend.usecase.utils.FileValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoadSagaDataUseCaseImpl implements LoadSagaDataUsecase {

    @Autowired
    private CallExternalClientGateway externalClientGateway;

    @Override
    public void loadSagaData() {

        boolean isExistsDataInFile = FileValidateUtil.isExistsDataInFile();
        if (!isExistsDataInFile) {
            log.info("Request saga data from the external client");
            SagaStarWars sagaStarWars = externalClientGateway.executeCallExternalClient();




            return;
        }
        log.info("Load saga data by file in memory");
        //TODO: persistir na base a partir do arquivo .json
    }


}
