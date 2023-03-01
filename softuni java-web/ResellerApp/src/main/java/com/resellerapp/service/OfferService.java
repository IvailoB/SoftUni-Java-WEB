package com.resellerapp.service;

import com.resellerapp.model.dto.OfferAddDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.view.OffersWithUsernamesDTO;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    private final ConditionService conditionService;
    private final UserService userService;

    private final LoggedUser loggedUser;

    public OfferService(OfferRepository offerRepository, ModelMapper modelMapper, ConditionService conditionService, UserService userService, LoggedUser loggedUser) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.conditionService = conditionService;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    public void addOrder(OfferAddDTO offerAddDTO) {

        Offer offer = modelMapper.map(offerAddDTO, Offer.class);

        Condition condition = conditionService.findCondition(offerAddDTO.getCondition());

        offer.setUser(userService.findUserByID(offerAddDTO.getId()));
        offer.setCondition(condition);
        offerRepository.save(offer);
    }

    public Set<Offer> getOfferFromCurrentUser(Long id) {
        return offerRepository.findAllByUser_Id(id);
    }

    public Set<OffersWithUsernamesDTO> getPostsFromOtherUsers(Long id) {
        Set<Offer> offersByUserIdNot = offerRepository.findOffersByUserIdNot(id).orElse(null);

        return offersByUserIdNot.stream()
                .map(offer -> {
                    OffersWithUsernamesDTO offersWithUsernamesDTO = new OffersWithUsernamesDTO();

                    offersWithUsernamesDTO.setId(offer.getId());
                    offersWithUsernamesDTO.setDescription(offer.getDescription());
                    offersWithUsernamesDTO.setUser(offer.getUser());
                    offersWithUsernamesDTO.setPrice(offer.getPrice());
                    offersWithUsernamesDTO.setCondition(offer.getCondition());

                    return offersWithUsernamesDTO;
                })
                .collect(Collectors.toSet());
    }

    public void removeOfferById(Long id) {
        offerRepository.deleteById(id);
    }

    public void addOfferByID(Long id) {

        Offer offer = offerRepository.findById(id).orElse(null);

        User user = userService.findUserById(loggedUser.getId());

        user.getOffers().add(offer);

        removeOfferById(id);
    }
}
