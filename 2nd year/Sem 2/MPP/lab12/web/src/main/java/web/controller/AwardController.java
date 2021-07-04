//package web.controller;
//
//import core.domain.Award;
//import core.service.AwardService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import web.converter.AwardConverter;
//import web.dto.AwardDTO;
//import web.dto.AwardsDTO;
//
//import java.util.List;
//import java.util.Set;
//
//@RestController
//public class AwardController {
//    public static final Logger logger = LoggerFactory.getLogger(AwardController.class);
//
//    @Autowired
//    private AwardService awardService;
//
//    @Autowired
//    private AwardConverter awardConverter;
//
//    @RequestMapping(value = "/awards", method = RequestMethod.GET)
//    List<AwardDTO> getAwards() {
//        logger.trace("begin get awards");
//        List<AwardDTO> cpy = new AwardsDTO(awardConverter
//                .convertModelsToDTOs(awardService.getAllAwards())).getAwards();
//        logger.trace("end get awards={}",cpy);
//        return cpy;
//    }
//    @RequestMapping(value="/awards/get-page/pageno={pageNo},size={size}",method=RequestMethod.GET)
//    List<AwardDTO> getAwardsOnPage(@PathVariable Integer pageNo, @PathVariable Integer size){
//        return awardConverter.convertModelsToDTOs(awardService.getAwardsOnPage(pageNo,size));
//    }
//
////    @RequestMapping(value = "/awards/{id}")
////    AwardsDTO getAwardsFromRepositoryByCatId(@PathVariable Long id) {
////        List<Award> awards = awardService.getAwardsOfCat(id);
////        return new AwardsDTO(awardConverter.convertModelsToDTOs(awards));
////    }
//
//    @RequestMapping(value = "/awards", method = RequestMethod.POST)
//    void addAward(@RequestBody AwardDTO awardDTO) {
//        logger.trace(awardDTO.toString());
//        var award = awardConverter.convertDtoToModel(awardDTO);
//        awardService.addAward(
//                award.getDate()
//        );
//    }
//
//    @RequestMapping(value = "/awards/{id}", method = RequestMethod.PUT)
//    void updateAward(@PathVariable Long id, @RequestBody AwardDTO awardDTO) {
//        var award = awardConverter.convertDtoToModel(awardDTO);
//        awardService.updateAward(
//                id,
//                award.getDate()
//        );
//    }
//
//    @RequestMapping(value = "/awards/{id}", method = RequestMethod.DELETE)
//    ResponseEntity<?> deleteAward(@PathVariable Long id) {
//        awardService.deleteAward(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}
