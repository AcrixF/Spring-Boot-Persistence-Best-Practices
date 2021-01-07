package org.neoa.service;

import lombok.extern.log4j.Log4j2;
import org.neoa.entity.Publisher;
import org.neoa.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class PublisherService {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public void fetchingAllPublisherWithItsAssociations() {
        log.info("Fetching the Publisher Association via subGraph");
        List<Publisher> publishers = publisherRepository.findAll();
        publishers.forEach(System.out::println);
    }

    @Transactional
    public void fetchingAllPublisherWithItsAssociationsUsingJPQL() {
        log.info("Fetching the Publisher Association via subGraph and JPQL");
        List<Publisher> publishers = publisherRepository.fetchAllIdBetween1And3();
        publishers.forEach(System.out::println);
    }
}
